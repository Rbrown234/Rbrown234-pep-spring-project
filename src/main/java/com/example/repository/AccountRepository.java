package com.example.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{

    
    Account findByUsername(String username);

    Account findByPassword(String password);
    
    
}
