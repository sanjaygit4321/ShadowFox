package com.bank.onlinebanking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @NotBlank
    private String Name;

    @Min(0)
    private double initialBalance;
}
