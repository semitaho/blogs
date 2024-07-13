package com.tahoo.guides.chatgptjava;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static com.tahoo.guides.chatgptjava.ChatGptUtil.createMessages;

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

            .bodyToMono(ChatGptResponse.class)
            .doOnError(consumer -> {
              log.warn("ei näin: {}", consumer);
            })
            .map(response -> {
              log.info("PASKAN MÖIVÄT!! {}", response);
              return response.choices().get(0).message().content();
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
