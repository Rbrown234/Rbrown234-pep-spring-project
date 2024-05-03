package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.repository.AccountRepository;

import java.util.List;

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
    
    @PostMapping(path = "login")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Account userLoginHandler(@RequestBody Account account){
        
        if(account.getUsername() == null || account.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        return account;

        
        
    }

    @PostMapping(path = "messages")
    public @ResponseBody Message createNewMessageHandler(@RequestBody Message message){
        
        

        if(message.getMessageText() == "" || message.getPostedBy() == null || message.getMessageText().length() > 255){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        else if(message.getMessageId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Message addedMessage = messageService.createMessageText(message);
        return message;
        //throw new ResponseStatusException(HttpStatus.OK);
        

        
        
    }

    @GetMapping(path = "messages")
    @ResponseBody
    public ResponseEntity<?> getAllMessagesHandler(){
        
       
        List<Message> messages = messageService.getAllMessages();
        
        return ResponseEntity.status(200).body(messages);
        

            
        
        
    }

    //@GetMapping(path = "messages/{message_id}")
    //@ResponseStatus(HttpStatus.OK)
    //@ResponseBody

    //@DeleteMapping(path = "messages/{message_id}")
    //@ResponseStatus(HttpStatus.OK)
    //@ResponseBody

    @PatchMapping(path = "messages/{message_id}")
    @ResponseBody
    public ResponseEntity<?> updateMessageText(@PathVariable Integer messageId, @RequestBody Message messageText){
        Message updatedMessage = messageService.updateMessageText(messageId, messageText);
        if(updatedMessage != null){
            return ResponseEntity.ok(updatedMessage);
        }

        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    //@GetMapping(path = "accounts/{account_id}/messages")
    //@ResponseStatus(HttpStatus.OK)
    //@ResponseBody
    

   


}
