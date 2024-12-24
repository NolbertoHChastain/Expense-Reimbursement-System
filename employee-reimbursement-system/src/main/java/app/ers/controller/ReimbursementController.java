package app.ers.controller;

import app.ers.model.DTO.IncomingReimbursementDTO;
import app.ers.model.DTO.OutgoingReimbursementDTO;
import app.ers.model.Reimbursement;

import app.ers.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(value = "http://localhost:5173")
public class ReimbursementController {

    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    } // constructor injection

    @PostMapping
    public ResponseEntity<OutgoingReimbursementDTO> createReimbursement(
            @RequestBody IncomingReimbursementDTO reimbursementDTO) {
        return ResponseEntity.ok(reimbursementService.createReimbursement(reimbursementDTO));
    }

    /**
     * Get list of all {@code Reimbursement} records for given {@code userId}.
     * @param userId
     * @return a {@code List<Reimbursement>} of all reimbursements for given {@code userId}
     */
    @GetMapping("/{userId}") // add 'users'/{userId}
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsByUser(@PathVariable int userId) {
        return ResponseEntity.ok().body(reimbursementService.getAllReimbursementsByUser(userId));
    } // jwt/session: will have userId - remove later

    @GetMapping("/{status}/{userId}") // add 'users'/{userId}
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsByStatus(
            @PathVariable String status,
            @PathVariable int userId) {
        return ResponseEntity.ok().body(reimbursementService.getAllReimbursementsByStatus(status, userId));
    } // jwt/session : will have userId - remove later

    @PatchMapping("/{reimbId}/users/{userId}") // users/{userId} will be removed for jwt/session
    public ResponseEntity<Reimbursement> updateReimbursementStatus(
            @PathVariable int reimbId,
            @PathVariable int userId,
            @RequestBody IncomingReimbursementDTO reimbursementDTO) {
        reimbursementDTO.setUserId(userId);
        return ResponseEntity.ok(reimbursementService.updateReimbursementStatus(reimbId, reimbursementDTO));
    }

    @DeleteMapping("/{reimbId}/users/{userId}")
    public ResponseEntity<Integer> deleteReimbursementByUser(@PathVariable int reimbId, @PathVariable int userId) {
        return ResponseEntity.ok(reimbursementService.deleteReimbursementByUser(reimbId, userId));
    }

}