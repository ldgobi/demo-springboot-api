package gft.impact.proposal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gft.impact.proposal.model.ProductParameter;
import gft.impact.proposal.repository.ProductParameterDefaultRepository;

@Service
public class ProductComplementService {

	@Autowired
    private ProductParameterDefaultRepository repository;

    public ProductParameter createProductComplement(ProductParameter productComplement) {
//        productComplement.setCreatedAt(LocalDateTime.now());
        return repository.save(productComplement);
    }

    public Optional<ProductParameter> getProductComplement(String id) {
        return repository.findById(id);
    }

    public ProductParameter updateProductComplement(String id, ProductParameter updatedProductComplement) {
        return repository.findById(id)
                .map(existingProductComplement -> {
                    existingProductComplement.setProductParameterPrefix(updatedProductComplement.getProductParameterPrefix());
                    existingProductComplement.setProductParameterSuffix(updatedProductComplement.getProductParameterSuffix());
                    existingProductComplement.setSituationCode(updatedProductComplement.getSituationCode());
                    existingProductComplement.setCreatedByUser(updatedProductComplement.getCreatedByUser());
                    existingProductComplement.setCreatedAt(updatedProductComplement.getCreatedAt());
//                    // ... (update other parameters)
                    return repository.save(existingProductComplement);
                })
                .orElseThrow(() -> new RuntimeException("Product complement not found"));
    }

    public void deleteProductComplement(String id) {
        repository.deleteById(id);
    }
}
