package com.companyname.gasbookingsystem.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService{
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank patchMapping(Bank bank) {
        return this.bankRepository.save(bank);
    }
}
