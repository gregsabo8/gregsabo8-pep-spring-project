package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //create user
    public ResponseEntity createUser(@RequestBody Account account) {
        if(accountService.creatAccount(account) != null&&account.getAccountId()!=null){
            return ResponseEntity.status(200).body(accountService.creatAccount(account));
        }else{
            //409 duplicate user
            return ResponseEntity.status(409).body("duplicate username");
        }
    }

    // login
    @PostMapping(value="/login")
    public Account login(@RequestBody Account account){
        
        return accountService.login(account.getUsername(),account.getPassword());
    }

    //create message
    @PostMapping(value="/messages")
    public ResponseEntity postMessage(@RequestBody Message message) /*throws InvalidMessageException*/{
        if(messageService.createMessage(message) != null&&message.getMessageText().length()<255&&message.getMessageText()!=""){
            return ResponseEntity.status(200).body(messageService.createMessage(message));
        }else{
            // throw new InvalidMessageException();
            //return 400 for improper message
            return ResponseEntity.status(400).body("invalid message");
        }
        
    }

    //get all messages
    @GetMapping("/messages")
    public List<Message> getAllMessagesHandler(){
        return messageService.getAllMessages();
    }

    //get message by message id
    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable int messageId){
        return messageService.getMessageByIdService(messageId);
    }

    //delete message by message id
    @DeleteMapping("/messages/{messageId}")
    public void deleteMessageById(@PathVariable int messageId){
        messageService.deleteMessage(messageId);
        //need to return number of rows affected
    }

    //update message by message id
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessageByMessageId(@PathVariable int messageId, @RequestBody Message message){
        Message updatedMessage = messageService.updateMessage(messageId,message);
        if(updatedMessage==null||updatedMessage.getMessageText().length()>=255||updatedMessage.getMessageText()==""){
            //status 400
            return ResponseEntity.status(400).body("400");
        }
        else{
            //good
            
            return ResponseEntity.status(200).body(updatedMessage);
        }
    }

    //get messages by account id
    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesForUser(@PathVariable int accountId){
        
        return messageService.getMessagesForUser(accountId);
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
