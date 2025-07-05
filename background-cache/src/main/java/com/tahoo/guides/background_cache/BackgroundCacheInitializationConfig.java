package com.tahoo.guides.background_cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;


@Configuration
public class BackgroundCacheInitializationConfig {


  @Bean(bootstrap = Bean.Bootstrap.BACKGROUND)
  @Lazy
  @DependsOn("numbersGenerationService")
  CacheInitializer cacheInitializer(final NumbersGenerationService numbersGenerationService) {
    return new CacheInitializer(numbersGenerationService);
  }

  @Bean
  NumbersGenerationService numbersGenerationService() {
    return new NumbersGenerationService();
  }


}
