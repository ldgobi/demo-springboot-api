package gft.impact.proposal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.impact.proposal.model.BlankProposalForm;
import gft.impact.proposal.service.BlankProposalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blank-proposals")
public class BlankProposalController {

    private final BlankProposalService blankProposalService;

    @Autowired
    public BlankProposalController(BlankProposalService blankProposalService) {
        this.blankProposalService = blankProposalService;
    }

    @PostMapping
    public ResponseEntity<BlankProposalForm> createBlankProposal(@Valid @RequestBody BlankProposalRequest request) {
        BlankProposalForm createdForm = blankProposalService.createBlankProposal(
                request.getForm(),
                request.getParameter(),
                request.getCommercialProductCodes()
        );
        return ResponseEntity.ok(createdForm);
    }
}
