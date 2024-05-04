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
        //Account addedAccount = account;


        if(accountRepository.findByUsername(account.getUsername()) != null ){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        else if(account.getPassword().length() < 4 || account.getUsername().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        

        else{
            return accountRepository.save(account);
        }
        
    }

    public Account userLogin(Account account){

        Account userLoginAcc = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());

        if(userLoginAcc == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }


        else{
            return userLoginAcc;
            
        }
    }

}
