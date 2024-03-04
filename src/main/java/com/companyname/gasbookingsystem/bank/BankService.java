package com.companyname.gasbookingsystem.bank;

import com.companyname.gasbookingsystem.bank.dto.CreateBankDTO;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;

public interface BankService {


    Bank createAccount(CreateBankDTO createBankDTO) throws InvalidPasswordException;
}
