package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;




/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    AccountService accountService;
    MessageService messageService;




    
    @PostMapping("/register")
    public ResponseEntity<?> userRegisterHandler(@RequestBody Account account){
        
        Account addedAccount = accountService.registerAccount(account);

        if(addedAccount != null){
            
            return ResponseEntity.status(200).body(addedAccount);
        }

        else{
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        
        
    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Account userLoginHandler(@RequestBody Account account){
        
        Account addedAccount = accountService.userLogin(account);
        
            return addedAccount;
        

        
        
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createNewMessageHandler(@RequestBody Message message){
        
        Message addedMessage = messageService.createMessageText(message);
        if(addedMessage != null){
            return ResponseEntity.status(200).body(addedMessage);
        }

        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            
            
        }       
        //throw new ResponseStatusException(HttpStatus.OK);
        

        
        
    }

    @GetMapping(path = "messages")
    @ResponseBody
    public ResponseEntity<?> getAllMessagesHandler(){
        
       
        List<Message> messages = messageService.getAllMessages();
        
        return ResponseEntity.status(200).body(messages);
        

            
        
        
    }

    @GetMapping(path = "messages/{messageId}")
    @ResponseBody
    public Message getMessageById(@PathVariable Integer messageId){
        Message getMessage = messageService.getMessageById(messageId);
        
        return getMessage;
        

        
    }

    @DeleteMapping(path = "messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> deleteMessageHandler(@PathVariable Integer messageId, @RequestBody Message messageText){
        Message deletedMessage = messageService.deleteMessageText(messageId, messageText);
        if(deletedMessage != null){
            return ResponseEntity.status(200).body(1);
        }

        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "messages/{messageId}")
    @ResponseBody
    public ResponseEntity<Integer> updateMessageText(@PathVariable Integer messageId, @RequestBody Message messageText){
        Message updatedMessage = messageService.updateMessageText(messageId, messageText);
        if(updatedMessage != null){
            return ResponseEntity.status(200).body(1);
        }

        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "accounts/{account_id}/messages")
    @ResponseBody
    public ResponseEntity<Message> getAllMessagesforUserHandler(@PathVariable Integer accountId, @RequestBody Message messageText){
        
        return ResponseEntity.status(200).body(messageText);
    }

   


}
