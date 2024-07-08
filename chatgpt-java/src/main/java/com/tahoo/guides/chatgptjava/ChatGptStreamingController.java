package com.tahoo.guides.chatgptjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ChatGptStreamingController {

  @Autowired
  private ChatGptStreamingService chatGptStreamingService;

  @GetMapping("/chat")
  public Mono<String> chat(@RequestParam("prompt") String prompt) {

    return chatGptStreamingService.chat(prompt);
  }
}
