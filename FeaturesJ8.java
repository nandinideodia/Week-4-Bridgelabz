//1

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class EmployeeDataProcessing {

    public static Map<String, Double> processEmployeeData(List<Employee> employees) {
        return employees.stream()
                .filter(e -> "Engineering".equals(e.getDepartment()) && e.getSalary() > 80000)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "Engineering", 90000),
                new Employee(2, "Bob", "Engineering", 75000),
                new Employee(3, "Charlie", "Sales", 85000),
                new Employee(4, "David", "Engineering", 100000),
                new Employee(5, "Eve", "HR", 60000)
        );

        Map<String, Double> averageSalaries = processEmployeeData(employees);
        System.out.println("Average Salary for Engineering Department (Filtered and Sorted): " + averageSalaries);
    }
}

//2

        import java.util.*;
        import java.util.stream.Collectors;

class Sale {
    int productId;
    int quantity;
    double price;

    public Sale(int productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }
}

class ProductSales {
    int productId;
    double totalRevenue;

    public ProductSales(int productId, double totalRevenue) {
        this.productId = productId;
        this.totalRevenue = totalRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public String toString() {
        return "ProductSales{" +
                "productId=" + productId +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}

public class ProductSalesAnalysis {

    public static List<ProductSales> analyzeSalesData(List<Sale> sales) {
        return sales.stream()
                .filter(s -> s.getQuantity() > 10)
                .collect(Collectors.groupingBy(Sale::getProductId,
                        Collectors.summingDouble(s -> s.getQuantity() * s.getPrice())))
                .entrySet().stream()
                .map(entry -> new ProductSales(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
                new Sale(101, 15, 10.0),
                new Sale(102, 8, 20.0),
                new Sale(101, 20, 10.0),
                new Sale(103, 12, 5.0),
                new Sale(102, 18, 20.0),
                new Sale(104, 5, 30.0),
                new Sale(103, 25, 5.0)
        );

        List<ProductSales> topProducts = analyzeSalesData(sales);
        System.out.println("Top 5 Products by Total Revenue: " + topProducts);
    }
}

//3

        import java.util.*;
        import java.util.stream.Collectors;

class Book {
    String title;
    String author;
    String genre;
    double rating;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }
}

class BookRecommendation {
    String title;
    double rating;

    public BookRecommendation(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "BookRecommendation{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                '}';
    }
}

public class BookRecommendations {

    public static List<List<BookRecommendation>> getRecommendedBooks(List<Book> books, int pageSize) {
        List<BookRecommendation> recommendedBooks = books.stream()
                .filter(b -> "Science Fiction".equals(b.getGenre()) && b.getRating() > 4.0)
                .map(b -> new BookRecommendation(b.getTitle(), b.getRating()))
                .sorted(Comparator.comparingDouble(BookRecommendation::getRating).reversed())
                .limit(10)
                .collect(Collectors.toList());

        List<List<BookRecommendation>> paginatedResults = new ArrayList<>();
        for (int i = 0; i < recommendedBooks.size(); i += pageSize) {
            int end = Math.min(i + pageSize, recommendedBooks.size());
            paginatedResults.add(new ArrayList<>(recommendedBooks.subList(i, end)));
        }
        return paginatedResults;
    }

    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Dune", "Frank Herbert", "Science Fiction", 4.5),
                new Book("Foundation", "Isaac Asimov", "Science Fiction", 4.2),
                new Book("1984", "George Orwell", "Dystopian", 4.8),
                new Book("Brave New World", "Aldous Huxley", "Dystopian", 4.1),
                new Book("The Martian", "Andy Weir", "Science Fiction", 4.6),
                new Book("Neuromancer", "William Gibson", "Science Fiction", 3.9),
                new Book("Snow Crash", "Neal Stephenson", "Science Fiction", 4.3),
                new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science Fiction", 4.7),
                new Book("Fahrenheit 451", "Ray Bradbury", "Dystopian", 4.0),
                new Book("Contact", "Carl Sagan", "Science Fiction", 4.4),
                new Book("Ender's Game", "Orson Scott Card", "Science Fiction", 4.6),
                new Book("Hyperion", "Dan Simmons", "Science Fiction", 4.5)
        );

        List<List<BookRecommendation>> paginatedRecommendations = getRecommendedBooks(books, 5);
        for (int i = 0; i < paginatedRecommendations.size(); i++) {
            System.out.println("Page " + (i + 1) + ": " + paginatedRecommendations.get(i));
        }
    }
}

