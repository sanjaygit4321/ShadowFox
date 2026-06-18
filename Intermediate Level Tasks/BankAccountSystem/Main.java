import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean ATMawake = true;
        BankAccount account = new BankAccount();

        System.out.println("\n==== Welcome to Bank Account Management System ====");

        while (ATMawake) {

            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter the choice: ");
            int choice = sc.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter deposit amount: ");
                    double deposit = sc.nextDouble();
                    account.deposit(deposit);
                    System.out.println("Deposited amount: " + deposit);

                }
                case 2 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = sc.nextDouble();
                    try {
                        account.withdraw(withdraw);
                        System.out.println("Withdrawn amount: " + withdraw);
                        System.out.println("Available Balance: " + account.getBalance());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Current Balance: " + account.getBalance());
                }
                case 4 -> {
                    System.out.println("Transaction History:");
                    if (account.getTransactionHistory().isEmpty()) {
                        System.out.println("No transactions made.");
                    }
                    for (String t : account.getTransactionHistory()) {
                        System.out.println(t);
                    }
                }
                case 5 -> {
                    ATMawake = false;
                    System.out.println("Thank you for using ATM!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}
