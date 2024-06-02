package com.tahoo.guides.banktransactionretry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankTransactionRetryService {

  Logger logger = LoggerFactory.getLogger(BankTransactionRetryService.class);


  public void withdrawMoney(final BigDecimal amount) {
    logger.info("money amount: {}", amount);

  }
}
