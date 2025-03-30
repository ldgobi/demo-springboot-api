package gft.impact.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gft.impact.proposal.model.ProductProposal;

@Repository
public interface ProductProposalRepository extends JpaRepository<ProductProposal, String> {
}
