package com.tahoo.guides.chatgptjava.streaming;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tahoo.guides.chatgptjava.ChatGptResponseChoiceMessage;
import com.tahoo.guides.chatgptjava.ChatGptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.internal.util.MapUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.tahoo.guides.chatgptjava.ChatGptUtil.createMessages;
import static com.tahoo.guides.chatgptjava.ChatGptUtil.getContent;
import static java.util.Optional.ofNullable;

@Service
public class ChatGptStreamingService implements Function<String, Flux<String>> {
  public final WebClient client;

  private static final Long STREAMING_DURATION_MS = 100L;

  public ChatGptStreamingService(final WebClient webClient) {
    this.client = webClient;
  }

  public Flux<String> apply(final String message) {
    final var request = createRequest(message);
    return client.post()
            .bodyValue(request)

            .retrieve()
            .bodyToFlux(String.class)
            .delayElements(Duration.ofMillis(STREAMING_DURATION_MS))
            .filter(streamChunk())
            .map(response -> {
              final var jsonMap = ChatGptUtil.convertToJsonMap(response);
              return getContent(jsonMap, "delta");
            });
  }


  private static Predicate<String> streamChunk() {
    return currentVal -> !currentVal.contains("DONE") && currentVal.contains("content");
  }


  private static Map<String, Object> createRequest(final String message) {
    return Map.of(
            "model", "gpt-3.5-turbo",
            "stream", true,
            "messages", createMessages(message)

    );
  }
}
