package gft.impact.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.impact.proposal.model.ProductComplement;

@Repository
public interface ProductComplementRepository extends JpaRepository<ProductComplement, String> {
    ProductComplement findByCparmProdtPrevdAndCparmProdtCompl(String cparmProdtPrevd, String cparmProdtCompl);
}
