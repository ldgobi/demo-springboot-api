package gft.impact.proposal.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalResponse {
    private int codigoRetorno;
    private String avisoRetorno;

    public ProposalResponse(int codigoRetorno, String avisoRetorno) {
        this.codigoRetorno = codigoRetorno;
        this.avisoRetorno = avisoRetorno;
    }

    // Getters and setters
}