//4

        import java.util.*;
        import java.util.stream.Collectors;

class Claim {
    String claimId;
    String policyNumber;
    double claimAmount;
    String claimDate;
    String status;

    public Claim(String claimId, String policyNumber, double claimAmount, String claimDate, String status) {
        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }
}

class PolicyClaimSummary {
    String policyNumber;
    double totalClaimAmount;
    double averageClaimAmount;

    public PolicyClaimSummary(String policyNumber, double totalClaimAmount, double averageClaimAmount) {
        this.policyNumber = policyNumber;
        this.totalClaimAmount = totalClaimAmount;
        this.averageClaimAmount = averageClaimAmount;
    }

    public double getTotalClaimAmount() {
        return totalClaimAmount;
    }

    @Override
    public String toString() {
        return "PolicyClaimSummary{" +
                "policyNumber='" + policyNumber + '\'' +
                ", totalClaimAmount=" + totalClaimAmount +
                ", averageClaimAmount=" + averageClaimAmount +
                '}';
    }
}

public class ClaimsAnalysis {

    public static List<PolicyClaimSummary> analyzeClaims(List<Claim> claims) {
        Map<String, List<Claim>> groupedClaims = claims.stream()
                .filter(c -> "Approved".equals(c.getStatus()) && c.getClaimAmount() > 5000)
                .collect(Collectors.groupingBy(Claim::getPolicyNumber));

        List<PolicyClaimSummary> policySummaries = groupedClaims.entrySet().stream()
                .map(entry -> {
                    String policyNumber = entry.getKey();
                    List<Claim> policyClaims = entry.getValue();
                    double totalClaimAmount = policyClaims.stream().mapToDouble(Claim::getClaimAmount).sum();
                    double averageClaimAmount = policyClaims.stream().mapToDouble(Claim::getClaimAmount).average().orElse(0.0);
                    return new PolicyClaimSummary(policyNumber, totalClaimAmount, averageClaimAmount);
                })
                .sorted(Comparator.comparingDouble(PolicyClaimSummary::getTotalClaimAmount).reversed())
                .limit(3)
                .collect(Collectors.toList());

        return policySummaries;
    }

    public static void main(String[] args) {
        List<Claim> claims = Arrays.asList(
                new Claim("C001", "P101", 7500.0, "2023-01-15", "Approved"),
                new Claim("C002", "P102", 4000.0, "2023-01-20", "Pending"),
                new Claim("C003", "P101", 12000.0, "2023-01-25", "Approved"),
                new Claim("C004", "P103", 6000.0, "2023-01-28", "Approved"),
                new Claim("C005", "P102", 9000.0, "2023-02-01", "Approved"),
                new Claim("C006", "P101", 3000.0, "2023-02-05", "Approved"),
                new Claim("C007", "P103", 15000.0, "2023-02-10", "Approved")
        );

        List<PolicyClaimSummary> topPolicies = analyzeClaims(claims);
        System.out.println("Top 3 Policies by Total Claim Amount: " + topPolicies);
    }
}

//5

        import java.util.*;
        import java.util.stream.Collectors;

class PolicyHolder {
    String holderId;
    String name;
    int age;
    String policyType;
    double premiumAmount;

