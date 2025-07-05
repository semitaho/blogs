package com.tahoo.guides.background_cache;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;


public class CacheInitializer {

  private static final Logger log = LoggerFactory.getLogger(CacheInitializer.class);
  private final NumbersGenerationService numbersGenerationService;
  private List<Long> numbers = new LinkedList<>();

  public CacheInitializer(final NumbersGenerationService numbersGenerationService) {
    this.numbersGenerationService = numbersGenerationService;
    log.info("CacheInitializer created with NumbersGenerationService");

  }

  @PostConstruct
  public void init() {
    postSetup();
  }

  private void postSetup() {
    log.info("post construct... initializing CacheInitializer");
    initNumbers();
    log.info("CacheInitializer initialized");
  }

  void initNumbers() {
    numbers = this.numbersGenerationService.get();
  }
}
