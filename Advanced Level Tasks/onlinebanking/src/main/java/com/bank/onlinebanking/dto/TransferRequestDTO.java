package com.bank.onlinebanking.dto;

import lombok.Data;

@Data
public class TransferRequestDTO {

    private Long fromAccount;
    private Long toAccount;
    private double amount;
}
