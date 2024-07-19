package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
//@RequestMapping
public class SocialMediaController {

    MessageService messageService;
    AccountService accountService;
    
    public SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService =accountService;
    }

    //create new account
    @PostMapping(value = "/register")
    public ResponseEntity registerAccount(@RequestBody Account account){    
        if(accountService.creatAccount(account)==null){
            return ResponseEntity.status(409).body("");
        }else{
            return ResponseEntity.status(200).body(accountService.creatAccount(account));
        }
    }


    // login
    @PostMapping(value="/login")
    public ResponseEntity login(@RequestBody Account account) throws JsonMappingException, JsonProcessingException{
        Account userLoggedIn = accountService.login(account);
        if(userLoggedIn==null){
            return ResponseEntity.status(401).body("invalid username/pass");
        }
        else{
            return ResponseEntity.status(200).body(userLoggedIn);
        }
    }

    //create message
    @PostMapping(value="/messages")
    public ResponseEntity postMessage(@RequestBody Message message) {
        //todo 3
        //the message needs a accountID
        if(messageService.createMessage(message) != null&&message.getMessageText()!=""){
            return ResponseEntity.status(200).body(messageService.createMessage(message));
        }else{
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
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        if(messageService.deleteMessage(messageId)==0){
            return ResponseEntity.status(200).body("");
            
        }else{
            return ResponseEntity.status(200).body(1);
        }
        
    }

    //update message by message id
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessageByMessageId(@PathVariable int messageId, @RequestBody Message message){
        Message updatedMessage = messageService.getMessageByIdService(messageId);
        if(updatedMessage==null||message.getMessageText()==""||message.getMessageText().length()>255){
            return ResponseEntity.status(400).body("400");
        }
        else{
            return ResponseEntity.status(200).body(1);
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
