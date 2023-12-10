package br.com.bookon.loan.repositories;

import br.com.bookon.loan.enums.LoanStatusEnum;
import br.com.bookon.loan.models.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {

    List<Loan> findByLenderIdAndStatus(Long lenderId, LoanStatusEnum status);

    Boolean existsByIdAndLenderId(String id, Long lenderId);
}