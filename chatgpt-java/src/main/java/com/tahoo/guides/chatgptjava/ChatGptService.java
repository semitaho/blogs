package com.tahoo.guides.chatgptjava;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static com.tahoo.guides.chatgptjava.ChatGptUtil.convertToJsonMap;
import static com.tahoo.guides.chatgptjava.ChatGptUtil.createMessages;
import static com.tahoo.guides.chatgptjava.ChatGptUtil.getContent;

@Service
public class ChatGptService {



  final static Logger log = LoggerFactory.getLogger(ChatGptService.class);
  public final WebClient client;

  public ChatGptService(final WebClient webClient) {
    this.client = webClient;
  }

  public Mono<String> chat(final String message) {
    final var request = createRequest(message);
    return client.post()
            .bodyValue(request)

            .retrieve()

            .bodyToMono(String.class)
            .doOnError(consumer -> {
              log.warn("ei näin: {}", consumer);
            })
            .map(response -> {
              final var jsonMap = convertToJsonMap(response);
              return getContent(jsonMap, "message");
            });
  }


  private static Map<String, Object> createRequest(final String message) {
    final var request =  Map.of(
            "model", "gpt-3.5-turbo",
            "stream", false,
            "messages", createMessages(message)

    );

    log.info("Chat GPT request: {}", request);

    return request;
  }

}
