package gft.impact.proposal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.impact.proposal.model.ProductProposal;
import gft.impact.proposal.service.ProductProposalService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/product-proposals")
public class ProductProposalController {

    @Autowired
    private ProductProposalService productProposalService;

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductProposal> getProductProposal(@PathVariable String productCode) {
        ProductProposal productProposal = productProposalService.getProductProposal(productCode);
        return ResponseEntity.ok(productProposal);
    }
    
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<ProposalResponse> deleteProposal(
            @PathVariable @NotNull @Positive Long proposalId) {
    	productProposalService.deleteProposal(proposalId);
        return ResponseEntity.ok(new ProposalResponse(0, "OPERACAO REALIZADA COM SUCESSO"));
    }
    
}
