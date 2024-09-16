package com.tahoo.guides.gracefulshutdown;

import jakarta.annotation.PreDestroy;
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
    LOG.info("STARTING longproces");
    Thread.sleep(Duration.ofSeconds(HOLD_THREAD_SECONDS));
    LOG.info("COMPLETING longprocess");
    return "OK";
  }

  @GetMapping("/processnotavailable")
  public String processNotAvailable() {
    LOG.info("processnotavailable");
    return "Process should not be handled";
  }
}
