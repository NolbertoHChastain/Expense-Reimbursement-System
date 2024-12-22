package app.ers.service;

import java.util.List;
import java.util.Optional;

import app.ers.model.DTO.IncomingReimbursementDTO;
import app.ers.model.DTO.OutgoingReimbursementDTO;
import app.ers.model.User;
import app.ers.model.Reimbursement;
import app.ers.exception.RepositoryException;
import app.ers.exception.InvalidRegistrationException;
import app.ers.repository.UserRepository;
import app.ers.repository.ReimbursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReimbursementService {

    private final ReimbursementRepository reimbursementRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReimbursementService(ReimbursementRepository reimbursementRepository, UserRepository userRepository) {
        this.reimbursementRepository = reimbursementRepository;
        this.userRepository = userRepository;
    } // constructor injection

    public OutgoingReimbursementDTO createReimbursement(IncomingReimbursementDTO reimbursementDTO) {
        // 1. convert reimbursementDTO to Reimbursement model class
        Reimbursement reimbursement = new Reimbursement(0, reimbursementDTO.getDescription(),
                reimbursementDTO.getAmount(), "PENDING", null);

        // 2. verify reimbursement fields are valid
        if (validFields(reimbursement)) {
            // 3. retrieve user
            Optional<User> user = userRepository.findById(reimbursementDTO.getUserId());
            if (user.isPresent()) {
                reimbursement.setUser(user.get());
                // 4. write to DB and return Outgoing Reimbursement
                Reimbursement savedReimbursement = reimbursementRepository.save(reimbursement);

                OutgoingReimbursementDTO outgoingReimbursementDTO = new OutgoingReimbursementDTO();
                outgoingReimbursementDTO.setReimbId(savedReimbursement.getReimbId());
                outgoingReimbursementDTO.setAmount(savedReimbursement.getAmount());
                outgoingReimbursementDTO.setDescription(savedReimbursement.getDescription());
                outgoingReimbursementDTO.setStatus(savedReimbursement.getStatus());
                return outgoingReimbursementDTO;
            } else throw new RepositoryException("Invalid user");
        } else throw new InvalidRegistrationException("Invalid Reimbursement details");
    }

    public List<Reimbursement> getAllReimbursementsByUser(int userId) {
        // 1. ensure user exists
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // 2. determine employee || manager role
            if (user.get().getRole().equalsIgnoreCase("employee")) {
                List<Reimbursement> reimbursements =
                        reimbursementRepository.findAllByUser_UserId(user.get().getUserId());

                for (Reimbursement reimbursement : reimbursements) {
                    reimbursement.setUser(null);
                } // for each reimbursement remove user's details
                return reimbursements;
            } // if : employee
            else {
                List<Reimbursement> reimbursements = reimbursementRepository.findAll();

                for (Reimbursement reimbursement : reimbursements) {
                    reimbursement.getUser().setUsername(null);
                    reimbursement.getUser().setPassword(null);
                } // for each reimbursement remove user's username & password
                return reimbursementRepository.findAll();
            } // else : manager
        } else throw new RepositoryException("invalid account details");

    }

    public List<Reimbursement> getAllReimbursementsByStatus(String status, int userId) {
        // 1. ensure user exists
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // 2. determine employee || manager role
            if (user.get().getRole().equalsIgnoreCase("employee")) {
                List<Reimbursement> reimbursements =
                        reimbursementRepository.findAllByUser_UserIdAndStatus(userId, status.toUpperCase());

                for (Reimbursement reimbursement : reimbursements) {
                    reimbursement.setUser(null);
                } // for each reimbursement remove user's details
                return reimbursements;
            } // if : employee
            else {
                List<Reimbursement> reimbursements = reimbursementRepository.findAllByStatus(status.toUpperCase());

                for (Reimbursement reimbursement : reimbursements) {
                    reimbursement.getUser().setUsername(null);
                    reimbursement.getUser().setPassword(null);
                } // for each reimbursement remove user's username & password
                return reimbursements;
            } // else : manager
        } else throw new RepositoryException("invalid account details");

    }

    private boolean validFields(Reimbursement reimbursement) {
        return !(reimbursement.getDescription().isBlank() || reimbursement.getAmount() == 0
                || reimbursement.getStatus().isBlank());
    }
}