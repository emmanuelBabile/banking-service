package com.coding.bankingservice;

import com.coding.bankingservice.service.Account;
import com.coding.bankingservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@SpringBootApplication
public class BankingServiceApplication implements CommandLineRunner {

    private final AccountService accountService;

    public BankingServiceApplication(AccountService accountService) {
        this.accountService = accountService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankingServiceApplication.class, args);
    }

    @Override
    public void run(String... args){
        System.out.println("\n--- Banking Service Application Started ---");

        System.out.println("\n--- Nominal scenario test (success) ---");
        Account account = (Account) accountService;

        // Original success scenario
        account.setClock(Clock.fixed(Instant.parse("2012-01-10T10:00:00Z"), ZoneId.systemDefault()));
        account.deposit(1000);
        account.setClock(Clock.fixed(Instant.parse("2012-01-13T10:00:00Z"), ZoneId.systemDefault()));
        account.deposit(2000);
        account.setClock(Clock.fixed(Instant.parse("2012-01-14T10:00:00Z"), ZoneId.systemDefault()));
        account.withdraw(500);

        account.printStatement();

        // Testing error cases
        System.out.println("\n--- Testing expected error cases ---");

        try {
            System.out.println("Test 1: Deposit with negative amount");
            account.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            System.out.printf("Test 2: Attempting to withdraw 4000 (current balance: %d)%n", account.getBalance());
            account.withdraw(4000);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            System.out.println("Test 3: Attempting to withdraw 0");
            account.withdraw(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println("--- Error testing completed. The program did not crash. ---");
    }
}
