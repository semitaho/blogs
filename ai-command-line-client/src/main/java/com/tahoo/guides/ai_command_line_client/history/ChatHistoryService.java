package com.tahoo.guides.ai_command_line_client.history;


import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatHistoryService {


  private final ChatMemoryRepository chatMemoryRepository;

  public ChatHistoryService(final ChatMemoryRepository chatMemoryRepository) {
    this.chatMemoryRepository = chatMemoryRepository;
  }


  void getHistory(final int maxMessages) {
  }
}
