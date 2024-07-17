package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("FROM Account WHERE username=:username AND password=:password")
    public Account login(@Param("username") String username, @Param("password") String password);

    
}
