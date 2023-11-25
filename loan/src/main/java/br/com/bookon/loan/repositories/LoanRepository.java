package br.com.bookon.loan.repositories;



import br.com.bookon.loan.models.Loan;
import br.com.bookon.loan.enumerations.LoanStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {

    List<Loan> findByLenderUserIdAndStatus(Integer lenderId, LoanStatusEnum status);

    Boolean  existsByIdAndLenderUserId(String id, Integer lenderUserId);

}
