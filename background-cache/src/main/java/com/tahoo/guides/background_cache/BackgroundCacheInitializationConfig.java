package com.tahoo.guides.background_cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BackgroundCacheInitializationConfig {


  @Bean(bootstrap = Bean.Bootstrap.BACKGROUND)
  CacheInitializer cacheInitializer(final NumbersGenerationService numbersGenerationService) {
    return new CacheInitializer(numbersGenerationService);
  }

  @Bean
  NumbersGenerationService numbersGenerationService() {
    return new NumbersGenerationService();
  }


}
