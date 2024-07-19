package com.example.service;

import java.util.List;
import java.util.Optional;

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
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if(existingAccount.isPresent()){
            return null;
        }
        else{
            return accountRepository.save(account);
        }
        
    }
    //login
    public Account login(Account account){
        List<Account> allAccounts = accountRepository.findAll();
        
        for(Account acc : allAccounts){
            boolean matchingUsername=acc.getUsername().compareTo(account.getUsername())==0;
            boolean mathcingPassword=acc.getPassword().compareTo(account.getPassword())==0;
            if(matchingUsername&&mathcingPassword)return acc;
        }
        return null;
        
    }

}
