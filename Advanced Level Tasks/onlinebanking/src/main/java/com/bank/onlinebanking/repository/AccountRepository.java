package com.bank.onlinebanking.repository;

import com.bank.onlinebanking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
