package gft.impact.proposal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gft.impact.proposal.model.ProductComplement;
import gft.impact.proposal.model.ProductProposal;
import gft.impact.proposal.repository.ProductComplementRepository;
import gft.impact.proposal.repository.ProductProposalRepository;
import gft.impact.proposal.repository.ProposalRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductProposalService {

    @Autowired(required = true)
    private ProposalRepository proposalRepository;

    @Autowired(required = true)
    private ProductProposalRepository productProposalRepository;

    @Autowired(required = false)
    private ProductComplementRepository productComplementRepository;

    public ProductProposal getProductProposal(String productCode) {
        validateProductCode(productCode);

        ProductProposal productProposal = productProposalRepository.findById(productCode)
                .orElseThrow(() -> new ProductNotFoundException("PRODUTO NAO ENCONTRADO"));

        ProductComplement productComplement = productComplementRepository
                .findByCparmProdtPrevdAndCparmProdtCompl("FORMU", productCode);

        if (productComplement == null) {
            productComplement = new ProductComplement();
            productComplement.setCparmProdt12("0");
        }

        productProposal.setEcmnhoDoctoPreve(productProposal.getEcmnhoDoctoPreve().trim());

        return productProposal;
    }

    @Transactional
    public void deleteProposal(Long proposalId) {
        if (proposalId == null || proposalId <= 0) {
            throw new InvalidProposalException("CODIGO DO PRODUTO INVALIDO");
        }

        boolean exists = proposalRepository.existsById(proposalId);
        if (!exists) {
            throw new ProposalNotFoundException("PRODUTO NAO ENCONTRADO");
        }

        proposalRepository.deleteFromTfrmulBrancPrevd(proposalId);
        proposalRepository.deleteFromTfrmulCpoObrig(proposalId);
        proposalRepository.deleteFromTfrmulProdtBranc(proposalId);
        proposalRepository.deleteFromTparmProdtCompl(proposalId);
    }
    
    private void validateProductCode(String productCode) {
        if (!productCode.matches("\\d+") || productCode.equals("0")) {
            throw new InvalidProductCodeException("CODIGO DO PRODUTO INVALIDO");
        }
    }
}
