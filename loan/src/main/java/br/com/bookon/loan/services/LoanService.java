package br.com.bookon.loan.services;

import br.com.bookon.loan.enums.LoanStatusEnum;
import br.com.bookon.loan.exceptions.LoanNotFoundException;
import br.com.bookon.loan.models.Loan;
import br.com.bookon.loan.payloads.request.LoanRequest;
import br.com.bookon.loan.payloads.response.LoanResponse;
import br.com.bookon.loan.repositories.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public LoanService(LoanRepository loanRepository, WebClient.Builder webClientBuilder) {
        this.loanRepository = loanRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<LoanResponse> getLoanByBorrowerId(Integer borrowerId) {
        List<Loan> loans = loanRepository.findByBorrowerIdAndStatus(borrowerId, LoanStatusEnum.PENDING);
        return loans.stream()
                .map(this::convertToLoanResponse)
                .toList();
    }

    public LoanResponse createPropose(LoanRequest loanRequest, Integer borrowerId) {
        boolean borrowerExist = Boolean.TRUE.equals(webClientBuilder.build().get().uri("http://user/api/users").retrieve().bodyToMono(boolean.class).block());
        boolean lenderExist = Boolean.TRUE.equals(webClientBuilder.build().get().uri("http://user/api/users").retrieve().bodyToMono(boolean.class).block());
        boolean bookExist = Boolean.TRUE.equals(webClientBuilder.build().get().uri("http://book/api/books").retrieve().bodyToMono(boolean.class).block());

        var loan = new Loan();
        BeanUtils.copyProperties(loanRequest, loan);
        return convertToLoanResponse(save(loan));
    }

    public List<LoanResponse> listProposes(Long lenderId) {
        List<Loan> loans = loanRepository.findByLenderIdAndStatus(lenderId, LoanStatusEnum.PENDING);
        return loans.stream()
                .map(this::convertToLoanResponse)
                .toList();
    }

    public void changePropose(String loanId, Integer lenderId,LoanStatusEnum loanStatusEnum) {
        Loan existLoan = loanRepository.findByIdAndLenderId(loanId, lenderId).orElseThrow(LoanNotFoundException::new);
        existLoan.setStatus(loanStatusEnum);
        save(existLoan);
    }

    private LoanResponse convertToLoanResponse(Loan loan) {
        var loanResponse = new LoanResponse();
        BeanUtils.copyProperties(loan, loanResponse);
        return loanResponse;
    }

}