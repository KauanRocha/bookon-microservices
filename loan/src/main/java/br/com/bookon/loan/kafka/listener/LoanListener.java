package br.com.bookon.loan.kafka.listener;

import br.com.bookon.loan.payloads.request.LoanRequest;
import br.com.bookon.loan.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.logging.Logger;

public class LoanListener {

    private final LoanService loanService;

    @Autowired
    public LoanListener(LoanService loanService) {
        this.loanService = loanService;
    }

    @KafkaListener(topics = "loan-topic", groupId = "group")
    public void process(LoanRequest loanRequest) {
        loanService.createProposeV2(loanRequest);
    }
}
