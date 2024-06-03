package com.tahoo.guides.banktransactionretry.exception;

public class InsufficientBalanceException extends RuntimeException{

  public InsufficientBalanceException(final String message) {
    super(message);
  }
}