    public PolicyHolder(String holderId, String name, int age, String policyType, double premiumAmount) {
        this.holderId = holderId;
        this.name = name;
        this.age = age;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyType() {
        return policyType;
    }

    public int getAge() {
        return age;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public String getHolderId() {
        return holderId;
    }

    public String getName() {
        return name;
    }
}

class RiskAssessment {
    String holderId;
    String name;
    double riskScore;
    String riskCategory;

    public RiskAssessment(String holderId, String name, double riskScore) {
        this.holderId = holderId;
        this.name = name;
        this.riskScore = riskScore;
        this.riskCategory = riskScore > 0.5 ? "High Risk" : "Low Risk";
    }

    public double getRiskScore() {
        return riskScore;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    @Override
    public String toString() {
        return "RiskAssessment{" +
                "holderId='" + holderId + '\'' +
                ", name='" + name + '\'' +
                ", riskScore=" + riskScore +
                ", riskCategory='" + riskCategory + '\'' +
                '}';
    }
}

public class PolicyHolderRiskAssessment {

    public static Map<String, List<RiskAssessment>> assessRisk(List<PolicyHolder> policyHolders) {
        List<RiskAssessment> riskAssessments = policyHolders.stream()
                .filter(ph -> "Life".equals(ph.getPolicyType()) && ph.getAge() > 60)
                .map(ph -> new RiskAssessment(ph.getHolderId(), ph.getName(), ph.getPremiumAmount() / ph.getAge()))
                .sorted(Comparator.comparingDouble(RiskAssessment::getRiskScore).reversed())
                .collect(Collectors.toList());

        return riskAssessments.stream()
                .collect(Collectors.groupingBy(RiskAssessment::getRiskCategory));
    }

    public static void main(String[] args) {
        List<PolicyHolder> policyHolders = Arrays.asList(
                new PolicyHolder("H001", "Alice", 65, "Life", 300.0),
                new PolicyHolder("H002", "Bob", 55, "Life", 200.0),
                new PolicyHolder("H003", "Charlie", 70, "Life", 400.0),
                new PolicyHolder("H004", "David", 62, "Life", 350.0),
                new PolicyHolder("H005", "Eve", 68, "Life", 250.0),
                new PolicyHolder("H006", "Frank", 58, "Auto", 150.0)
        );

        Map<String, List<RiskAssessment>> categorizedRisk = assessRisk(policyHolders);
        System.out.println("Categorized Risk Assessments: " + categorizedRisk);
    }
}

//6

        import java.util.*;
        import java.util.stream.Collectors;

class Transaction {
    String transactionId;
    String policyNumber;
    double amount;
    String transactionDate;
    boolean isFraudulent;

    public Transaction(String transactionId, String policyNumber, double amount, String transactionDate, boolean isFraudulent) {
        this.transactionId = transactionId;
        this.policyNumber = policyNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.isFraudulent = isFraudulent;
    }

    public boolean isFraudulent() {
        return isFraudulent;
    }

    public double getAmount() {
        return amount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }
}

class FraudSummary {
    String policyNumber;
    long fraudulentTransactionCount;
    double totalFraudAmount;

    public FraudSummary(String policyNumber, long fraudulentTransactionCount, double totalFraudAmount) {
        this.policyNumber = policyNumber;
        this.fraudulentTransactionCount = fraudulentTransactionCount;
        this.totalFraudAmount = totalFraudAmount;
    }

    public long getFraudulentTransactionCount() {
        return fraudulentTransactionCount;
    }

    public double getTotalFraudAmount() {
        return totalFraudAmount;
    }

    @Override
    public String toString() {
        return "FraudSummary{" +
                "policyNumber='" + policyNumber + '\'' +
                ", fraudulentTransactionCount=" + fraudulentTransactionCount +
                ", totalFraudAmount=" + totalFraudAmount +
                '}';
    }
}

public class FraudDetection {

    public static List<String> detectFraud(List<Transaction> transactions) {
        Map<String, List<Transaction>> fraudulentTransactionsByPolicy = transactions.stream()
                .filter(t -> t.isFraudulent() && t.getAmount() > 10000)
                .collect(Collectors.groupingBy(Transaction::getPolicyNumber));

        List<FraudSummary> fraudSummaries = fraudulentTransactionsByPolicy.entrySet().stream()
                .map(entry -> {
                    String policyNumber = entry.getKey();
                    List<Transaction> policyTransactions = entry.getValue();
                    long fraudulentCount = policyTransactions.size();
                    double totalFraudAmount = policyTransactions.stream().mapToDouble(Transaction::getAmount).sum();
                    return new FraudSummary(policyNumber, fraudulentCount, totalFraudAmount);
                })
                .collect(Collectors.toList());

        List<String> alerts = new ArrayList<>();
        for (FraudSummary summary : fraudSummaries) {
            if (summary.getFraudulentTransactionCount() > 5 || summary.getTotalFraudAmount() > 50000) {
                alerts.add("ALERT: Policy " + summary.getPolicyNumber() + " has " + summary.getFraudulentTransactionCount() +
                        " fraudulent transactions and a total fraud amount of $" + summary.getTotalFraudAmount());
            }
        }
        return alerts;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T001", "P201", 15000.0, "2023-03-01", true),
                new Transaction("T002", "P202", 8000.0, "2023-03-05", false),
                new Transaction("T003", "P201", 20000.0, "2023-03-10", true),
                new Transaction("T004", "P203", 12000.0, "2023-03-15", true),
                new Transaction("T005", "P201", 5000.0, "2023-03-20", true), // Amount < 10000
                new Transaction("T006", "P201", 25000.0, "2023-03-25", true),
                new Transaction("T007", "P203", 30000.0, "2023-03-28", true),
                new Transaction("T008", "P201", 18000.0, "2023-04-01", true),
                new Transaction("T009", "P201", 22000.0, "2023-04-05", true),
                new Transaction("T010", "P202", 11000.0, "2023-04-10", true),
                new Transaction("T011", "P201", 16000.0, "2023-04-15", true)
        );

        List<String> fraudAlerts = detectFraud(transactions);
        if (fraudAlerts.isEmpty()) {
            System.out.println("No fraud alerts generated.");
        } else {
            System.out.println("Fraud Alerts:");
            fraudAlerts.forEach(System.out::println);
        }
    }
}


//PART - 2
//
//        1

@FunctionalInterface
interface SumCalculator {
    int sum(int a, int b);
}

public class CustomFunctionalInterface {

    public static void main(String[] args) {
        SumCalculator sumLambda = (a, b) -> a + b;
        int result = sumLambda.sum(5, 10);
        System.out.println("Sum using lambda: " + result);
    }
}

//2

        import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilteringList {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Anna", "Charlie", "Andrew");
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Filtered names: " + filteredNames);
    }
}

//3

