//1

import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 1
        System.out.println(validateUsername("user_123"));
        System.out.println(validateUsername("123user"));
        System.out.println(validateUsername("us"));
    }

    public static boolean validateUsername(String username) {
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,14}$";
        return Pattern.matches(regex, username);
    }
}

//2

        import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 2
        System.out.println(validateLicensePlate("AB1234"));
        System.out.println(validateLicensePlate("A12345"));
    }

    public static boolean validateLicensePlate(String license) {
        String regex = "^[A-Z]{2}\\d{4}$";
        return Pattern.matches(regex, license);
    }
}

//3

        import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 3
        System.out.println(validateHexColor("#FFA500"));
        System.out.println(validateHexColor("#ff4500"));
        System.out.println(validateHexColor("#123"));
    }

    public static boolean validateHexColor(String hexColor) {
        String regex = "^#([0-9a-fA-F]{6})$";
        return Pattern.matches(regex, hexColor);
    }
}

//4

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 4
        String emailText = "Contact us at support@example.com and info@company.org";
        System.out.println(extractEmails(emailText));
    }

    public static List<String> extractEmails(String text) {
        List<String> emails = new ArrayList<>();
        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            emails.add(matcher.group());
        }
        return emails;
    }
}

//5

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 5
        String capitalizedText = "The Eiffel Tower is in Paris and the Statue of Liberty is in New York.";
        System.out.println(extractCapitalizedWords(capitalizedText));
    }

    public static List<String> extractCapitalizedWords(String sentence) {
        List<String> capitalizedWords = new ArrayList<>();
        String regex = "\\b[A-Z][a-zA-Z]*\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sentence);
        while (matcher.find()) {
            capitalizedWords.add(matcher.group());
        }
        return capitalizedWords;
    }
}

//6

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 6
        String dateText = "The events are scheduled for 12/05/2023, 15/08/2024, and 29/02/2020.";
        System.out.println(extractDates(dateText));
    }

    public static List<String> extractDates(String text) {
        List<String> dates = new ArrayList<>();
        String regex = "\\b\\d{2}/\\d{2}/\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            dates.add(matcher.group());
        }
        return dates;
    }
}

//7

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 7
        String linkText = "Visit https://www.google.com and http://example.org for more info.";
        System.out.println(extractLinks(linkText));
    }

    public static List<String> extractLinks(String text) {
        List<String> links = new ArrayList<>();
        String regex = "https?://\\S+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            links.add(matcher.group());
        }
        return links;
    }
}

//8

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 8
        String spaceText = "This   is an    example with  multiple  spaces.";
        System.out.println(replaceMultipleSpaces(spaceText));
    }

    public static String replaceMultipleSpaces(String text) {
        String regex = "\\s+";
        return text.replaceAll(regex, " ");
    }
}

//9

        import java.util.List;
import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 9
        String badWordText = "This is a damn bad example with some stupid words.";
        List<String> badWords = List.of("damn", "stupid");
        System.out.println(censorBadWords(badWordText, badWords));
    }

    public static String censorBadWords(String sentence, List<String> badWords) {
        String censoredSentence = sentence;
        for (String badWord : badWords) {
            String regex = "\\b" + Pattern.quote(badWord) + "\\b";
            censoredSentence = censoredSentence.replaceAll(regex, "****");
        }
        return censoredSentence;
    }
}

//10

        import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 10
        System.out.println(validateIPAddress("192.168.1.1"));
        System.out.println(validateIPAddress("256.1.1.1"));
    }

    public static boolean validateIPAddress(String ipAddress) {
        String octet = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)";
        String regex = "^" + octet + "\\." + octet + "\\." + octet + "\\." + octet + "$";
        return Pattern.matches(regex, ipAddress);
    }
}

//11

        import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 11
        System.out.println(validateCreditCard("4111111111111111"));
        System.out.println(validateCreditCard("5111111111111111"));
        System.out.println(validateCreditCard("411111111111111"));
    }

    public static boolean validateCreditCard(String cardNumber) {
        String regex = "^(4|5)\\d{15}$";
        return Pattern.matches(regex, cardNumber);
    }
}

//12

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 12
        String langText = "I love Java, Python, and JavaScript, but I haven't tried Go yet.";
        System.out.println(extractProgrammingLanguages(langText));
    }

    public static List<String> extractProgrammingLanguages(String text) {
        List<String> languages = new ArrayList<>();
        List<String> languageList = List.of("Java", "Python", "JavaScript", "Go");
        String regex = "\\b(" + String.join("|", languageList) + ")\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            languages.add(matcher.group());
        }
        return languages;
    }
}

