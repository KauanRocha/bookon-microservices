package br.com.bookon.loan.services;

import br.com.bookon.loan.enums.LoanStatusEnum;
import br.com.bookon.loan.models.Loan;
import br.com.bookon.loan.payloads.mappers.LoanMapper;
import br.com.bookon.loan.payloads.request.LoanRequest;
import br.com.bookon.loan.payloads.response.BookResponse;
import br.com.bookon.loan.payloads.response.LoanResponse;
import br.com.bookon.loan.payloads.response.UserResponse;
import br.com.bookon.loan.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private WebClient webClient;

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<LoanResponse> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream()
                .map(loanMapper::convertToLoanResponse)
                .collect(Collectors.toList());
    }

    public LoanResponse getLoanById(String id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        return loanMapper.convertToLoanResponse(loan);
    }

    public boolean deleteLoan(String id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public LoanResponse createPropose(LoanRequest loanRequest, Integer borrowerId) {
        UserResponse borrower = webClient.get().uri("http://localhost:/8082/api/users").retrieve().bodyToMono(UserResponse.class).block();
        UserResponse lender = webClient.get().uri("http://localhost:/8082/api/users").retrieve().bodyToMono(UserResponse.class).block();

        BookResponse book = webClient.get().uri("http://localhost:/8081/api/books/{}").retrieve().bodyToMono(BookResponse.class).block();

        var loan = new Loan();
        loan.setBorrower(borrower);
        loan.setLender(lender);
        loan.setBook(book);
        loan.setReturnDate(null);
        loan.setStatus(LoanStatusEnum.PENDING);
        return loanMapper.convertToLoanResponse(save(loan));
    }

    public List<LoanResponse> listProposes(Long lenderId) {
        List<Loan> loans = loanRepository.findByLenderIdAndStatus(lenderId, LoanStatusEnum.PENDING);

        return loans.stream()
                .map(loanMapper::convertToLoanResponse)
                .collect(Collectors.toList());
    }

    public void approvePropose(String loanId, Long lenderUserId) {
        if (loanNotExists(loanId,lenderUserId)) {
            return;
        }
        updateLoanStatusForApproved(loanId);
    }

    private boolean loanNotExists(String loanId, Long lenderUserId) {
        return !loanRepository.existsByIdAndLenderId(loanId, lenderUserId);
    }

    private void updateLoanStatusForApproved(String loanId) {
        Query query = new Query(Criteria.where("_id").is(loanId));
        Update update = new Update().set("status", LoanStatusEnum.APPROVED);
        mongoTemplate.updateFirst(query, update, Loan.class);
    }
}