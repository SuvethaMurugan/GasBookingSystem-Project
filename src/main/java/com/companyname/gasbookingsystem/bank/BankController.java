package com.companyname.gasbookingsystem.bank;

import com.companyname.gasbookingsystem.bank.dto.CreateBankDTO;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/login/bank")
    public Bank bankAccount(@RequestBody CreateBankDTO createBankDTO) throws InvalidPasswordException {

        return this.bankService.createAccount(createBankDTO);
    }
}
