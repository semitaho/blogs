package com.tahoo.guides.ai_command_line_client;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class AiCommandLineClientApplicationConfiguration {

  public static Integer MAX_MESSAGES_IN_HISTORY = 10;

  public static String MESSAGE_ID = "msg-id-1";

  @Bean
  ChatMemory createChatMemory(final ChatMemoryRepository chatMemoryRepository) {
    return MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(MAX_MESSAGES_IN_HISTORY)
            .build();
  }

  @Bean
  ChatClient createChatClient(final ChatMemory chatMemory, final ChatClient.Builder chatClientBuilder) {
    return chatClientBuilder
            .defaultAdvisors(
                    MessageChatMemoryAdvisor
                            .builder(chatMemory)
                            .conversationId(AiCommandLineClientApplicationConfiguration.MESSAGE_ID)
                            .build())

            .defaultOptions(ChatOptions.builder()
                    .model(OpenAiApi.ChatModel.GPT_4_1_MINI.getValue())
                    .build())
            .build();
  }

  @Bean
  public PromptProvider myPromptProvider() {
    return () -> new AttributedString("gpt> ",
            AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
  }
}
