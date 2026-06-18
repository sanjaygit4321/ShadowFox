package com.bank.onlinebanking.controller;

import com.bank.onlinebanking.dto.TransactionResponseDTO;
import com.bank.onlinebanking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionRepository transactionRepository;

    @GetMapping
    public List<TransactionResponseDTO> getAllTransactions() {

        return transactionRepository.findAll()
                .stream()
                .map(tx -> new TransactionResponseDTO(
                        tx.getId(),
                        tx.getFromAccount(),
                        tx.getToAccount(),
                        tx.getAmount(),
                        tx.getTimestamp().toString()
                ))
                .toList();
    }
}


