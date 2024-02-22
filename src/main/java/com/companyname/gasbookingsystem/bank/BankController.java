package com.companyname.gasbookingsystem.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PatchMapping("/login/bank")
    public Bank patchMapping(@RequestBody Bank bank){
        return this.bankService.patchMapping(bank);
    }
}
