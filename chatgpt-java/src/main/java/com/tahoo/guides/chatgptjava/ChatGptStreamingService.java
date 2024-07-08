package com.tahoo.guides.chatgptjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ChatGptStreamingService {


  final static Logger log = LoggerFactory.getLogger(ChatGptStreamingService.class);
  public final WebClient client;

  public ChatGptStreamingService(final WebClient webClient) {
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
    return Map.of(
            "model", "gpt-3.5-turbo",
            "messages", createMessages(message)

    );
  }

  private static List<Map<String, String>> createMessages(final String message) {
    return List.of(
            Map.of("role", "user",
                    "content", message));
  }
}
