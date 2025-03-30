package gft.impact.proposal.controllers;

import java.util.List;

import gft.impact.proposal.model.BlankProposalForm;
import gft.impact.proposal.model.ProductParameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlankProposalRequest {

    @NotNull
    @Valid
    private BlankProposalForm form;

    @NotNull
    @Valid
    private ProductParameter parameter;

    @NotEmpty
    private List<Integer> commercialProductCodes;
}
