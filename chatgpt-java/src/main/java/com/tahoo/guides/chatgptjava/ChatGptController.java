package com.tahoo.guides.chatgptjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/chat")
public class ChatGptController {

  private final ChatGptService chatGptService;

  public ChatGptController(ChatGptService chatGptService) {
    this.chatGptService = chatGptService;
  }
  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public Mono<String> chat(
          @RequestParam("prompt")

                             final String prompt) {

    return chatGptService.chat(prompt);
  }
}
