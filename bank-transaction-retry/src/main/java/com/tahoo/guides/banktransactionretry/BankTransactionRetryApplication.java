package com.tahoo.guides.banktransactionretry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;

import java.math.BigDecimal;

@SpringBootApplication
@EnableRetry
@Import(BankTransactionRetryConfiguration.class)
public class BankTransactionRetryApplication {


	static Logger logger = LoggerFactory.getLogger(BankTransactionRetryApplication.class);


	@Autowired
	private BankTransactionRetryService bankTransactionRetryService;
	public static void main(String[] args) {
		final var banktransactionData = new BankTransactionData(BigDecimal.valueOf(99), BigDecimal.valueOf(30));
		final var banksTransactionRetryApp =   SpringApplication.run(BankTransactionRetryApplication.class, args);
		logger.info("using annotated service");
		final BankTransactionRetryService retryService = banksTransactionRetryApp.getBean(BankTransactionRetryService.class);
		retryService.transferMoneyUsingRecover(banktransactionData, BigDecimal.valueOf(100));
		logger.info("using retry template");
		retryService.transferMoneyUsingRetryTemplate(banktransactionData, BigDecimal.valueOf(150));

	}

}
