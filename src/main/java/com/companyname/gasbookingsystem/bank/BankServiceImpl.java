package com.companyname.gasbookingsystem.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService{
    @Autowired
    private BankRepository bankRepository;
    @Override
    public Bank patchMapping(Bank bank) {
        return this.bankRepository.save(bank);
    }
}
