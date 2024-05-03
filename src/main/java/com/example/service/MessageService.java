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

    @Autowired
    public List<Message> getAllMessages(){
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    public Message createMessageText(Message messageText){
        return messageRepository.save(messageText);
    }

    public Message updateMessageText(int messageId, Message messageText){
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message existingMessage = messageOptional.get();
            if(existingMessage == null || messageText.getMessageText().length() > 0 || messageText.getMessageText().length() < 255){
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
