package br.com.bookon.loan.controllers;

import br.com.bookon.loan.enums.LoanStatusEnum;
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
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/me/propose/search")
    public ResponseEntity <List<LoanResponse>> getLoanBy(@RequestParam Integer borrowerId) {
        List<LoanResponse> loans = loanService.getLoanByBorrowerId(borrowerId);
        if (loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping()
    public void teste(){
        loanService.getLoanByBorrowerId(1);
        System.out.println("tttt");
    }

    @PostMapping("/propose/create")
    public ResponseEntity<LoanResponse> createPropose(@RequestBody @Valid LoanRequest loanRequest, @RequestParam Integer borrowerId) {
        var createdLoan = loanService.createPropose(loanRequest, borrowerId);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    @GetMapping("propose/search")
    public ResponseEntity<List<LoanResponse>> listPropose(@RequestParam Long lenderId) {
        List<LoanResponse> loans = loanService.listProposes(lenderId);
        if (loans.isEmpty()) {
            return new ResponseEntity<>(loans, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @PutMapping("/propose/change")
    public ResponseEntity<Void> changePropose(@RequestParam String loanId, @RequestParam Integer lenderId, @RequestParam LoanStatusEnum loanStatusEnum) {
        loanService.changePropose(loanId, lenderId, loanStatusEnum);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}