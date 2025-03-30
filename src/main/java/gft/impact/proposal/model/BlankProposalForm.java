package gft.impact.proposal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TFRMUL_BRANC_PREVD")
public class BlankProposalForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tfrmul_branc_prevd_seq_gen")
    @SequenceGenerator(name = "tfrmul_branc_prevd_seq_gen", sequenceName = "TFRMUL_BRANC_PREVD_SEQ", allocationSize = 1)
    @Column(name = "CFRMUL_PPSTA_PREVD")
    private Long formNumber;

    @NotNull
    @Positive
    @Column(name = "CFNALD_PPSTA")
    private Integer proposalTypeCode;

    @NotBlank
    @Size(max = 255)
    @Column(name = "RDOCTO_PRODT_PREVD")
    private String productName;

    @Column(name = "ECMNHO_DOCTO_PREVD")
    private String documentPath;

    @NotBlank
    @Size(max = 50)
    @Column(name = "CUSUAR_SENHA_INCL")
    private String createdByUser;

    @Column(name = "HINCL_REG")
    private LocalDateTime createdAt;
}
