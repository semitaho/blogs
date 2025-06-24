package com.tahoo.guides.spring_ai_poc;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest.AudioParameters.Voice.ALLOY;

@Service
public class PromptToAudioOutputService implements Function<String, byte[]> {

  private final ChatClient client;

  PromptToAudioOutputService(final ChatClient.Builder clientBuilder) {
    this.client = clientBuilder.build();
  }

  @Override
  public byte[] apply(final String textPrompt) {
    final var userMessage = UserMessage.builder()
            .text(textPrompt)
            .build();
    final var chatResponse = client.prompt()
            .messages(userMessage)
            .options(OpenAiChatOptions
                    .builder()
                    .model(OpenAiApi.ChatModel.GPT_4_O_AUDIO_PREVIEW)
                    .outputModalities(List.of("text", "audio"))
                    .outputAudio(new OpenAiApi.ChatCompletionRequest.AudioParameters(
                            ALLOY,
                            OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat.MP3))

                    .build()
            )
            .call()
            .chatResponse();
    return chatResponse.getResult().getOutput().getMedia().get(0).getDataAsByteArray();

  }
}
