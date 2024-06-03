package com.tahoo.guides.banktransactionretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import java.math.BigDecimal;

@SpringBootApplication
@EnableRetry
public class BankTransactionRetryApplication {


	@Autowired
	private BankTransactionRetryService bankTransactionRetryService;
	public static void main(String[] args) {
		final var banktransactionData = new BankTransactionData(BigDecimal.valueOf(99), BigDecimal.valueOf(30));
		final var banksTransactionRetryApp =   SpringApplication.run(BankTransactionRetryApplication.class, args);
		final BankTransactionRetryService retryService = banksTransactionRetryApp.getBean(BankTransactionRetryService.class);
		retryService.transferMoney(banktransactionData, BigDecimal.valueOf(100));



	}

}
