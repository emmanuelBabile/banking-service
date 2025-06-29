package com.coding.bankingservice.service;

import com.coding.bankingservice.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class Account implements AccountService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Clock clock = Clock.systemDefaultZone();
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public int getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        this.balance += amount;

        Transaction depositTransaction = new Transaction(
                LocalDate.now(this.clock),
                amount,
                this.balance
        );
        this.transactions.add(depositTransaction);
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal.");
        }

        this.balance -= amount;

        Transaction withdrawalTransaction = new Transaction(
                LocalDate.now(this.clock),
                -amount,
                this.balance
        );
        this.transactions.add(withdrawalTransaction);
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount   || Balance");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            System.out.printf("%-10s || %-8s || %d%n",
                    transaction.getDate().format(DATE_FORMATTER),
                    String.format("%+d", transaction.getAmount()),
                    transaction.getBalance()
            );
        }
    }
}
