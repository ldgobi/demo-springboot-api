package  gft.impact.proposal.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import gft.impact.proposal.model.BlankProposalForm;
import gft.impact.proposal.model.FormProduct;
import gft.impact.proposal.model.ProductParameter;
import gft.impact.proposal.repository.BlankProposalFormRepository;
import gft.impact.proposal.repository.FormProductRepository;
import gft.impact.proposal.repository.ProductParameterDefaultRepository;
import gft.impact.proposal.repository.ProductParameterRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BlankProposalServiceTest {

    @Mock
    private BlankProposalFormRepository blankProposalFormRepository;
    
    @Mock
    private ProductParameterRepository productParameterRepository;
    
    @Mock
    private ProductParameterDefaultRepository productParameterDefaultRepository;
    
    @Mock
    private FormProductRepository formProductRepository;
    
    @InjectMocks
    private BlankProposalService blankProposalService;
    
    @Captor
    private ArgumentCaptor<BlankProposalForm> formCaptor;
    
    @Captor
    private ArgumentCaptor<ProductParameter> parameterCaptor;
    
    @Captor
    private ArgumentCaptor<FormProduct> formProductCaptor;
    
    private BlankProposalForm form;
    private ProductParameter parameter;
    private List<Integer> commercialProductCodes;
    
    @BeforeEach
    void setUp() {
        form = new BlankProposalForm();
        form.setFormNumber(null);
        
        parameter = new ProductParameter();
        parameter.setSituationCode(1);
        parameter.setCreatedByUser("testUser");
        
        commercialProductCodes = Arrays.asList(101, 102, 103);
    }
    
    @Test
    void createBlankProposal_WithNewFormNumber_ShouldCreateNewProposal() {
        // Arrange
        Long maxFormNumber = 42L;
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(maxFormNumber);
        when(blankProposalFormRepository.save(any(BlankProposalForm.class))).thenReturn(form);
        when(productParameterRepository.findByPrefixSuffix(anyString(), anyString())).thenReturn(Optional.empty());
        when(productParameterDefaultRepository.save(any(ProductParameter.class))).thenReturn(parameter);
        when(formProductRepository.save(any(FormProduct.class))).thenReturn(new FormProduct());
        
        // Act
        BlankProposalForm result = blankProposalService.createBlankProposal(form, parameter, commercialProductCodes);
        
        // Assert
        verify(blankProposalFormRepository).save(formCaptor.capture());
        BlankProposalForm capturedForm = formCaptor.getValue();
        
        assertEquals(maxFormNumber + 1, capturedForm.getFormNumber(), "Form number should be incremented by 1");
        assertNotNull(capturedForm.getCreatedAt(), "Created date should not be null");
        
        verify(productParameterDefaultRepository).save(parameterCaptor.capture());
        ProductParameter capturedParameter = parameterCaptor.getValue();
        
        assertEquals("FORMU", capturedParameter.getProductParameterPrefix(), "Parameter prefix should be FORMU");
        assertEquals(String.format("%06d", maxFormNumber + 1), capturedParameter.getProductParameterSuffix(), 
                "Parameter suffix should be formatted form number");
        
        verify(formProductRepository, times(commercialProductCodes.size())).save(formProductCaptor.capture());
        List<FormProduct> capturedFormProducts = formProductCaptor.getAllValues();
        
        assertEquals(commercialProductCodes.size(), capturedFormProducts.size(), "Should create form products for all codes");
        for (int i = 0; i < commercialProductCodes.size(); i++) {
            assertEquals(maxFormNumber + 1, capturedFormProducts.get(i).getFormNumber(), 
                    "Form product should have correct form number");
            assertEquals(commercialProductCodes.get(i), capturedFormProducts.get(i).getCommercialProductCode(), 
                    "Form product should have correct product code");
        }
        
        assertEquals(form, result, "Method should return the saved form");
    }
    
    @Test
    void createBlankProposal_WithNoExistingForms_ShouldStartWithFormNumberOne() {
        // Arrange
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(null);
        when(blankProposalFormRepository.save(any(BlankProposalForm.class))).thenReturn(form);
        when(productParameterRepository.findByPrefixSuffix(anyString(), anyString())).thenReturn(Optional.empty());
        when(productParameterDefaultRepository.save(any(ProductParameter.class))).thenReturn(parameter);
        
        // Act
        blankProposalService.createBlankProposal(form, parameter, commercialProductCodes);
        
        // Assert
        verify(blankProposalFormRepository).save(formCaptor.capture());
        assertEquals(1L, formCaptor.getValue().getFormNumber(), "Form number should start at 1 when no forms exist");
    }
    
    @Test
    void createBlankProposal_WithExistingParameter_ShouldUpdateParameter() {
        // Arrange
        Long maxFormNumber = 100L;
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(maxFormNumber);
        when(blankProposalFormRepository.save(any(BlankProposalForm.class))).thenReturn(form);
        
        ProductParameter existingParameter = new ProductParameter();
        existingParameter.setSituationCode(0);
        existingParameter.setCreatedByUser("oldUser");
        existingParameter.setCreatedAt(LocalDateTime.now().minusDays(1));
        
        when(productParameterRepository.findByPrefixSuffix("FORMU", String.format("%06d", maxFormNumber + 1)))
            .thenReturn(Optional.of(existingParameter));
        
        // Act
        blankProposalService.createBlankProposal(form, parameter, commercialProductCodes);
        
        // Assert
        verify(productParameterDefaultRepository).save(parameterCaptor.capture());
        ProductParameter updatedParameter = parameterCaptor.getValue();
        
        assertEquals(parameter.getSituationCode(), updatedParameter.getSituationCode(), 
                "Situation code should be updated");
        assertEquals(parameter.getCreatedByUser(), updatedParameter.getCreatedByUser(), 
                "Created by user should be updated");
        assertNotNull(updatedParameter.getCreatedAt(), "Created date should be updated");
    }
    
    @Test
    void createBlankProposal_WithEmptyProductCodes_ShouldNotCreateFormProducts() {
        // Arrange
        List<Integer> emptyProductCodes = List.of();
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(1L);
        when(blankProposalFormRepository.save(any(BlankProposalForm.class))).thenReturn(form);
        when(productParameterRepository.findByPrefixSuffix(anyString(), anyString())).thenReturn(Optional.empty());
        
        // Act
        blankProposalService.createBlankProposal(form, parameter, emptyProductCodes);
        
        // Assert
        verify(formProductRepository, never()).save(any(FormProduct.class));
    }
    
    @Test
    void createBlankProposal_ShouldSetCreatedAtForFormAndParameter() {
        // Arrange
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(1L);
        when(blankProposalFormRepository.save(any(BlankProposalForm.class))).thenReturn(form);
        when(productParameterRepository.findByPrefixSuffix(anyString(), anyString())).thenReturn(Optional.empty());
        
        LocalDateTime beforeTest = LocalDateTime.now();
        
        // Act
        blankProposalService.createBlankProposal(form, parameter, commercialProductCodes);
        
        // Assert
        verify(blankProposalFormRepository).save(formCaptor.capture());
        verify(productParameterDefaultRepository).save(parameterCaptor.capture());
        
        LocalDateTime afterTest = LocalDateTime.now();
        
        LocalDateTime formCreatedAt = formCaptor.getValue().getCreatedAt();
        LocalDateTime parameterCreatedAt = parameterCaptor.getValue().getCreatedAt();
        
        assertNotNull(formCreatedAt, "Form created date should not be null");
        assertNotNull(parameterCreatedAt, "Parameter created date should not be null");
        
        // Check that dates are between before and after test execution
        assertTrue(
            !formCreatedAt.isBefore(beforeTest) && !formCreatedAt.isAfter(afterTest),
            "Form created date should be set to current time"
        );
        
        assertTrue(
            !parameterCreatedAt.isBefore(beforeTest) && !parameterCreatedAt.isAfter(afterTest),
            "Parameter created date should be set to current time"
        );
    }
}
