package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("FROM Account WHERE username=:username AND password=:password")
    public Account login(@Param("username") String username, @Param("password") String password);

    @Query("FROM Account WHERE username=:username")
    public Optional<Account> findByUsername(@Param("username") String username);

}
// @Query("FROM Message WHERE messageId =:messageId")
// public Message getMessageById(@Param("messageId") int messageId);

/*
 * SELECT accountId
 * FROM Account 
 * INNER JOIN Message
 * ON Account.accountId = Message.postedBy
 * 
 * @Query("FROM ReleaseDateType AS rdt LEFT JOIN rdt.cacheMedias AS cm WHERE cm.id = ?1") 
 * @Query("SELECT username FROM Account LEFT JOIN Message ON Account.accountId = Message.postedBy WHERE postedBy=:postedBy")
 */