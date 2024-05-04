package com.example.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Component
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    
    public List<Message> getAllMessages(){
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    public Message createMessageText(Message message){
        //Message getMessage = messageRepository.getById(message.getMessageId());

        if(message.getPostedBy() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(message.getMessageText().length() <= 0 || message.getPostedBy() != null || message.getMessageText().length() > 255){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        messageRepository.save(message);
        return message;
    }

    
    public Message getMessageById(int messageId){
        Optional<Message> getMessage = messageRepository.findById(messageId);
        Message newMessage;
        if(getMessage.isPresent()){
            newMessage = getMessage.get();
            return newMessage;
        }

        return null;
        
        

    }

    public Message deleteMessageText(int messageId, Message message) {
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message existingMessage = messageOptional.get();
            if(existingMessage == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            existingMessage.setMessageText(message.getMessageText());
            messageRepository.delete(existingMessage);
            return existingMessage;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    
    }


    public Message updateMessageText(int messageId, Message messageText){
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message existingMessage = messageOptional.get();
            if(existingMessage == null || messageText.getMessageText().length() <= 0 || messageText.getMessageText().length() > 255){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            existingMessage.setMessageText(messageText.getMessageText());
            messageRepository.save(existingMessage);
            return existingMessage;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
