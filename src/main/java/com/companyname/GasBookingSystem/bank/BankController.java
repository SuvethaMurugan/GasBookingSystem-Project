package com.companyname.GasBookingSystem.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    @Autowired
    private BankService bankService;
    @PatchMapping("/bank")
    public Bank patchMapping(@RequestBody Bank bank){
        return this.bankService.patchMapping(bank);
    }
}
