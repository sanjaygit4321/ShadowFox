import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleCalculator {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
            while (running) {
                showMenu();
                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }

            switch (choice) {
                    case 1 -> basicArithmetic(scanner);
                    case 2 -> scientificCalculations(scanner);
                    case 3 -> unitConversions(scanner);
                    case 4 -> running = false;
                    default -> System.out.println("Invalid choice");
                }

            if (running) {
                    System.out.print("\nDo you want to continue? (y/n): ");
                    String again = scanner.next();
                    System.out.println();
                    if (!again.equalsIgnoreCase("y")) {
                        running = false;
                    }
            }
        }

        System.out.println("!! Thanks for using calculator !!");
        scanner.close();
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("==== CONSOLE CALCULATOR ====");
        System.out.println();
        System.out.println("1. Basic Arithmetic");
        System.out.println("2. Scientific Calculations");
        System.out.println("3. Unit Conversions");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Enter your choice: ");
    }

    private static void basicArithmetic(Scanner scanner) {

        try {
            System.out.print("Enter first number: ");
            BigDecimal a = scanner.nextBigDecimal();

            System.out.print("Enter second number: ");
            BigDecimal b = scanner.nextBigDecimal();

            System.out.print("Enter operator (+ - * /): ");
            char op = scanner.next().charAt(0);

            BigDecimal result;

            switch (op) {
                case '+' -> result = a.add(b);
                case '-' -> result = a.subtract(b);
                case '*' -> result = a.multiply(b);
                case '/' -> {
                    if (b.compareTo(BigDecimal.ZERO) == 0) {
                        System.out.println("Cannot divide by zero");
                        return;
                    }
                    result = a.divide(b, 10, RoundingMode.HALF_UP);
                }
                default -> {
                    System.out.println("Invalid operator");
                    return;
                }
            }

            System.out.println("Result: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values.");
            scanner.nextLine();
        }
    }

    private static void scientificCalculations(Scanner scanner) {

        try {
            System.out.println("\n==== Scientific Menu ====");
            System.out.println("1. Square Root");
            System.out.println("2. Exponentiation");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter number: ");
                double num = scanner.nextDouble();

                if (num < 0) {
                    System.out.println("Invalid input");
                    return;
                }

                System.out.println("Result: " + Math.sqrt(num));

            } else if (choice == 2) {
                System.out.print("Enter base: ");
                double base = scanner.nextDouble();

                System.out.print("Enter exponent: ");
                double exp = scanner.nextDouble();

                System.out.println("Result: " + Math.pow(base, exp));
            } else {
                System.out.println("Invalid choice");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

    private static void unitConversions(Scanner scanner) {

        try {
            System.out.println("\n==== Unit Conversion Menu ====");
            System.out.println("1. Temperature");
            System.out.println("2. Currency");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {

                System.out.println("\n---- Temperature Conversion ----");
                System.out.println("1. Celsius to Fahrenheit");
                System.out.println("2. Fahrenheit to Celsius");
                System.out.println();
                System.out.print("Enter your choice: ");
                int tempChoice = scanner.nextInt();

                if (tempChoice == 1) {
                    System.out.print("Enter temperature in Celsius: ");
                    double c = scanner.nextDouble();
                    double f = (c * 9 / 5) + 32;
                    System.out.println("Temperature in Fahrenheit: " + f);

                } else if (tempChoice == 2) {
                    System.out.print("Enter temperature in Fahrenheit: ");
                    double f = scanner.nextDouble();
                    double c = (f - 32) * 5 / 9;
                    System.out.println("Temperature in Celsius: " + c);

                } else {
                    System.out.println("Invalid choice");
                }

            } else if (choice == 2) {

                System.out.println("\n---- Currency Conversion ----");
                System.out.println("1. USD to INR");
                System.out.println("2. INR to USD");
                System.out.println();
                System.out.print("Enter your choice: ");
                int curChoice = scanner.nextInt();

                System.out.print("Enter amount: ");
                BigDecimal amt = scanner.nextBigDecimal();

                BigDecimal rate = new BigDecimal("90");

                if (curChoice == 1) {
                    System.out.println("Amount in INR: " + amt.multiply(rate));
                } else if (curChoice == 2) {
                    System.out.println("Amount in USD: " +
                            amt.divide(rate, 2, RoundingMode.HALF_UP));
                } else {
                    System.out.println("Invalid choice");
                }

            } else {
                System.out.println("Invalid choice");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }
}
