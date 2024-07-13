package com.tahoo.guides.chatgptjava.streaming;

import com.tahoo.guides.chatgptjava.ChatGptResponseChoice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ChatGptStreamingResponse(Optional<List<Map<String, Object>>> choices) {
}
