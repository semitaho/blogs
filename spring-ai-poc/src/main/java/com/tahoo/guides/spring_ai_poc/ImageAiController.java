package com.tahoo.guides.spring_ai_poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Base64;

@RestController
public class ImageAiController {

  private static final Logger log = LoggerFactory.getLogger(ImageAiController.class);
  private final ImageModel imageModel;
  ImageAiController(final ImageModel imageModel) {
    this.imageModel = imageModel;
  }
  @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] generateImage(
          final String prompt
  ) {
    final var imagePrompt = new ImagePrompt(prompt, OpenAiImageOptions.builder()
            .responseFormat("b64_json")
            .build());
    final var imageResponse = imageModel.call(imagePrompt);
    final var result = imageResponse.getResult();
    final var json = result.getOutput().getB64Json();
    log.info("DONE generating image");
    return Base64.getDecoder().decode( json);
  }
}
