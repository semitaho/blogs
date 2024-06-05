package com.tahoo.guides.banktransactionretry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class BankTransactionRetryConfiguration {

  @Bean
  public RetryTemplate bankTransactionRetryTemplate() {
    final RetryTemplate retryTemplate = new RetryTemplate();
    final var fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(200);
    final var retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(2);
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    retryTemplate.setRetryPolicy(retryPolicy);
    return retryTemplate;
  }
}
