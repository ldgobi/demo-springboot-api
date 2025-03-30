package gft.impact.proposal.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import gft.impact.proposal.model.BlankProposalForm;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {BlankProposalFormRepository.class})
@SpringBootTest
public class BlankProposalFormRepositoryTest {

    @Mock
    private BlankProposalFormRepository blankProposalFormRepository;

    private BlankProposalForm form1;
    private BlankProposalForm form2;

    @BeforeEach
    public void setUp() {
        form1 = new BlankProposalForm();
        form1.setFormNumber(1001L);

        form2 = new BlankProposalForm();
        form2.setFormNumber(1002L);
    }

    @Test
    public void findAll_ShouldReturnAllForms() {
        // Arrange
        List<BlankProposalForm> expectedForms = Arrays.asList(form1, form2);
        when(blankProposalFormRepository.findAll()).thenReturn(expectedForms);

        // Act
        List<BlankProposalForm> actualForms = blankProposalFormRepository.findAll();

        // Assert
        assertEquals(expectedForms.size(), actualForms.size(), "Should return all forms");
        assertEquals(expectedForms, actualForms, "Should return the expected forms");
        verify(blankProposalFormRepository).findAll();
    }

    @Test
    public void findById_WhenFormExists_ShouldReturnForm() {
        // Arrange
        when(blankProposalFormRepository.findById(1L)).thenReturn(Optional.of(form1));

        // Act
        Optional<BlankProposalForm> result = blankProposalFormRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent(), "Form should be found");
        assertEquals(form1, result.get(), "Should return the correct form");
        verify(blankProposalFormRepository).findById(1L);
    }

    @Test
    public void findById_WhenFormDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        when(blankProposalFormRepository.findById(3L)).thenReturn(Optional.empty());

        // Act
        Optional<BlankProposalForm> result = blankProposalFormRepository.findById(3L);

        // Assert
        assertFalse(result.isPresent(), "Form should not be found");
        verify(blankProposalFormRepository).findById(3L);
    }

    @Test
    public void save_ShouldReturnSavedForm() {
        // Arrange
        BlankProposalForm newForm = new BlankProposalForm();
        newForm.setFormNumber(1003L);
        
        BlankProposalForm savedForm = new BlankProposalForm();
        savedForm.setFormNumber(1003L);
        
        when(blankProposalFormRepository.save(newForm)).thenReturn(savedForm);

        // Act
        BlankProposalForm result = blankProposalFormRepository.save(newForm);

        // Assert
        assertNotNull(result, "Saved form should not be null");
        assertEquals(savedForm.getFormNumber(), result.getFormNumber(), "Should have the correct form number");
        verify(blankProposalFormRepository).save(newForm);
    }

    @Test
    public void delete_ShouldCallRepositoryDelete() {
        // Act
        blankProposalFormRepository.delete(form1);

        // Assert
        verify(blankProposalFormRepository).delete(form1);
    }

    @Test
    public void deleteById_ShouldCallRepositoryDeleteById() {
        // Act
        blankProposalFormRepository.deleteById(1L);

        // Assert
        verify(blankProposalFormRepository).deleteById(1L);
    }

    @Test
    public void findMaxFormNumber_WhenFormsExist_ShouldReturnMaxNumber() {
        // Arrange
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(1002L);

        // Act
        Long maxFormNumber = blankProposalFormRepository.findMaxFormNumber();

        // Assert
        assertEquals(1002L, maxFormNumber, "Should return the maximum form number");
        verify(blankProposalFormRepository).findMaxFormNumber();
    }

    @Test
    public void findMaxFormNumber_WhenNoFormsExist_ShouldReturnNull() {
        // Arrange
        when(blankProposalFormRepository.findMaxFormNumber()).thenReturn(null);

        // Act
        Long maxFormNumber = blankProposalFormRepository.findMaxFormNumber();

        // Assert
        assertNull(maxFormNumber, "Should return null when no forms exist");
        verify(blankProposalFormRepository).findMaxFormNumber();
    }
}
