package app.ers.repository;

import java.util.List;

import app.ers.model.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {

    public List<Reimbursement> findAllByUser_UserId(int userId);

    //public List<Reimbursement> findAllByUser_UserIdAndStatusIgnoreCase(int userId, String status);
    public List<Reimbursement> findAllByUser_UserIdAndStatus(int userId, String status);

    //public List<Reimbursement> findAllByStatusIgnoreCase(String status);
    public List<Reimbursement> findAllByStatus(String status);

}