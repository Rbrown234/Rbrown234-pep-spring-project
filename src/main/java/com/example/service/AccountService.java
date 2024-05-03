package com.example.service;


//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Component
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) {
        Account addedAccount = account;

        if(addedAccount == null || addedAccount.getPassword().length() < 4 || addedAccount.getUsername().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        else{
            return accountRepository.save(addedAccount);
        }
        
    }

}
