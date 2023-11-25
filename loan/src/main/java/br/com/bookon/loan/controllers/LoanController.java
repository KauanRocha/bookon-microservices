package br.com.bookon.loan.controllers;

import br.com.bookon.loan.payloads.requests.LoanRequest;
import br.com.bookon.loan.payloads.responses.LoanResponse;
import br.com.bookon.loan.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.bookon.server.annotations.UserId;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable String id) {
    	var loan = loanService.getLoanById(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable String id) {
        boolean deleted = loanService.deleteLoan(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<LoanResponse> createPropose(@RequestBody @Valid LoanRequest loanRequest, @UserId Integer borrowerId) {
    	var createdLoan = loanService.createPropose(loanRequest, borrowerId);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }
    
    @GetMapping("search")
    public ResponseEntity<List<LoanResponse>> listPropose(@UserId Integer lenderId) {
    	List<LoanResponse> loans = loanService.listProposes(lenderId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    @PutMapping("/{loanId}/approve")
    public ResponseEntity<Void> approvePropose(@PathVariable String loanId, @UserId Integer lenderUserId) {
    	loanService.approvePropose(loanId, lenderUserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}