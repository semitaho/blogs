package com.tahoo.guides.spring_ai_poc;


import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.function.Function;

@Service
public class SpeechToTextService implements Function<InputStream, String> {

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
                            .language("en")
                            .build()));
    return response.getResult().getOutput();
  }


}
