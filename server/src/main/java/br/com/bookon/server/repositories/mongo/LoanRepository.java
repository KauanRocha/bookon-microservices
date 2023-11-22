package br.com.bookon.server.repositories.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.bookon.server.enumerations.LoanStatusEnum;
import br.com.bookon.server.models.mongo.Loan;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String>{
	
    List<Loan> findByLenderUserIdAndStatus(Integer lenderId, LoanStatusEnum status);
    
    Boolean  existsByIdAndLenderUserId(String id, Integer lenderUserId);
}
