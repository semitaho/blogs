package com.tahoo.guides.chatgptjava;

import java.util.List;

public record ChatGptResponse(List<ChatGptResponseChoice> choices) {
}
