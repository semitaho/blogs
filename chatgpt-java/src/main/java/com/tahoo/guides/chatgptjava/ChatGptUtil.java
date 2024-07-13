package com.tahoo.guides.chatgptjava;

import java.util.List;
import java.util.Map;

public final class ChatGptUtil {

  public static List<Map<String, String>> createMessages(final String message) {
    return List.of(
            Map.of("role", "user",
                    "content", message));
  }
}
