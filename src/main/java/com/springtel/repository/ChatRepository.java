package com.springtel.repository;
import com.springtel.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c LEFT JOIN FETCH c.messages WHERE c.chatId = :id")
    Optional<Chat> findByIdWithMessages(Long id);
}