//13

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 13
        String currencyText = "The price is $45.99, and the discount is 10.50.";
        System.out.println(extractCurrencyValues(currencyText));
    }

    public static List<String> extractCurrencyValues(String text) {
        List<String> values = new ArrayList<>();
        String regex = "\\b[€$]?\\d+(\\.\\d+)?\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            values.add(matcher.group());
        }
        return values;
    }
}

//14

        import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 14
        String repeatingText = "This is is a repeated repeated word test.";
        System.out.println(findRepeatingWords(repeatingText));
    }

    public static List<String> findRepeatingWords(String sentence) {
        List<String> repeatingWords = new ArrayList<>();
        String regex = "\\b(\\w+)\\s+\\1\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sentence);
        while (matcher.find()) {
            repeatingWords.add(matcher.group(1));
        }
        return repeatingWords;
    }
}

//15

        import java.util.regex.Pattern;

public class RegexPracticeProblems {

    public static void main(String[] args) {
        // Problem 15
        System.out.println(validateSSN("123-45-6789"));
        System.out.println(validateSSN("123456789"));
    }

    public static boolean validateSSN(String ssn) {
        String regex = "^\\d{3}-\\d{2}-\\d{4}$";
        return Pattern.matches(regex, ssn);
    }
}

// PART - 2

// 1

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double) a / b;
    }
}

class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtract() {
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void testDivide() {
        Calculator calculator = new Calculator();
        assertEquals(2.5, calculator.divide(5, 2), 0.001);
    }

    @Test
    void testDivideByZero() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }
}

//2

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtils {
    public String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        String cleanedStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedStr = new StringBuilder(cleanedStr).reverse().toString();
        return cleanedStr.equals(reversedStr);
    }

    public String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }
}

class StringUtilsTest {

    @Test
    void testReverse() {
        StringUtils utils = new StringUtils();
        assertEquals("dlrow", utils.reverse("world"));
        assertNull(utils.reverse(null));
        assertEquals("", utils.reverse(""));
    }

    @Test
    void testIsPalindrome() {
        StringUtils utils = new StringUtils();
        assertTrue(utils.isPalindrome("madam"));
        assertTrue(utils.isPalindrome("Racecar"));
        assertTrue(utils.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(utils.isPalindrome("hello"));
        assertFalse(utils.isPalindrome(null));
        assertTrue(utils.isPalindrome(""));
    }

    @Test
    void testToUpperCase() {
        StringUtils utils = new StringUtils();
        assertEquals("HELLO", utils.toUpperCase("hello"));
        assertEquals("WORLD", utils.toUpperCase("World"));
        assertEquals("", utils.toUpperCase(""));
        assertNull(utils.toUpperCase(null));
    }
}

//3

        import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ListManager {
    public void addElement(List<Integer> list, int element) {
        list.add(element);
    }

    public boolean removeElement(List<Integer> list, int element) {
        return list.remove(Integer.valueOf(element));
    }

    public int getSize(List<Integer> list) {
        return list.size();
    }
}

class ListManagerTest {

    @Test
    void testAddElement() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        manager.addElement(list, 10);
        assertTrue(list.contains(10));
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveElement() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        manager.removeElement(list, 10);
        assertFalse(list.contains(10));
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveNonexistentElement() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        list.add(10);
        assertFalse(manager.removeElement(list, 20));
        assertEquals(1, list.size());
    }

    @Test
    void testGetSize() {
        ListManager manager = new ListManager();
        List<Integer> list = new ArrayList<>();
        assertEquals(0, manager.getSize(list));
        list.add(10);
        assertEquals(1, manager.getSize(list));
        list.add(20);
        assertEquals(2, manager.getSize(list));
        manager.removeElement(list, 10);
        assertEquals(1, manager.getSize(list));
    }
}

//4

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionTester {
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double) a / b;
    }
}

class ExceptionHandlingTest {

    @Test
    void testDivideByZeroThrowsException() {
        ExceptionTester tester = new ExceptionTester();
        assertThrows(ArithmeticException.class, () -> tester.divide(10, 0), "Division by zero should throw ArithmeticException");
    }

    @Test
    void testDivideWithoutException() {
        ExceptionTester tester = new ExceptionTester();
        assertEquals(2.0, tester.divide(4, 2), 0.001);
    }
}

//5

        import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnection {
    private boolean connected = false;

    public void connect() {
        System.out.println("Connecting to database...");
        connected = true;
    }

    public void disconnect() {
        System.out.println("Disconnecting from database...");
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }
}

class DatabaseConnectionTest {

    private DatabaseConnection dbConnection;

    @BeforeEach
    void setUp() {
        dbConnection = new DatabaseConnection();
        dbConnection.connect();
    }

