package com.tahoo.guides.chatgptjava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tahoo.guides.chatgptjava.streaming.ChatGptStreamingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public final class ChatGptUtil {

  private final static Logger LOG = LoggerFactory.getLogger(ChatGptUtil.class);

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


  public static List<Map<String, String>> createMessages(final String message) {
    return List.of(
            Map.of("role", "user",
                    "content", message));
  }

  public static Map<String, Object> convertToJsonMap(String content) {
    try {
      return OBJECT_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() {
      });
    } catch (JsonProcessingException e) {
      LOG.error("ERROR parsing string to json: {}", content, e);
      throw new RuntimeException(e);
    }

  }

  public static String getContent(final Map<String, Object> jsonMap, final String messageField) {
    final var choices = (List<Map<String, Object>>) jsonMap.get("choices");
    return choices.stream()
            .findFirst()
            .map(choice -> choice.get(messageField))
            .map(message -> (Map<String, String>) message)
            .map(delta -> delta.get("content"))
            .orElse("");
  }
}
