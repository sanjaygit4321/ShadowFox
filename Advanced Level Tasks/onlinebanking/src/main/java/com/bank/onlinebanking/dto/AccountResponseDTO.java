package com.bank.onlinebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponseDTO {

    private Long accountId;
    private String Name;
    private double balance;
}
