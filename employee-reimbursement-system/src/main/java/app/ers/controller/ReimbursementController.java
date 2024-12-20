package app.ers.controller;

import app.ers.model.Reimbursement;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(value = "https://hoppscotch.io")
public class ReimbursementController {

    @PostMapping
    public ResponseEntity<Reimbursement> createReimbursement(@RequestBody Reimbursement reimbursement) {
        return ResponseEntity.ok(reimbursement);
    }
}
