package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {

    MessageService messageService;
    AccountService accountService;
    
    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService =accountService;
    }

    //create new account
    @PostMapping(value = "/register")
    public Account registerAccount(@RequestBody Account account){
        return accountService.creatAccount(account);
    }

    // login
    @PostMapping(value="/login")
    public Account login(@RequestBody Account account){
        return account;
    }

    //create message
    @PostMapping(value="/messages")
    public Message postMessage(@RequestBody Message message) /*throws InvalidMessageException*/{
        if(messageService.createMessage(message) != null){
            return messageService.createMessage(message);
        }else{
            // throw new InvalidMessageException();
            //return 400 for improper message
            return null;
        }
        
    }

    //get all messages
    @GetMapping("/messages")
    public List<Message> getAllMessagesHandler(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable int messageId){
        return messageService.getMessageByIdService(messageId);
    }

    @DeleteMapping("/messages/{messageId}")
    public void deleteMessageById(@PathVariable int messageId){
        messageService.deleteMessage(messageId);
        //need to return number of rows affected
    }

    @PatchMapping("/messages/{messageId}")
    public Message updateMessageByMessageId(@PathVariable int id){
        Message message = new Message();
        return message;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesForUser(@PathVariable int accountId){
        
        return messageService.getMessagesForUser(accountId);
    }

    //create user
    public Account createUser(@RequestBody Account account) {
        if(accountService.creatAccount(account) != null){
            return accountService.creatAccount(account);
        }else{
            //409 duplicate user
            return null;
        }
    }

}

// @GetMapping("/example401")
// public ResponseEntity example(){
//     return ResponseEntity.status(401).body("Unauthorized resource!");
// }

// @GetMapping("/exampleHeaders")
// public ResponseEntity headers(@RequestBody Sample sample){
//     return ResponseEntity.status(200).header("content-type", "application/zip").body(sample);
// }
