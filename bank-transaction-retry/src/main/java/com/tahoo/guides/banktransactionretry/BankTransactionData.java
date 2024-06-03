package com.tahoo.guides.banktransactionretry;

import java.math.BigDecimal;

public record BankTransactionData(BigDecimal accountABalance, BigDecimal accountBBalance) {
}
