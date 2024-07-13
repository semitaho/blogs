package com.tahoo.guides.chatgptjava.streaming;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.function.Function;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("/chatstream")
public class ChatGptStreamingController implements Function<String, Flux<String>> {

  private final ChatGptStreamingService chatGptStreamingService;

  public ChatGptStreamingController(ChatGptStreamingService chatGptStreamingService) {
    this.chatGptStreamingService = chatGptStreamingService;
  }


  @GetMapping( produces = TEXT_EVENT_STREAM_VALUE)
  @Override
  public Flux<String> apply(final String prompt) {
    return chatGptStreamingService.apply(prompt);
  }
}
