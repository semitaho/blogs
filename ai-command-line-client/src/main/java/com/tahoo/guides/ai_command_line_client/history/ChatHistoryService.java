package com.tahoo.guides.ai_command_line_client.history;


import com.tahoo.guides.ai_command_line_client.AiCommandLineClientApplicationConfiguration;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryService {

  private final ChatMemory chatMemory;

  public ChatHistoryService(final ChatMemory chatMemory) {
    this.chatMemory = chatMemory;
  }

  public List<Message> getHistory() {
    return chatMemory.get(AiCommandLineClientApplicationConfiguration.MESSAGE_ID);
  }
}
