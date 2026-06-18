package com.bank.onlinebanking.service;

import com.bank.onlinebanking.entity.Account;
import com.bank.onlinebanking.entity.Transaction;
import com.bank.onlinebanking.repository.AccountRepository;
import com.bank.onlinebanking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public void transfer(Long fromId, Long toId, double amount) {

        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (from.getBalance() < amount)
            throw new RuntimeException("Insufficient funds");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        Transaction tx = new Transaction();
        tx.setFromAccount(fromId);
        tx.setToAccount(toId);
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());

        transactionRepository.save(tx);
    }
}
