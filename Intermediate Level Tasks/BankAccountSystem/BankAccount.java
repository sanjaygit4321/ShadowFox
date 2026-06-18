import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private final List<String> transactionHistory;

    public BankAccount() {
        balance = 0;
        transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) return;
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= 0) return;
        if (amount > balance) throw new Exception("Amount Withdrawn more than balance!");
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
