package gft.impact.proposal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//generated from SEAP6045 - Alteração
//notice the attribute names are different from ProductComplement generated previously from Inclusão

@Data
@Entity
@Table(name = "TPARM_PRODT_COMPL")
public class ProductComplement2 {

    @Id
    @NotNull
    @Size(min = 5, max = 5)
    @Column(name = "CPARM_PRODT_PREVD")
    private String productComplementId;

    @NotNull
    @Size(max = 20)
    @Column(name = "CPARM_PRODT_COMPL")
    private String complementCode;

    @NotNull
    @Size(max = 80)
    @Column(name = "RPARM_PRODT_COMPL")
    private String description;

    @NotNull
    @Size(max = 30)
    @Column(name = "RRSUMO_PARM_COMPL")
    private String summary;

    @NotNull
    @Size(max = 20)
    @Column(name = "CPARM_PRODT_1")
    private String parameter1;

    @NotNull
    @Size(max = 20)
    @Column(name = "CPARM_PRODT_2")
    private String parameter2;

    // ... (parameters 3 to 19)

    @NotNull
    @Size(max = 20)
    @Column(name = "CPARM_PRODT_20")
    private String parameter20;

    @NotNull
    @Size(max = 9)
    @Column(name = "CUSUAR_INCL")
    private String createdBy;

    @NotNull
    @Column(name = "HINCL_REG")
    private LocalDateTime createdAt;
}
