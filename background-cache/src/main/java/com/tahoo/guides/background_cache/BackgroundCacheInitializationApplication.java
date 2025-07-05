package com.tahoo.guides.background_cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackgroundCacheInitializationApplication implements CommandLineRunner {


  private static final Logger log = LoggerFactory.getLogger(BackgroundCacheInitializationApplication.class);


  @Override
  public void run(String... args) throws Exception {
    // This method will be called after the application context is loaded
    // and all beans are initialized.
    log.info("Application started successfully!");
  }


  public static void main(String[] args) {
    SpringApplication.run(BackgroundCacheInitializationApplication.class, args);
  }

}
