package com.tahoo.guides.ai_command_line_client.history;


import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryService {


  private static String MESSAGE_ID = "msg-id-1";
  private final ChatMemory chatMemory;

  public ChatHistoryService(final ChatMemoryRepository chatMemoryRepository) {
    this.chatMemory = MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(10)

            .build();
  }


  public List<Message> getHistory() {
    return chatMemory.get(MESSAGE_ID);


  }

  public ChatMemory getChatMemory() {
    return chatMemory;
  }

  public String getMessageId() {
    return MESSAGE_ID;
  }
}
