package com.companyname.gasbookingsystem.bank;

import com.companyname.gasbookingsystem.bank.dto.CreateBankDTO;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BankServiceImpl implements BankService{
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {

        this.bankRepository = bankRepository;
    }

    @Override
    public Bank createAccount(CreateBankDTO createBankDTO)  throws  InvalidPasswordException{
        String password=createBankDTO.getPassword();
        passwordValidator(password);
        Bank bank=Bank.builder().username(createBankDTO.getUsername()).password(createBankDTO.getPassword()).balance(createBankDTO.getBalance()).build();
        this.bankRepository.save(bank);
        return bank;
    }
    public void passwordValidator(String password) throws InvalidPasswordException {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new InvalidPasswordException("Password should not contain any space.Password should contain at least one digit(0-9).Password length should be between 8 to 15 characters.Password should contain at least one lowercase letter(a-z).Password should contain at least one uppercase letter(A-Z).Password should contain at least one special character ( @, #, %, &, !, $, etc….).");
        }
    }
}
