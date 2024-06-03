package com.tahoo.guides.banktransactionretry;

import com.tahoo.guides.banktransactionretry.exception.InsufficientBalanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankTransactionRetryService {


  Logger logger = LoggerFactory.getLogger(BankTransactionRetryService.class);


  @Retryable
  public BankTransactionData transferMoney(final BankTransactionData bankTransactionData, final BigDecimal amount) {
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
