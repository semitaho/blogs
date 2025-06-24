package com.tahoo.guides.spring_ai_poc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class AiController {

  private static final String AUDIO_MIME_TYPE = "audio/webm";

  private static final Logger LOG = LoggerFactory.getLogger(AiController.class);

  private final SpeechToTextService audioToSpeechService;

  private final PromptToAudioOutputService promptToAudioOutputService;


  AiController(final SpeechToTextService speechToTextService, final PromptToAudioOutputService promptToAudioOutputService) {
    this.audioToSpeechService = speechToTextService;
    this.promptToAudioOutputService = promptToAudioOutputService;
  }


  @PostMapping(value = "/upload", consumes = AUDIO_MIME_TYPE)
  public byte[] askGPT(InputStream inputStream) {
    return audioToSpeechService
            .andThen(AiController::logText)
            .andThen(promptToAudioOutputService).apply(inputStream);
  }

  private static String logText(String text)
  {
    LOG.info("Received text from audio transcription: {}", text);
    return text;
  }

}