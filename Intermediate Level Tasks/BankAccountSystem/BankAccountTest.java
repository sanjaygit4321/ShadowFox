import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Test
    void testDeposit() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
        assertEquals(1, account.getTransactionHistory().size());
    }

    @Test
    void testWithdraw() throws Exception {
        account.deposit(1000);
        account.withdraw(300);
        assertEquals(700, account.getBalance());
        assertEquals(2, account.getTransactionHistory().size());
    }

    @Test
    void testBalanceEnquiry() throws Exception {
        assertEquals(0, account.getBalance());
        account.deposit(400);
        account.withdraw(100);
        assertEquals(300, account.getBalance());
    }

    @Test
    void testTransactionHistory() throws Exception {
        account.deposit(200);
        account.withdraw(100);
        account.deposit(50);
        assertEquals(3, account.getTransactionHistory().size());
    }

    @Test
    void testWithdrawMoreThanBalance() {
        account.deposit(500);
        Exception exception = assertThrows(Exception.class, () -> account.withdraw(1000));
        assertEquals("Withdrawn Amount", exception.getMessage());
        assertEquals(500, account.getBalance());
    }
    @Test
    void testFailDeposit() {
        account.deposit(500);
        assertEquals(1000, account.getBalance());
    }


    @Test
    void testNegativeWithdraw() throws Exception {
        account.deposit(500);
        account.withdraw(-200);
        assertEquals(500, account.getBalance());
        assertEquals(1, account.getTransactionHistory().size());
    }
    }
