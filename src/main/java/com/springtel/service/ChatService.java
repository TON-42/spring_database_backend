package com.springtel.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springtel.model.Chat;
import com.springtel.model.Message;
import com.springtel.model.TextEntity;
import com.springtel.model.User;
import com.springtel.repository.ChatRepository;
import com.springtel.repository.MessageRepository;
import com.springtel.repository.TextEntityRepository;
import com.springtel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TextEntityRepository textEntityRepository;

    public void saveChat(String jsonData) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);

        //create user | or skip if already in database
        Long userId = jsonNode.get("id").asLong();
        User user = new User();
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            String userName = jsonNode.get("name").asText();
            user.setUserId(userId);
            user.setName(userName);
            userRepository.save(user);
        } else {
            user = userOpt.get();
        }

        // insert chat if new | exception if already in database
        Long chatId = jsonNode.get("chat_id").asLong();
        if (chatRepository.findById(chatId).isPresent()) {
            throw new Exception("Chat already exists");
        }
        String chatType = jsonNode.get("type").asText();
        Long amountOfWords = jsonNode.get("amount_of_words").asLong();
        boolean permition = jsonNode.get("permition").asBoolean();

        Chat chat = new Chat();
        chat.setChatId(chatId);
        chat.setType(chatType);
        chat.setAmountOfWords(amountOfWords);
        chat.setPermition(permition);
        chat.setClient(user);
        chatRepository.save(chat);

        JsonNode messagesNode = jsonNode.get("messages");
        for (JsonNode messageNode : messagesNode) {
            Long messageId = messageNode.get("id").asLong();
            String messageType = messageNode.get("type").asText();
            LocalDateTime date = LocalDateTime.parse(messageNode.get("date").asText());
            Long dateUnixtime = messageNode.get("date_unixtime").asLong();
            String from = messageNode.get("from").asText();
            String fromId = messageNode.get("from_id").asText();
            String text = messageNode.get("text").asText();

            Message message = new Message();
            message.setId(messageId);
            message.setType(messageType);
            message.setDate(date);
            message.setDateUnixtime(dateUnixtime);
            message.setText(text);
            message.setChat(chat);

            User fromUser = userRepository.findById(Long.parseLong(fromId.replace("user", ""))).orElse(null);
            if (fromUser == null) {
                fromUser = new User();
                fromUser.setUserId(Long.parseLong(fromId.replace("user", "")));
                fromUser.setName(from);
                userRepository.save(fromUser);
            }
            message.setFromUser(fromUser);
            messageRepository.save(message);

            // Insert text entities
            JsonNode textEntitiesNode = messageNode.get("text_entities");
            for (JsonNode textEntityNode : textEntitiesNode) {
                String textEntityType = textEntityNode.get("type").asText();
                String textEntityText = textEntityNode.get("text").asText();

                TextEntity textEntity = new TextEntity();
                textEntity.setType(textEntityType);
                textEntity.setText(textEntityText);
                textEntity.setMessage(message);
                textEntityRepository.save(textEntity);
            }
        }


    }

}
