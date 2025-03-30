package gft.impact.proposal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TFRMUL_BRANC_PREVD")
public class ProductProposal {

    @Id
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CFRMUL_PPSTA_PREVD")
    private String cfrmulPpstaPrevd;

    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CFNALD_PPSTA")
    private String cfnaldPpsta;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "RDOCTO_PRODT_PREVD")
    private String rdoctoProdtPrevd;

    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "ECMNHO_DOCTO_PREVD")
    private String ecmnhoDoctoPreve;

    public String getCfrmulPpstaPrevd() {
        return cfrmulPpstaPrevd;
    }

    public void setCfrmulPpstaPrevd(String cfrmulPpstaPrevd) {
        this.cfrmulPpstaPrevd = cfrmulPpstaPrevd;
    }

    public String getCfnaldPpsta() {
        return cfnaldPpsta;
    }

    public void setCfnaldPpsta(String cfnaldPpsta) {
        this.cfnaldPpsta = cfnaldPpsta;
    }

    public String getRdoctoProdtPrevd() {
        return rdoctoProdtPrevd;
    }

    public void setRdoctoProdtPrevd(String rdoctoProdtPrevd) {
        this.rdoctoProdtPrevd = rdoctoProdtPrevd;
    }

    public String getEcmnhoDoctoPreve() {
        return ecmnhoDoctoPreve;
    }

    public void setEcmnhoDoctoPreve(String ecmnhoDoctoPreve) {
        this.ecmnhoDoctoPreve = ecmnhoDoctoPreve;
    }
}
