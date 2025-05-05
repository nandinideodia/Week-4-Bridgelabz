import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Custom exception for insufficient balance
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class Exceptions {

    // 1. Checked Exception (Compile-time Exception)
    public static void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    // 2. Unchecked Exception (Runtime Exception)
    public static void divideNumbers() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the first number (numerator): ");
            int numerator = scanner.nextInt();

            System.out.print("Enter the second number (denominator): ");
            int denominator = scanner.nextInt();

            if (denominator == 0) {
                throw new ArithmeticException("Cannot divide by zero.");
            }

            double result = (double) numerator / denominator;

            System.out.println("Result of " + numerator + " / " + denominator + " is: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter numbers only.");

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            scanner.close();
            System.out.println("Program completed.");
        }
    }

    // 3. Custom Exception (User-defined Exception)
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above.");
        }
    }

    public static void ageValidationDemo(int ageToCheck) {
        try {
            validateAge(ageToCheck);
            System.out.println("Access granted!");
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 4. Multiple Catch Blocks
    public static void arrayOperations() {
        Scanner scanner = new Scanner(System.in);
        int[] myArray = null;
        int index = 0;

        try {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
            myArray = new int[size];

            System.out.println("Enter the elements of the array:");
            for (int i = 0; i < size; i++) {
                myArray[i] = scanner.nextInt();
            }

            System.out.print("Enter the index to retrieve: ");
            index = scanner.nextInt();

            int value = myArray[index];
            System.out.println("Value at index " + index + ": " + value);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index!");
        } catch (NullPointerException e) {
            System.out.println("Array is not initialized!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        } finally {
            scanner.close();
        }
    }

    // 5. try-with-resources (Auto-closing Resources)
    public static void readFirstLine(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstLine = br.readLine();
            if (firstLine != null) {
                System.out.println("First line: " + firstLine);
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    // 6. throw vs. throws (Exception Propagation)
    public static double calculateInterest(double amount, double rate, int years) throws IllegalArgumentException {
        if (amount < 0 || rate < 0) {
            throw new IllegalArgumentException("Invalid input: Amount and rate must be positive");
        }
        return amount * rate * years;
    }

    public static void interestCalculationDemo(double amount, double rate, int years) {
        try {
            double interest = calculateInterest(amount, rate, years);
            System.out.println("Calculated interest: " + interest);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 7. finally Block Execution
    public static void performDivisionWithFinally() {
        Scanner scanner = new Scanner(System.in);
        int numerator = 0;
        int denominator = 1;
        try {
            System.out.print("Enter the numerator: ");
            numerator = scanner.nextInt();
            System.out.print("Enter the denominator: ");
            denominator = scanner.nextInt();
            double result = (double) numerator / denominator;
            System.out.println("Result of division: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter integers.");
        } finally {
            scanner.close();
            System.out.println("Operation completed");
        }
    }

    // 8. Exception Propagation in Methods
    public static void method1() {
        System.out.println("Inside method1");
        int result = 10 / 0; // This will cause an ArithmeticException
        System.out.println("End of method1"); // This line will not be executed
    }

    public static void method2() {
        System.out.println("Inside method2");
        method1(); // Exception propagates from method1 to method2
        System.out.println("End of method2"); // This line will not be executed
    }

    public static void exceptionPropagationDemo() {
        System.out.println("Inside main");
        try {
            method2(); // Exception propagates from method2 to main
        } catch (ArithmeticException e) {
            System.out.println("Handled exception in main: " + e); // Catch and handle the exception
        }
        System.out.println("End of main");
    }

    // 9. Nested try-catch Block
    public static void nestedTryCatchDemo() {
        Scanner scanner = new Scanner(System.in);
        int[] myArray = null;
        int index = 0;
        int divisor = 1;

        try {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
            myArray = new int[size];

            System.out.println("Enter the elements of the array:");
            for (int i = 0; i < size; i++) {
                myArray[i] = scanner.nextInt();
            }

            System.out.print("Enter the index to access: ");
            index = scanner.nextInt();

            System.out.print("Enter the divisor: ");
            divisor = scanner.nextInt();

            try {
                int value = myArray[index];
                double result = (double) value / divisor;
                System.out.println("Result of division: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index!");
        } catch (NullPointerException e) {
            System.out.println("Array is not initialized!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers.");
        } finally {
            scanner.close();
        }
    }

    // 10. Bank Transaction System (Checked + Custom Exception)
    public static double withdraw(double balance, double amount) throws InsufficientBalanceException, IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount: Amount cannot be negative.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance!");
        }
        return balance - amount;
    }

    public static void bankTransactionDemo() {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000.0;

        try {
            System.out.print("Enter the withdrawal amount: ");
            double amount = scanner.nextDouble();
            balance = withdraw(balance, amount);
            System.out.println("Withdrawal successful, new balance: " + balance);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- 1. Checked Exception ---");
        readFile("data.txt"); // Create a data.txt file in the project root to test

        System.out.println("\n--- 2. Unchecked Exception ---");
        divideNumbers();

        System.out.println("\n--- 3. Custom Exception ---");
        ageValidationDemo(15);
        ageValidationDemo(25);

        System.out.println("\n--- 4. Multiple Catch Blocks ---");
        arrayOperations();

        System.out.println("\n--- 5. try-with-resources ---");
        readFirstLine("info.txt"); // Create an info.txt file to test

        System.out.println("\n--- 6. throw vs. throws ---");
        interestCalculationDemo(1000, 0.05, 2);
        interestCalculationDemo(1000, -0.05, 2);

        System.out.println("\n--- 7. finally Block Execution ---");
        performDivisionWithFinally();

        System.out.println("\n--- 8. Exception Propagation ---");
        exceptionPropagationDemo();

        System.out.println("\n--- 9. Nested try-catch Block ---");
        nestedTryCatchDemo();

        System.out.println("\n--- 10. Bank Transaction System ---");
        bankTransactionDemo();
    }
}