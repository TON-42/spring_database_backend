package com.springtel.repository;
import com.springtel.model.Message;
import com.springtel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Long countByFromUser(User user);
}

