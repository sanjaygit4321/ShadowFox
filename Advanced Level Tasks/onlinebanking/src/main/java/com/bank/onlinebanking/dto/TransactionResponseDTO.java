package com.bank.onlinebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponseDTO {

    private Long id;
    private Long fromAccount;
    private Long toAccount;
    private double amount;
    private String time;
}
