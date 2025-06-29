# Banking Service Project

This project is an implementation of core banking functionalities developed in **Java** using the **Spring Boot** framework. It simulates deposit, withdrawal, and account statement printing operations, in accordance with a precise set of specifications.

---

## 📄 Summary

The application exposes functionalities of a **single bank account** through a service class. It allows a user to:

1. 💰 Deposit money into the account.
2. 💸 Withdraw money (if sufficient funds are available).
3. 🧾 Print an account statement with the **most recent transactions shown first**.

The focus is on:
- Clean code
- Respect of business constraints
- Testability of business logic

---

## 🛠️ Technologies Used

| Component           | Version / Tool                        |
|---------------------|----------------------------------------|
| **Language**        | Java 21                                |
| **Framework**       | Spring Boot 3.5.3                      |
| **Build Tool**      | Apache Maven                           |
| **Libraries**       | - Lombok<br>- Spring Boot DevTools     |

---

## 📁 Project Structure

```plaintext
banking-service/
├── pom.xml                                # Maven build and dependency file
└── src/main/java/com/coding/bankingservice/
    ├── model/
    │   └── Transaction.java               # Represents a transaction with date, amount, balance
    ├── service/
    │   ├── AccountService.java            # Interface defining core account operations
    │   └── Account.java                   # Implementation containing business logic
    └── BankingServiceApplication.java     # Main class and test scenario runner
```

---

## 🧠 Key Design Decisions

### 🕒 Testing Date-Dependent Logic

To make transactions testable with controlled dates:
- A `Clock` is injected into the `Account` class (`Clock.systemDefaultZone()` by default).
- A public method `setClock(Clock clock)` (excluded from interface) allows setting a fixed/mock clock for tests.
- All date retrievals use `LocalDate.now(this.clock)` to ensure predictable outputs.

### ⚠️ Exception Handling

The application handles invalid inputs as follows:
- Throws `IllegalArgumentException` on:
  - Negative or zero deposit/withdrawal attempts
  - Withdrawals with insufficient balance

### 🧪 Testing and Validation

Executed through the `run()` method of the main class:
- First, the **nominal success scenario** is tested.
- Then, **error cases** are triggered using `try-catch` blocks to verify exception throwing without app failure.

---

## ▶️ How to Run

### ✅ Prerequisites
- JDK 21 or higher
- Apache Maven 3.6+
- Java-compatible IDE (e.g., IntelliJ IDEA, VS Code)

### 📌 Execution Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd banking-service
   ```

2. Open the project in your IDE. Dependencies will resolve via `pom.xml`.

3. Locate:
   ```
   src/main/java/com/coding/bankingservice/BankingServiceApplication.java
   ```

4. Run the `main()` method.

5. Observe the console output.

---

## 🖨️ Expected Console Output

```plaintext
--- Banking Service Application Started ---

--- Nominal scenario test (success) ---
Date       || Amount   || Balance
14/01/2012 || -500     || 2500
13/01/2012 || +2000    || 3000
10/01/2012 || +1000    || 1000

--- Testing expected error cases ---
Test 1: Deposit with negative amount
Expected error: Deposit amount must be positive.
Test 2: Attempting to withdraw 4000 (current balance: 2500)
Expected error: Insufficient balance for withdrawal.
Test 3: Attempting to withdraw 0
Expected error: Withdrawal amount must be greater than zero.
--- Error testing completed. The program did not crash. ---
```
