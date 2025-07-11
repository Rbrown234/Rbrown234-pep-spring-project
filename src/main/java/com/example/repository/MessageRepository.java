package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Integer>{

    Message findByPostedBy(int postedBy);
}
