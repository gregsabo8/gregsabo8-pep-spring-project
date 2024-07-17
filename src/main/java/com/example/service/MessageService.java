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
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    //get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    //get message by id
    public Message getMessageByIdService(int messageId) {
        // long lid = (long) message_id;
        // Optional<Message> opMessage=messageRepository.findById(lid);
        if(messageRepository.getMessageById(messageId) != null){
            return messageRepository.getMessageById(messageId);
        }else{
            return null;
        }
    }

    //create message
    public Message createMessage(Message message){
        if(message.getMessageText().length()<255&&message.getMessageText()!=""){
            return messageRepository.save(message);
        }else{
            return null;
        }
        
    }

    //get messages for user
    public List<Message> getMessagesForUser(int postedBy){
        return messageRepository.getMessagesForUser(postedBy);
        
    }


    //update message by id
    public Message updateMessage(int messageId, Message message){
        Message updatedMessage = messageRepository.getMessageById(messageId);

        if(updatedMessage!=null&&updatedMessage.getMessageText().length()<=255){
            updatedMessage.setMessageText(message.getMessageText());
            return updatedMessage;
        }
        else{
            return null;
        }
        
    }
    //delete message by id
    public void deleteMessage(int messageId){
        messageRepository.deleteById(messageId);
    }
}
