package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;
import com.example.entity.Message;

public interface AccountRepository extends JpaRepository<Account, Long>{

    
}
