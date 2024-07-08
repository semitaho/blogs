package com.tahoo.guides.chatgptjava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootApplication
public class ChatGptJavaRestApiApplication {


  private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

  @Bean
  public WebClient chatGptClient(
          @Value("${OPENAI_API_KEY}") final String openaiApiKey) {
    return WebClient
            .builder()
            .baseUrl(OPENAI_API_URL)
            .defaultHeaders(httpHeaders -> {
              httpHeaders.set("Content-type", APPLICATION_JSON_VALUE);
              httpHeaders.setBearerAuth(openaiApiKey);
            })

            .build();
  }

  public static void main(String[] args) {
    SpringApplication.run(ChatGptJavaRestApiApplication.class, args);
  }

}