    @AfterEach
    void tearDown() {
        dbConnection.disconnect();
    }

    @Test
    void testConnectionIsEstablished() {
        assertTrue(dbConnection.isConnected());
    }

    @Test
    void testAnotherOperationRequiringConnection() {
        assertTrue(dbConnection.isConnected());
        // Simulate some database operation
        System.out.println("Performing database operation...");
    }
}

//6

        import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class NumberChecker {
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
}

class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6})
    void testIsEven_True(int number) {
        NumberChecker checker = new NumberChecker();
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 9, 11})
    void testIsEven_False(int number) {
        NumberChecker checker = new NumberChecker();
        assertFalse(checker.isEven(number));
    }
}

//7

        import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

class PerformanceTester {
    public String longRunningTask() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Task Completed";
    }

    public String quickTask() {
        return "Task Completed Quickly";
    }
}

class TimeoutTest {

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testLongRunningTaskExceedsTimeout() {
        PerformanceTester tester = new PerformanceTester();
        tester.longRunningTask(); // This test should fail due to timeout
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testQuickTaskWithinTimeout() {
        PerformanceTester tester = new PerformanceTester();
        tester.quickTask(); // This test should pass
    }
}

//8

        import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessor {
    public void writeToFile(String filename, String content) throws IOException {
        Files.writeString(Path.of(filename), content);
    }

    public String readFromFile(String filename) throws IOException {
        return Files.readString(Path.of(filename));
    }
}

class FileProcessorTest {

    @TempDir
    Path tempDir; // JUnit provides a temporary directory

    private Path testFile;
    private FileProcessor fileProcessor;

    @AfterEach
    void cleanUp() throws IOException {
        // Ensure the file is deleted after each test
        if (testFile != null && Files.exists(testFile)) {
            Files.delete(testFile);
        }
    }

    @Test
    void testWriteAndReadFromFile() throws IOException {
        fileProcessor = new FileProcessor();
        String filename = tempDir.resolve("test_write_read.txt").toString();
        String content = "Hello, JUnit File Testing!";
        testFile = Path.of(filename);

        fileProcessor.writeToFile(filename, content);
        assertTrue(Files.exists(testFile));

        String readContent = fileProcessor.readFromFile(filename);
        assertEquals(content, readContent);
    }

    @Test
    void testFileExistsAfterWriting() throws IOException {
        fileProcessor = new FileProcessor();
        String filename = tempDir.resolve("test_exists.txt").toString();
        String content = "Check existence";
        testFile = Path.of(filename);

        fileProcessor.writeToFile(filename, content);
        assertTrue(Files.exists(testFile));
    }

    @Test
    void testReadFromFileThrowsIOExceptionWhenNotExists() {
        fileProcessor = new FileProcessor();
        String filename = tempDir.resolve("non_existent_file.txt").toString();

        assertThrows(IOException.class, () -> fileProcessor.readFromFile(filename));
    }
}

//1

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false; // Insufficient funds or invalid amount
    }

    public double getBalance() {
        return balance;
    }
}

class BankAccountTest {

    @Test
    void testDepositIncreasesBalance() {
        BankAccount account = new BankAccount(100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testDepositNegativeAmountDoesNothing() {
        BankAccount account = new BankAccount(100.0);
        account.deposit(-50.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testWithdrawDecreasesBalance() {
        BankAccount account = new BankAccount(100.0);
        assertTrue(account.withdraw(50.0));
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientFundsFails() {
        BankAccount account = new BankAccount(100.0);
        assertFalse(account.withdraw(150.0));
        assertEquals(100.0, account.getBalance()); // Balance should remain unchanged
    }

    @Test
    void testWithdrawNegativeAmountFails() {
        BankAccount account = new BankAccount(100.0);
        assertFalse(account.withdraw(-50.0));
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testGetBalance() {
        BankAccount account = new BankAccount(200.0);
        assertEquals(200.0, account.getBalance());
    }
}

//2

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidator {
    public boolean isValid(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return hasUppercase && hasDigit;
    }
}

class PasswordValidatorTest {

    @Test
    void testValidPassword() {
        PasswordValidator validator = new PasswordValidator();
        assertTrue(validator.isValid("Password123"));
        assertTrue(validator.isValid("MyLongPassword456"));
    }

    @Test
    void testPasswordTooShort() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("Short1"));
        assertFalse(validator.isValid("Pwd7!"));
    }

    @Test
    void testPasswordMissingUppercase() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("password123"));
        assertFalse(validator.isValid("nodigitbutlongenought"));
    }

    @Test
    void testPasswordMissingDigit() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("PasswordWithoutDigit"));
        assertFalse(validator.isValid("ANOTHERLONGPASSWORD"));
    }

