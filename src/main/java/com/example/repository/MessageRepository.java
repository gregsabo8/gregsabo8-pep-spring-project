package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

    @Query("FROM Message WHERE messageId =:messageId")
	public Message getMessageById(@Param("messageId") int messageId);

    @Modifying
    @Query("DELETE FROM Message WHERE messageId=:messageId")
    public void deleteById(@Param("messageId") int messageId);

    @Query("FROM Message WHERE postedBy =:postedBy")
    public List<Message> getMessagesForUser(@Param("postedBy") int postedBy);
}
// @Query("FROM Pet WHERE name = :name OR age=:age")
// List<Pet> lab2(@Param("name") String name, @Param("age") int age);