        import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

class Person {
    String name;
    int age;
    double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class SortingListObjects {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, 50000),
                new Person("Bob", 25, 60000),
                new Person("Charlie", 35, 70000)
        );
        people.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("Sorted people by age: " + people);
    }
}

//4

        import java.util.Arrays;
import java.util.List;

public class MapReduceOperation {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfSquaresOfEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * n)
                .sum();
        System.out.println("Sum of squares of even numbers: " + sumOfSquaresOfEvens);
    }
}

//5

        import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateComposition {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "grape", "orange", "pineapple", "kiwi");
        Predicate<String> lengthGreaterThan5 = s -> s.length() > 5;
        Predicate<String> containsSubstring = s -> s.contains("an");
        List<String> filteredWords = words.stream()
                .filter(lengthGreaterThan5.and(containsSubstring))
                .collect(Collectors.toList());
        System.out.println("Filtered words: " + filteredWords);
    }
}

//6

        import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerInterface {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        Consumer<String> printUppercase = s -> System.out.println(s.toUpperCase());
        names.forEach(printUppercase);
    }
}

//7

        import java.util.function.Function;

public class FunctionInterface {

    public static void main(String[] args) {
        Function<Double, Double> calculateCircleArea = radius -> Math.PI * radius * radius;
        double radius = 5.0;
        double area = calculateCircleArea.apply(radius);
        System.out.println("Area of the circle with radius " + radius + ": " + area);
    }
}

//8

        import java.util.function.BiFunction;

public class BiFunctionInterface {

    public static void main(String[] args) {
        BiFunction<String, String, String> concatenateWithSpace = (s1, s2) -> s1 + " " + s2;
        String result = concatenateWithSpace.apply("Hello", "World");
        System.out.println("Concatenated string: " + result);
    }
}
9
@FunctionalInterface
interface SquareCalculator {
    int calculateSquare(int number);

    default void printSquare(int number) {
        int square = calculateSquare(number);
        System.out.println("The square of " + number + " is " + square);
    }
}

public class CustomFunctionalInterfaceWithDefaultMethod {

    public static void main(String[] args) {
        SquareCalculator squareLambda = n -> n * n;
        squareLambda.printSquare(7);
    }
}

//10

        import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalHandling {

    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(10, 5, 20, 15);
        Optional<Integer> max1 = findMaximum(numbers1);
        max1.ifPresent(max -> System.out.println("Maximum in list 1: " + max));

        List<Integer> numbers2 = Arrays.asList();
        Optional<Integer> max2 = findMaximum(numbers2);
        max2.orElseGet(() -> {
            System.out.println("List is empty, no maximum found.");
            return null; // Or any appropriate default value
        });
    }

    public static Optional<Integer> findMaximum(List<Integer> numbers) {
        return numbers.stream()
                .max(Integer::compareTo);
    }
}


//POLICY MANAGEMENT SYSTEM


import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class InsurancePolicy {
    String policyNumber;
    String holderName;
    double premiumAmount;

    public InsurancePolicy(String policyNumber, String holderName, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.premiumAmount = premiumAmount;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", holderName='" + holderName + '\'' +
                ", premiumAmount=" + premiumAmount +
                '}';
    }
}

public class InsuranceProblem {

