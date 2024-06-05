package com.tahoo.guides.banktransactionretry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

@Component("bankTransactionRetryListener")
public class BankTransactionRetryListener implements RetryListener {


  private static Logger logger = LoggerFactory.getLogger(BankTransactionRetryListener.class);

  @Override
  public <T, E extends Throwable> boolean open(final RetryContext context, final RetryCallback<T, E> callback) {
    logger.info("STARTING");
    return true;
  }

  @Override
  public <T, E extends Throwable> void onError(final RetryContext context, final RetryCallback<T, E> callback, final Throwable throwable) {
    logger.warn("FAILED, re-trying action: {}, retry attempt: {}", callback.getLabel(), context.getRetryCount());
  }
}
