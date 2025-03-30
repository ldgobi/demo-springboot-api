package gft.impact.proposal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "TFRMUL_PRODT_BRANC")
public class FormProduct {

    @Id
    @Column(name = "CFRMUL_PPSTA_PREVD")
    private Long formNumber;

    @NotNull
    @Column(name = "CPRODT_NEGOC")
    private Integer commercialProductCode;
}
