package gft.impact.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.impact.proposal.model.FormProduct;

@Repository
public interface FormProductRepository extends JpaRepository<FormProduct, Long> {
}
