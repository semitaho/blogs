package com.tahoo.guides.gracefulshutdown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class GracefulShutdownController {

   private static final Logger LOG = LoggerFactory.getLogger(GracefulShutdownController.class);

  private static final Long HOLD_THREAD_SECONDS = 10l;


  @GetMapping("/longprocess")
  public String longProcess() throws InterruptedException{
    LOG.info("STARTING graceful shutdown");
    Thread.sleep(Duration.ofSeconds(HOLD_THREAD_SECONDS));
    return "OK";
  }
}
