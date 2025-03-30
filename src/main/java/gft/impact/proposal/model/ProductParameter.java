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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TPARM_PRODT_COMPL")
public class ProductParameter {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TPARM_PRODT_COMPL_seq_gen")
    @SequenceGenerator(name = "TPARM_PRODT_COMPL_seq_gen", sequenceName = "TPARM_PRODT_COMPL_SEQ", allocationSize = 1)
	private Integer id;
	
	@Column(name = "CPARM_PRODT_PREVD")
    private String productParameterPrefix;

    @Column(name = "CPARM_PRODT_COMPL")
    private String productParameterSuffix;

    @NotNull
    @Column(name = "CPARM_PRODT_12")
    private Integer situationCode;

    @NotBlank
    @Size(max = 50)
    @Column(name = "CUSUAR_INCL")
    private String createdByUser;

    @Column(name = "HINCL_REG")
    private LocalDateTime createdAt;
}
