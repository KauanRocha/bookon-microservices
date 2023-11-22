package br.com.bookon.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.com.bookon.server.enumerations.LoanStatusEnum;
import br.com.bookon.server.exceptions.BookNotFoundException;
import br.com.bookon.server.exceptions.LoanNotFoundException;
import br.com.bookon.server.exceptions.UserNotFoundException;
import br.com.bookon.server.models.mongo.BookMongo;
import br.com.bookon.server.models.mongo.Loan;
import br.com.bookon.server.models.mongo.UserMongo;
import br.com.bookon.server.models.postgres.Book;
import br.com.bookon.server.models.postgres.User;
import br.com.bookon.server.payload.request.mongo.LoanRequest;
import br.com.bookon.server.payload.response.mongo.LoanResponse;
//import br.com.bookon.server.producer.EmailProducer;
import br.com.bookon.server.repositories.mongo.LoanRepository;
import br.com.bookon.server.repositories.postgres.BookRepository;
import br.com.bookon.server.repositories.postgres.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    private EmailProducer emailProducer;

    public Loan save(Loan loan) {
        //emailProducer.sendMessage(loan.getLenderUser().getEmail());
    	return loanRepository.save(loan);
    }

    public List<LoanResponse> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        List<LoanResponse> loanResponses = new ArrayList<>();

        for (Loan loan : loans) {
            var loanResponse = new LoanResponse(loan);
            loanResponses.add(loanResponse);
        }

        return loanResponses;
    }

    public LoanResponse getLoanById(String id) {
        Loan loan = loanRepository.findById(id).orElse(null);
        return new LoanResponse(loan);
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
        User borrowerPostgres = userRepository.findById(borrowerId)
        		.orElseThrow(() -> new UserNotFoundException());

        User lenderPostgres = userRepository.findById(loanRequest.getLenderId())
        		.orElseThrow(() -> new UserNotFoundException());

        Book bookPostgres = bookRepository.findById(loanRequest.getBookId())
        		.orElseThrow(() -> new BookNotFoundException());

        var loan = new Loan();
        loan.setBorrowerUser(new UserMongo(borrowerPostgres));
        loan.setLenderUser(new UserMongo(lenderPostgres));
        loan.setBook(new BookMongo(bookPostgres));
        loan.setReturnDate(null);
        loan.setStatus(LoanStatusEnum.PENDING);
        return new LoanResponse(save(loan));

    }

    public List<LoanResponse> listProposes(Integer lenderId) {
        List<Loan> loans = loanRepository.findByLenderUserIdAndStatus(lenderId, LoanStatusEnum.PENDING);

        return loans.stream()
                .map(LoanResponse::new)
                .collect(Collectors.toList());
    }

    public void approvePropose(String loanId, Integer lenderUserId) {
    	if (loanNotExists(loanId,lenderUserId)) {
    		throw new LoanNotFoundException();
    	}
    	updateLoanStatusForApproved(loanId);
    }

    private boolean loanNotExists(String loanId, Integer lenderUserId) {
    	return !loanRepository.existsByIdAndLenderUserId(loanId, lenderUserId);
    }

    private void updateLoanStatusForApproved(String loanId) {
    	Query query = new Query(Criteria.where("_id").is(loanId));
   	 	Update update = new Update().set("status", LoanStatusEnum.APPROVED);

        mongoTemplate.updateFirst(query, update, Loan.class);
    }
}
