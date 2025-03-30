package gft.impact.proposal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TPARM_PRODT_COMPL")
public class ProductComplement {

    @NotNull
    @Size(min = 1, max = 5)
    @Id
    @Column(name = "CPARM_PRODT_PREVD")
    private String cparmProdtPrevd;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CPARM_PRODT_COMPL")
    private String cparmProdtCompl;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CPARM_PRODT_12")
    private String cparmProdt12;

    public String getCparmProdtPrevd() {
        return cparmProdtPrevd;
    }

    public void setCparmProdtPrevd(String cparmProdtPrevd) {
        this.cparmProdtPrevd = cparmProdtPrevd;
    }

    public String getCparmProdtCompl() {
        return cparmProdtCompl;
    }

    public void setCparmProdtCompl(String cparmProdtCompl) {
        this.cparmProdtCompl = cparmProdtCompl;
    }

    public String getCparmProdt12() {
        return cparmProdt12;
    }

    public void setCparmProdt12(String cparmProdt12) {
        this.cparmProdt12 = cparmProdt12;
    }
}
