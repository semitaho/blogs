package com.tahoo.guides.spring_ai_poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.content.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

@Service
public class PromptToAudioOutputService implements Function<String, byte[]> {

  private static final Logger log = LoggerFactory.getLogger(PromptToAudioOutputService.class);
  private final ChatClient client;

  PromptToAudioOutputService(final ChatClient.Builder clientBuilder) {
    this.client = clientBuilder.build();
  }

  @Override
  public byte[] apply(final String textPrompt) {
    final var userMessage = UserMessage.builder()
            .text(textPrompt)
            .build();
    log.info("user: {}", userMessage);
    return
            ofNullable(
                    client.prompt()
                            .messages(userMessage)
                            .options(OpenAiChatOptions
                                    .builder()
                                    .model(OpenAiApi.ChatModel.GPT_4_O_MINI_AUDIO_PREVIEW)
                                    .outputModalities(List.of("text", "audio"))
                                    .outputAudio(new OpenAiApi.ChatCompletionRequest.AudioParameters(
                                            OpenAiApi.ChatCompletionRequest.AudioParameters.Voice.NOVA,
                                            OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat.MP3))

                                    .build()
                            )
                            .call()
                            .chatResponse())
                    .map(ChatResponse::getResult)
                    .map(Generation::getOutput)
                    .map(AssistantMessage::getMedia)
                    .map(List::getFirst)
                    .map(Media::getDataAsByteArray)
                    .orElse(null);
  }
}
