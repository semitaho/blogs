package com.tahoo.guides.banktransactionretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BankTransactionRetryApplication {


	@Autowired
	private BankTransactionRetryService bankTransactionRetryService;
	public static void main(String[] args) {
		final var banksTransactionRetryApp =   SpringApplication.run(BankTransactionRetryApplication.class, args);
		final BankTransactionRetryService retryService = banksTransactionRetryApp.getBean(BankTransactionRetryService.class);
		retryService.withdrawMoney(BigDecimal.valueOf(100));



	}

}
