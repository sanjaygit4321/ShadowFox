package com.bank.onlinebanking.service;

import com.bank.onlinebanking.dto.AccountRequestDTO;
import com.bank.onlinebanking.dto.AccountResponseDTO;
import com.bank.onlinebanking.entity.Account;
import com.bank.onlinebanking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountResponseDTO createAccount(AccountRequestDTO dto) {

        Account account = new Account();
        account.setName(dto.getName());
        account.setBalance(dto.getInitialBalance());

        Account saved = accountRepository.save(account);

        return new AccountResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getBalance()
        );
    }

    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(acc -> new AccountResponseDTO(
                        acc.getId(),
                        acc.getName(),
                        acc.getBalance()
                ))
                .collect(Collectors.toList());
    }
}
