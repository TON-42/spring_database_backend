package com.springtel.controller;

import com.springtel.model.User;
import com.springtel.repository.MessageRepository;
import com.springtel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public ResponseEntity<Long> getAllUser(){
        return ResponseEntity.ok(userRepository.count());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<String> getUserMesssageNumber(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Long res = messageRepository.countByFromUser(user.get());
            return ResponseEntity.ok(res.toString());
        }
        return ResponseEntity.notFound().build();
    }
}
