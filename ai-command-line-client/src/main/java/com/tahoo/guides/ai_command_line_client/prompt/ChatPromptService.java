package com.tahoo.guides.ai_command_line_client.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatPromptService {

  private final ChatClient chatClient;

  public ChatPromptService(final ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public Flux<String> stream(final String question) {
    return chatClient.prompt()
            .user(question)
            .stream()
            .content();
  }
}
