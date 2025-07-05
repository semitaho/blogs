package com.tahoo.guides.spring_ai_poc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.function.Function;

@Service
public class SpeechToTextService implements Function<InputStream, String> {

  private static final Logger log = LoggerFactory.getLogger(
          SpeechToTextService.class);

  private final OpenAiAudioTranscriptionModel transcriptionModel;

  public SpeechToTextService(final OpenAiAudioTranscriptionModel transcriptionModel) {
    this.transcriptionModel = transcriptionModel;
  }

  @Override
  public String apply(InputStream audioInputStream) {
    final var resource = new InputStreamResource(audioInputStream);
    // Use the transcription model to transcribe the audio input stream
    final var response = transcriptionModel
            .call(new AudioTranscriptionPrompt(resource,
                    OpenAiAudioTranscriptionOptions
                            .builder()
                            .temperature(0f)
                            .model(OpenAiApi.ChatModel.GPT_4_O.value)
                            .build()));
    final var text= response.getResult().getOutput();
    log.info("transcribed: {}", text);
    return text;
  }


}
