package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.exception.InvalidAccountException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Transactional(rollbackFor = InvalidAccountException.class)
@Service
public class AccountService {
    AccountRepository accountRepository;
    MessageRepository messageRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository,MessageRepository messageRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;

    }
    //create account
        
    public Account creatAccount(Account account){
        if(accountRepository.save(account) != null){
            return accountRepository.save(account);
        }
        else{
            return null;
        }
        
    }
    //login
}
