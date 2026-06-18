package com.bank.onlinebanking.controller;

import com.bank.onlinebanking.dto.TransferRequestDTO;
import com.bank.onlinebanking.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(
            @RequestBody TransferRequestDTO dto) {

        transferService.transfer(
                dto.getFromAccount(),
                dto.getToAccount(),
                dto.getAmount()
        );

        return ResponseEntity.ok("Transfer successful");
    }
}
