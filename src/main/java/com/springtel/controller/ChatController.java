package com.springtel.controller;

import com.springtel.model.Chat;
import com.springtel.repository.ChatRepository;
import com.springtel.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @PostMapping
    public ResponseEntity<String> saveChat(@RequestBody String chatData) {
        try {
            chatService.saveChat(chatData);
            return ResponseEntity.status(HttpStatus.CREATED).body("Chat saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save chat: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable("id") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        return chat.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
