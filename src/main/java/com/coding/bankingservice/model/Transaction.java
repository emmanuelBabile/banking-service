package com.coding.bankingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Transaction {
    private LocalDate date;
    private int amount;
    private int balance;
}
