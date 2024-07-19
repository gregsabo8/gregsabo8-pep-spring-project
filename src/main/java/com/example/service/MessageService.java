package com.example.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Transactional(rollbackFor = InvalidMessageException.class)
@Service
public class MessageService {
    AccountRepository accountRepository;
    MessageRepository messageRepository;
    @Autowired
    public MessageService(AccountRepository accountRepository,MessageRepository messageRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;

    }
    //get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    //get message by id
    public Message getMessageByIdService(int messageId) {
        if(messageRepository.getMessageById(messageId) != null){
            return messageRepository.getMessageById(messageId);
        }else{
            return null;
        }
    }

    //create message
    public Message createMessage(Message message){
        if(message.getMessageText().length()>255) return null;
        List<Message> getAllMessagesByAccountId = messageRepository.findAll();
        boolean isUserFound = false;
        for(Message m : getAllMessagesByAccountId){
            if(m.getPostedBy().compareTo(message.getPostedBy())==0) isUserFound = true;
        }
        if(!isUserFound){
            return null;
        }
        Message messageCreated = messageRepository.save(message);
        return messageCreated;
        //use account.findAll
    }

    //get messages for user
    public List<Message> getMessagesForUser(int postedBy){
        return messageRepository.getMessagesForUser(postedBy);
        
    }

    //update message by id
    public Message updateMessage(int messageId, Message message){
        
        Message updatedMessage = messageRepository.getMessageById(messageId);

        if(updatedMessage!=null&&updatedMessage.getMessageText().length()>255&&updatedMessage.getMessageText()!=""){
            updatedMessage.setMessageText(message.getMessageText());
            return updatedMessage;
        }
        else{
            return null;
        }
        
    }
    //delete message by id
    public int deleteMessage(int messageId){
        return messageRepository.deleteById(messageId);
    }
}
