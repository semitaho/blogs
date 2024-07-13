package com.tahoo.guides.chatgptjava.streaming;

import com.tahoo.guides.chatgptjava.ChatGptResponseChoiceMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.internal.util.MapUtils;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.tahoo.guides.chatgptjava.ChatGptUtil.createMessages;
import static java.util.Optional.ofNullable;

@Service
public class ChatGptStreamingService implements Function<String, Flux<String>> {

  final static Logger log = LoggerFactory.getLogger(ChatGptStreamingService.class);
  public final WebClient client;

  public ChatGptStreamingService(final WebClient webClient) {
    this.client = webClient;
  }

  public Flux<String> apply(final String message) {
    final var request = createRequest(message);
    return client.post()
            .bodyValue(request)

            .retrieve()
            .bodyToFlux(Object.class)
            .doOnError(consumer -> {
              log.warn("ei näin: {}", consumer.getMessage());
            })
            .map(response -> {
              log.info("current value: {}", response);
              return "jaa";

              /*
              final var choicesOptional = response.choices();
              if (choicesOptional.isEmpty()) return "";
              final var choices = choicesOptional.orElseThrow();
              final Optional<Map<String, Object>> deltaOptional =

                      choices
                              .
                              stream()
                              .findFirst()
                              .map(toMap())
                              .map(obj -> obj.get("delta"))
                              .map(toMap());
              return deltaOptional.map(delta -> delta.get("content")).map(Object::toString).orElse("");
           */
            });
  }

  private static Function<Object, Map<String, Object>> toMap() {
    return obj -> ((Map<String, Object>) obj);
  }


  private static Map<String, Object> createRequest(final String message) {
    final var request = Map.of(
            "model", "gpt-3.5-turbo",
            "stream", true,
            "messages", createMessages(message)

    );
    return request;
  }
}
