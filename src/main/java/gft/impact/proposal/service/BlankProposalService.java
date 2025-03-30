package gft.impact.proposal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gft.impact.proposal.model.BlankProposalForm;
import gft.impact.proposal.model.FormProduct;
import gft.impact.proposal.model.ProductParameter;
import gft.impact.proposal.repository.BlankProposalFormRepository;
import gft.impact.proposal.repository.FormProductRepository;
import gft.impact.proposal.repository.ProductParameterDefaultRepository;
import gft.impact.proposal.repository.ProductParameterRepository;
import jakarta.transaction.Transactional;

@Service
public class BlankProposalService {

    private BlankProposalFormRepository blankProposalFormRepository;
    private ProductParameterRepository productParameterRepository;
    private ProductParameterDefaultRepository productParameterDefaultRepository;
    private FormProductRepository formProductRepository;

    public BlankProposalService(BlankProposalFormRepository blankProposalFormRepository,
                                ProductParameterRepository productParameterRepository,
                                ProductParameterDefaultRepository productParameterDefaultRepository,
                                FormProductRepository formProductRepository) {
        this.blankProposalFormRepository = blankProposalFormRepository;
        this.productParameterRepository = productParameterRepository;
        this.productParameterDefaultRepository = productParameterDefaultRepository;
        this.formProductRepository = formProductRepository;
    }

    @Transactional
    public BlankProposalForm createBlankProposal(BlankProposalForm form, ProductParameter parameter, List<Integer> commercialProductCodes) {
        // Generate new form number
        Long newFormNumber = generateNewFormNumber();
        form.setFormNumber(newFormNumber);
        form.setCreatedAt(LocalDateTime.now());

        // Insert blank proposal form
        BlankProposalForm savedForm = blankProposalFormRepository.save(form);

        // Insert or update product parameter
        String parameterPrefix = "FORMU";
        String parameterSuffix = String.format("%06d", newFormNumber);
        parameter.setProductParameterPrefix(parameterPrefix);
        parameter.setProductParameterSuffix(parameterSuffix);
        parameter.setCreatedAt(LocalDateTime.now());

        Optional<ProductParameter> existingParameter = productParameterRepository.findByPrefixSuffix(parameterPrefix, parameterSuffix);
        if (existingParameter.isPresent()) {
            ProductParameter updatedParameter = existingParameter.get();
            updatedParameter.setSituationCode(parameter.getSituationCode());
            updatedParameter.setCreatedByUser(parameter.getCreatedByUser());
            updatedParameter.setCreatedAt(LocalDateTime.now());
            productParameterDefaultRepository.save(updatedParameter);
        } else {
            productParameterDefaultRepository.save(parameter);
        }

        // Associate products with blank proposal form
        for (Integer productCode : commercialProductCodes) {
            FormProduct formProduct = new FormProduct();
            formProduct.setFormNumber(newFormNumber);
            formProduct.setCommercialProductCode(productCode);
            formProductRepository.save(formProduct);
        }

        return savedForm;
    }

    private Long generateNewFormNumber() {
        Long maxFormNumber = blankProposalFormRepository.findMaxFormNumber();
        return (maxFormNumber != null) ? maxFormNumber + 1 : 1L;
    }
}