    @Test
    void testPasswordMissingUppercaseAndDigit() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("toolongbutnoworld"));
    }

    @Test
    void testNullPassword() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid(null));
    }

    @Test
    void testEmptyPassword() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid(""));
    }
}

//3

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverter {
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
}

class TemperatureConverterTest {

    private static final double DELTA = 0.001; // Tolerance for double comparisons

    @Test
    void testCelsiusToFahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();
        assertEquals(32.0, converter.celsiusToFahrenheit(0.0), DELTA);
        assertEquals(212.0, converter.celsiusToFahrenheit(100.0), DELTA);
        assertEquals(68.0, converter.celsiusToFahrenheit(20.0), DELTA);
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40.0), DELTA); // -40C is -40F
    }

    @Test
    void testFahrenheitToCelsius() {
        TemperatureConverter converter = new TemperatureConverter();
        assertEquals(0.0, converter.fahrenheitToCelsius(32.0), DELTA);
        assertEquals(100.0, converter.fahrenheitToCelsius(212.0), DELTA);
        assertEquals(20.0, converter.fahrenheitToCelsius(68.0), DELTA);
        assertEquals(-40.0, converter.fahrenheitToCelsius(-40.0), DELTA); // -40F is -40C
    }
}

//4

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

        import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class DateFormatter {
    public String formatDate(String inputDate) {
        if (inputDate == null) {
            throw new IllegalArgumentException("Input date cannot be null");
        }
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd", e);
        }
    }
}

class DateFormatterTest {

    @Test
    void testFormatValidDate() {
        DateFormatter formatter = new DateFormatter();
        assertEquals("31-12-2023", formatter.formatDate("2023-12-31"));
        assertEquals("01-01-2000", formatter.formatDate("2000-01-01"));
    }

    @Test
    void testFormatInvalidDateThrowsException() {
        DateFormatter formatter = new DateFormatter();
        assertThrows(IllegalArgumentException.class, () -> formatter.formatDate("31/12/2023")); // Wrong separator
        assertThrows(IllegalArgumentException.class, () -> formatter.formatDate("2023-13-01")); // Invalid month
        assertThrows(IllegalArgumentException.class, () -> formatter.formatDate("2023-12-32")); // Invalid day
        assertThrows(IllegalArgumentException.class, () -> formatter.formatDate("2023-12-3")); // Wrong day format
        assertThrows(IllegalArgumentException.class, () -> formatter.formatDate("abc-de-fghi")); // Non-digit input
    }

    @Test
    void testFormatNullDateThrowsException() {
        DateFormatter formatter = new DateFormatter();
        assertThrows(IllegalArgumentException.class, () -> formatter.formatDate(null));
    }
}

//5

        import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRegistration {

    public void registerUser(String username, String email, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (email == null || !email.contains("@")) { // Simple email validation
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password == null || password.length() < 6) { // Simple password validation
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        // Simulate successful registration
        System.out.println("User registered successfully: " + username);
    }
}

class UserRegistrationTest {

    @Test
    void testValidUserRegistration() {
        UserRegistration registration = new UserRegistration();
        assertDoesNotThrow(() -> registration.registerUser("john_doe", "john@example.com", "securepwd123"));
    }

    @Test
    void testRegistrationWithEmptyUsernameThrowsException() {
        UserRegistration registration = new UserRegistration();
        assertThrows(IllegalArgumentException.class, () -> registration.registerUser("", "john@example.com", "securepwd123"));
    }

    @Test
    void testRegistrationWithNullUsernameThrowsException() {
        UserRegistration registration = new UserRegistration();
        assertThrows(IllegalArgumentException.class, () -> registration.registerUser(null, "john@example.com", "securepwd123"));
    }

    @Test
    void testRegistrationWithInvalidEmailThrowsException() {
        UserRegistration registration = new UserRegistration();
        assertThrows(IllegalArgumentException.class, () -> registration.registerUser("john_doe", "johnatexample.com", "securepwd123")); // Missing @
        assertThrows(IllegalArgumentException.class, () -> registration.registerUser("john_doe", null, "securepwd123")); // Null email
    }

    @Test
    void testRegistrationWithShortPasswordThrowsException() {
        UserRegistration registration = new UserRegistration();
        assertThrows(IllegalArgumentException.class, () -> registration.registerUser("john_doe", "john@example.com", "short")); // Less than 6 chars
    }

    @Test
    void testRegistrationWithNullPasswordThrowsException() {
        UserRegistration registration = new UserRegistration();
        assertThrows(IllegalArgumentException.class, () -> registration.registerUser("john_doe", "john@example.com", null));
    }
}

