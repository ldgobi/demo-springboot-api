package gft.impact.proposal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import gft.impact.proposal.model.ProductParameter;

@Repository
public class ProductParameterRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductParameterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	public Optional<ProductParameter> findByPrefixSuffix(String parameterPrefix, String parameterSuffix) {
		String sql = "SELECT * FROM TPARM_PRODT_COMPL WHERE CPARM_PRODT_PREVD = ? and CPARM_PRODT_COMPL = ?";
        List<ProductParameter> result = jdbcTemplate.queryForList(sql, ProductParameter.class, parameterPrefix, parameterSuffix);
        return (result.isEmpty()) ? Optional.empty() : Optional.of(result.get(0));
	}

}
