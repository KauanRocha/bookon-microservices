package br.com.bookon.loan.payloads.mappers;

import br.com.bookon.loan.models.Loan;
import br.com.bookon.loan.payloads.response.LoanResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    private final ModelMapper modelMapper;

    public LoanMapper() {
        this.modelMapper = new ModelMapper();
    }

    public LoanResponse convertToLoanResponse(Loan loan) {
        return modelMapper.map(loan, LoanResponse.class);
    }
}

