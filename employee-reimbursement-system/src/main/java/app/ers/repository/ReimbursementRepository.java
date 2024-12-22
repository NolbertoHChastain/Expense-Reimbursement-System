package app.ers.repository;

import java.util.List;

import app.ers.model.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {

    public List<Reimbursement> findAllByUser_UserId(int userId);
}