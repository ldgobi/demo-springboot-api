package gft.impact.proposal.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProposalRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProposalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsById(Long proposalId) {
        String sql = "SELECT COUNT(*) FROM TFRMUL_BRANC_PREVD WHERE CFRMUL_PPSTA_PREVD = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, proposalId);
        return count != null && count > 0;
    }

    public void deleteFromTfrmulBrancPrevd(Long proposalId) {
        String sql = "DELETE FROM TFRMUL_BRANC_PREVD WHERE CFRMUL_PPSTA_PREVD = ?";
        jdbcTemplate.update(sql, proposalId);
    }

    public void deleteFromTfrmulCpoObrig(Long proposalId) {
        String sql = "DELETE FROM TFRMUL_CPO_OBRIG WHERE CFRMUL_PPSTA_PREVD = ?";
        jdbcTemplate.update(sql, proposalId);
    }

    public void deleteFromTfrmulProdtBranc(Long proposalId) {
        String sql = "DELETE FROM TFRMUL_PRODT_BRANC WHERE CFRMUL_PPSTA_PREVD = ?";
        jdbcTemplate.update(sql, proposalId);
    }

    public void deleteFromTparmProdtCompl(Long proposalId) {
        String sql = "DELETE FROM TPARM_PRODT_COMPL WHERE CPARM_PRODT_PREVD = 'FORMU' AND CPARM_PRODT_COMPL = ?";
        jdbcTemplate.update(sql, String.format("%05d", proposalId));
    }
}