    public static void main(String[] args) {
        List<InsurancePolicy> policies = Arrays.asList(
                new InsurancePolicy("POL101", "Alice Smith", 1500.0),
                new InsurancePolicy("POL102", "Bob Johnson", 1100.0),
                new InsurancePolicy("POL103", "Charlie Brown", 1800.0),
                new InsurancePolicy("POL104", "David Davis", 900.0),
                new InsurancePolicy("POL105", "Eve Adams", 2200.0),
                new InsurancePolicy("POL106", "Alice Williams", 1300.0)
        );

        // 1. Filter Policies by Premium Amount
        List<InsurancePolicy> highPremiumPolicies = policies.stream()
                .filter(p -> p.getPremiumAmount() > 1200)
                .collect(Collectors.toList());
        System.out.println("Policies with premium > $1200: " + highPremiumPolicies);

        // 2. Sort Policies by Holder Name
        List<InsurancePolicy> sortedPoliciesByName = policies.stream()
                .sorted(Comparator.comparing(InsurancePolicy::getHolderName))
                .collect(Collectors.toList());
        System.out.println("Policies sorted by holder name: " + sortedPoliciesByName);

        // 3. Compute Total Premium
        double totalPremium = policies.stream()
                .mapToDouble(InsurancePolicy::getPremiumAmount)
                .sum();
        System.out.println("Total Premium: $" + totalPremium);

        // 4. Print Policy Details
        System.out.println("All Policy Details:");
        policies.forEach(System.out::println);

        // 5. Filter Policies by Premium Range
        List<InsurancePolicy> policiesInRange = policies.stream()
                .filter(p -> p.getPremiumAmount() >= 1000 && p.getPremiumAmount() <= 2000)
                .collect(Collectors.toList());
        System.out.println("Policies with premium between $1000 and $2000: " + policiesInRange);

        // 6. Find Policy with Highest Premium
        Optional<InsurancePolicy> highestPremiumPolicy = policies.stream()
                .max(Comparator.comparingDouble(InsurancePolicy::getPremiumAmount));
        highestPremiumPolicy.ifPresent(policy -> System.out.println("Policy with highest premium: " + policy));

        // 7. Group Policies by Holder Name Initial
        Map<Character, List<InsurancePolicy>> groupedByInitial = policies.stream()
                .collect(Collectors.groupingBy(p -> p.getHolderName().charAt(0)));
        System.out.println("Policies grouped by holder name initial: " + groupedByInitial);

        // 8. Compute Average Premium
        double averagePremium = policies.stream()
                .mapToDouble(InsurancePolicy::getPremiumAmount)
                .average()
                .orElse(0.0);
        System.out.println("Average Premium: $" + averagePremium);

        // 9. Sort Policies by Premium and Print
        System.out.println("Policies sorted by premium (ascending):");
        policies.stream()
                .sorted(Comparator.comparingDouble(InsurancePolicy::getPremiumAmount))
                .forEach(System.out::println);

        // 10. Check If Any Policy Exceeds a Certain Premium
        boolean anyExceeds = policies.stream()
                .anyMatch(p -> p.getPremiumAmount() > 2000);
        System.out.println("Is there any policy with premium > $2000? " + anyExceeds);

        // 11. Count Policies for Each Premium Range
        Map<String, Long> premiumRangeCounts = policies.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.getPremiumAmount() <= 1000) return "$0-$1,000";
                    else if (p.getPremiumAmount() <= 2000) return "$1,001-$2,000";
                    else return ">$2,000";
                }, Collectors.counting()));
        System.out.println("Policy counts by premium range: " + premiumRangeCounts);

        // 12. Extract Unique Holder Names
        List<String> uniqueHolderNames = policies.stream()
                .map(InsurancePolicy::getHolderName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique holder names: " + uniqueHolderNames);

        // 13. Find Policies by Holder Name Substring
        String substring = "Smith";
        List<InsurancePolicy> policiesWithSubstring = policies.stream()
                .filter(p -> p.getHolderName().contains(substring))
                .collect(Collectors.toList());
        System.out.println("Policies with holder name containing '" + substring + "': " + policiesWithSubstring);

        // 14. Create a Map of Policy Numbers to Premium Amounts
        Map<String, Double> policyPremiumMap = policies.stream()
                .collect(Collectors.toMap(InsurancePolicy::getPolicyNumber, InsurancePolicy::getPremiumAmount));
        System.out.println("Map of Policy Number to Premium Amount: " + policyPremiumMap);
    }
}

