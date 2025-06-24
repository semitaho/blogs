package com.tahoo.guides.spring_ai_poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;

public class TextAiController {

  private static final Logger LOG = LoggerFactory.getLogger(TextAiController.class);


  private final ChatModel chatModel;

  TextAiController(final ChatModel chatModel) {
    this.chatModel = chatModel;
  }

  public String generateText(final String prompt) {
    LOG.info("Generating text for prompt: {}", prompt);
    return null;
   // LOG.info("Received response from chat model: {}", chatResponse.getResult().getContent());
  //  return chatResponse.getResult().getContent();
  }
}
