package com.tahoo.guides.spring_ai_poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeechAiController {

  private static final Logger LOG = LoggerFactory.getLogger(SpeechAiController.class);

  private final SpeechModel speechModel;

  SpeechAiController(final SpeechModel speechModel) {
    this.speechModel = speechModel;
  }

  @GetMapping(value = "/audio")
  public byte[] generateAudio(final String prompt) {

    OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
            .voice(OpenAiAudioApi.SpeechRequest.Voice.ONYX)
            .speed(0.7f)

            .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
            .model(OpenAiAudioApi.TtsModel.TTS_1_HD.value)
            .build();

    final var speechResponse = speechModel.call(new SpeechPrompt(prompt, speechOptions));
    LOG.info("GOT response from speech model: {}", speechResponse.getResult().getMetadata());
    return speechResponse.getResult().getOutput();

  }


}

