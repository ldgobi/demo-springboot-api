package gft.impact.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gft.impact.proposal.model.BlankProposalForm;

@Repository
public interface BlankProposalFormRepository extends JpaRepository<BlankProposalForm, Long> {

    @Query("SELECT MAX(b.formNumber) FROM BlankProposalForm b")
    Long findMaxFormNumber();
}
