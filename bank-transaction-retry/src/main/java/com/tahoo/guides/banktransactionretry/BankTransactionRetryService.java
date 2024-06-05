package com.tahoo.guides.banktransactionretry;

import com.tahoo.guides.banktransactionretry.exception.InsufficientBalanceException;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankTransactionRetryService {


  static Logger logger = LoggerFactory.getLogger(BankTransactionRetryService.class);

  @Autowired
  private RetryTemplate retryTemplate;

  @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000), listeners = {"bankTransactionRetryListener"})
  public BankTransactionData transferMoneyUsingRecover(final BankTransactionData bankTransactionData, final BigDecimal amount) {
    return doTransfer(bankTransactionData, amount);
  }

  @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000), listeners = {"bankTransactionRetryListener"})
  public BankTransactionData transferMoneyWaitingTwoSeconds(final BankTransactionData bankTransactionData, final BigDecimal amount) {
    return doTransfer(bankTransactionData, amount);
  }

  public BankTransactionData transferMoneyUsingRetryTemplate(final BankTransactionData bankTransactionData, final BigDecimal amount) {
    return retryTemplate.execute(retryCallback ->
            doTransfer(bankTransactionData, amount));
  }



  @Recover
  public BankTransactionData transferMoneyUsingRecover(final InsufficientBalanceException infe) {
    logger.warn("do recovering...");
    return null;
  }

  private BankTransactionData doTransfer(final BankTransactionData bankTransactionData, final BigDecimal amount) {
    logger.info("[START] transaction");
    checkBalance(bankTransactionData, amount);
    final var debitedBankTransactionData = debitAccountA(bankTransactionData, amount);
    final var debitedAndCreditedBankTransactionData = creditAccountB(debitedBankTransactionData, amount);
    logger.info("[END] transaction");
    return debitedAndCreditedBankTransactionData;
  }


  private BankTransactionData debitAccountA(final BankTransactionData currentBankTransactionData, BigDecimal amount) {
    return new BankTransactionData(currentBankTransactionData.accountABalance().subtract(amount), currentBankTransactionData.accountBBalance());
  }

  private BankTransactionData creditAccountB(final BankTransactionData debitedBankTransactionData, final BigDecimal amount) {
    return new BankTransactionData(debitedBankTransactionData.accountABalance(), debitedBankTransactionData.accountBBalance().add(amount));
  }


  private void checkBalance(final BankTransactionData bankTransactionData, final BigDecimal amount) {
    if (bankTransactionData.accountABalance().compareTo(amount) < 0) {
      throw new InsufficientBalanceException("Account A has no balance for debiting %s, current balance: %s".formatted(amount, bankTransactionData.accountABalance()));
    }
  }
}
