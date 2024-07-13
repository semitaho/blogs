package com.tahoo.guides.chatgptjava.streaming;

import java.util.Map;

public record ChatGptStreamingResponseChoice(Map<String, Object> delta) {
}
