package br.com.bookon.loan.controllers;

import br.com.bookon.loan.payloads.request.LoanRequest;
import br.com.bookon.loan.payloads.response.LoanResponse;
import br.com.bookon.loan.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/search")
    public ResponseEntity<LoanResponse> getLoanById(@RequestParam String id) {
        var loan = loanService.getLoanById(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLoan(@RequestParam String id) {
        boolean deleted = loanService.deleteLoan(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/propose/create")
    public ResponseEntity<LoanResponse> createPropose(@RequestBody @Valid LoanRequest loanRequest, @RequestParam Integer borrowerId) {
        var createdLoan = loanService.createPropose(loanRequest, borrowerId);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @GetMapping("propose/search")
    public ResponseEntity<List<LoanResponse>> listPropose(@RequestParam Long lenderId) {
        List<LoanResponse> loans = loanService.listProposes(lenderId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @PutMapping("/propose/approve")
    public ResponseEntity<Void> approvePropose(@RequestParam String loanId,@RequestParam Long lenderUserId) {
        loanService.approvePropose(loanId, lenderUserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}