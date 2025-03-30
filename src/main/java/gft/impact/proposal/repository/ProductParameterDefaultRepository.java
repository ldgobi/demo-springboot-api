package gft.impact.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.impact.proposal.model.ProductParameter;

@Repository
public interface ProductParameterDefaultRepository extends JpaRepository<ProductParameter, String> {
}
