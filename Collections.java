import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class Collections {
    public static <T> List<T> reverseList(List<T> list) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            T temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
        return list;
    }

    // Find Frequency of Elements
    public static Map<String, Integer> findFrequency(List<String> list) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        if (list != null) {
            for (String element : list) {
                frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
            }
        }
        return frequencyMap;
    }

    // Rotate Elements in a List
    public static <T> void rotateList(List<T> list, int n) {
        if (list == null) {
            return;
        }
        int size = list.size();
        n = n % size;
        Collections.rotate(list, n);
    }

    // Remove Duplicates While Preserving Order
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    // Find the Nth Element from the End
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class LinkedListHelper {
        Node head;

        public int findFromEnd(int n) {
            Node fast = head;
            Node slow = head;
            for (int i = 0; i < n; i++) {
                if (fast == null) {
                    throw new IllegalArgumentException("N is larger than the list size");
                }
                fast = fast.next;
            }
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }

        public void append(int newData) {
            Node newNode = new Node(newData);
            if (head == null) {
                head = newNode;
                return;
            }
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Set Interface

    // Check if Two Sets Are Equal
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        if (set1 == null || set2 == null) {
            return set1 == set2;
        }
        if (set1.size() != set2.size()) {
            return false;
        }
        return set1.containsAll(set2);
    }

    // Union and Intersection of Two Sets
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        return unionSet;
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);
        return intersectionSet;
    }

    // Symmetric Difference
    public static <T> Set<T> symmetricDifference(Set<T> set1, Set<T> set2) {
        Set<T> diff = new HashSet<>(set1);
        diff.addAll(set2);
        Set<T> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        diff.removeAll(intersection);
        return diff;
    }

    // Convert a Set to a Sorted List
    public static List<Integer> convertHashSetToSortedList(Set<Integer> integerSet) {
        if (integerSet == null || integerSet.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> integerList = new ArrayList<>(integerSet);
        Collections.sort(integerList);
        return integerList;
    }

    // Find Subsets
    public static <T> boolean isSubset(Set<T> set1, Set<T> set2) {
        if (set1 == null) {
            return true; // Empty set is a subset of any set
        }
        if (set2 == null) {
            return false; // A non-empty set cannot be a subset of a null set
        }
        return set2.containsAll(set1);
    }

    // Queue Interface

    // Reverse a Queue
    public static void reverseQueue(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        int front = queue.poll();
        reverseQueue(queue);
        queue.offer(front);
    }

    // Generate Binary Numbers Using a Queue
    public static List<String> generateBinaryNumbers(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer("1");
        result.add("1");

        for (int i = 1; i < n; i++) {
            String current = queue.poll();
            String s1 = current + "0";
            String s2 = current + "1";

            queue.offer(s1);
            queue.offer(s2);

            result.add(s1);
            if (result.size() < n) {
                result.add(s2);
            }
        }
        return result;
    }

    // Hospital Triage System (Patient class defined within main for brevity)
    public static class Patient {
        String name;
        int severity;

        public Patient(String name, int severity) {
            this.name = name;
            this.severity = severity;
        }

        public String getName() {
            return name;
        }

        public int getSeverity() {
            return severity;
        }

        @Override
        public String toString() {
            return "(" + name + ", " + severity + ")";
        }
    }

    public static void simulateTriage(List<Patient> patientsList) {
        PriorityQueue<Patient> triageQueue = new PriorityQueue<>(Comparator.comparingInt(Patient::getSeverity).reversed());
        triageQueue.addAll(patientsList);
        System.out.println("Treating patients based on severity:");
        while (!triageQueue.isEmpty()) {
            Patient nextPatient = triageQueue.poll();
            System.out.println("Treating: " + nextPatient.getName() + " (Severity: " + nextPatient.getSeverity() + ")");
        }
        System.out.println("Triage queue is now empty.");
    }

    // Implement a Stack Using Queues
    public static class StackUsingTwoQueues {
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();

        public void push(int x) {
            q2.offer(x);
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public Integer pop() {
            return q1.isEmpty() ? null : q1.poll();
        }

        public Integer top() {
            return q1.isEmpty() ? null : q1.peek();
        }

        public boolean isEmpty() {
            return q1.isEmpty();
        }
    }

    // Circular Buffer Simulation
    public static void simulateCircularBuffer(Queue<Integer> queue, int element, int size) {
        if (queue.size() == size) {
            queue.poll();
        }
        queue.offer(element);
    }

    // Map Interface

    // Word Frequency Counter
    public static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        String lowerCaseText = text.toLowerCase();
        Matcher matcher = pattern.matcher(lowerCaseText);
        while (matcher.find()) {
            String word = matcher.group();
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        return wordFrequencies;
    }

    public static Map<String, Integer> countWordFrequencyFromFile(String filePath) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String lowerCaseLine = line.toLowerCase();
                Matcher matcher = pattern.matcher(lowerCaseLine);
                while (matcher.find()) {
                    String word = matcher.group();
                    wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return wordFrequencies;
    }

    // Invert a Map
    public static <K, V> Map<V, List<K>> invertMap(Map<K, V> map) {
        Map<V, List<K>> invertedMap = new HashMap<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            invertedMap.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }
        return invertedMap;
    }

    // Find the Key with the Highest Value
    public static Optional<String> findKeyWithHighestValue(Map<String, Integer> map) {
        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    // Merge Two Maps
    public static Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> mergedMap = new HashMap<>(map1);
        map2.forEach((key, value) -> mergedMap.merge(key, value, Integer::sum));
        return mergedMap;
    }

    // Group Objects by Property
    public static class Employee {
        String name;
        Department department;

        public Employee(String name, Department department) {
            this.name = name;
            this.department = department;
        }

        public Department getDepartment() {
            return department;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Department {
        HR, IT, FINANCE
    }

    public static Map<Department, List<Employee>> groupByDepartment(List<Employee> employees) {
        Map<Department, List<Employee>> groupedByDepartment = new HashMap<>();
        for (Employee employee : employees) {
            groupedByDepartment.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>()).add(employee);
        }
        return groupedByDepartment;
    }

    // Insurance Policy Management System (Classes defined within main for brevity)
    public static class InsurancePolicy {
        String policyNumber;
        String policyholderName;
        LocalDate expiryDate;
        String coverageType;
        double premiumAmount;

        public InsurancePolicy(String policyNumber, String policyholderName, LocalDate expiryDate, String coverageType, double premiumAmount) {
            this.policyNumber = policyNumber;
            this.policyholderName = policyholderName;
            this.expiryDate = expiryDate;
            this.coverageType = coverageType;
            this.premiumAmount = premiumAmount;
        }

        public String getPolicyNumber() {
            return policyNumber;
        }

        public String getPolicyholderName() {
            return policyholderName;
        }

        public LocalDate getExpiryDate() {
            return expiryDate;
        }

        public String getCoverageType() {
            return coverageType;
        }

        public double getPremiumAmount() {
            return premiumAmount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InsurancePolicy that = (InsurancePolicy) o;
            return policyNumber.equals(that.policyNumber);
        }

        @Override
        public int hashCode() {
            return policyNumber.hashCode();
        }

        @Override
        public String toString() {
            return "Policy Number: " + policyNumber + ", Policyholder: " + policyholderName + ", Expiry: " + expiryDate + ", Coverage: " + coverageType + ", Premium: " + premiumAmount;
        }
    }

    public static class InsurancePolicyManager {
        private Set<InsurancePolicy> policiesHashSet = new HashSet<>();
        private Set<InsurancePolicy> policiesLinkedHashSet = new LinkedHashSet<>();
        private Set<InsurancePolicy> policiesTreeSet = new TreeSet<>(Comparator.comparing(InsurancePolicy::getExpiryDate));
        private Map<String, InsurancePolicy> policiesByNumber = new HashMap<>();

        public void addPolicy(InsurancePolicy policy) {
            policiesHashSet.add(policy);
            policiesLinkedHashSet.add(policy);
            policiesTreeSet.add(policy);
            policiesByNumber.put(policy.getPolicyNumber(), policy);
        }

        public Set<InsurancePolicy> getAllUniquePolicies() {
            return policiesHashSet;
        }

        public List<InsurancePolicy> getPoliciesExpiringSoon(int days) {
            LocalDate cutoffDate = LocalDate.now().plusDays(days);
            return policiesTreeSet.stream()
                    .filter(policy -> !policy.getExpiryDate().isAfter(cutoffDate))
                    .collect(Collectors.toList());
        }

        public List<InsurancePolicy> getPoliciesByCoverageType(String coverageType) {
            return policiesHashSet.stream()
                    .filter(policy -> policy.getCoverageType().equalsIgnoreCase(coverageType))
                    .collect(Collectors.toList());
        }

        public Set<String> findDuplicatePolicies() {
            Set<String> policyNumbers = new HashSet<>();
            Set<String> duplicates = new HashSet<>();
            for (InsurancePolicy policy : policiesHashSet) {
                if (!policyNumbers.add(policy.getPolicyNumber())) {
                    duplicates.add(policy.getPolicyNumber());
                }
            }
            return duplicates;
        }

        // Add methods for performance comparison if needed
    }

    // Voting System
    public static class VotingSystem {
        private Map<String, Integer> votes = new HashMap<>();
        private Map<String, Integer> orderedVotes = new LinkedHashMap<>();
        private Map<String, Integer> sortedResults = new TreeMap<>();

        public void castVote(String candidate) {
            votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
            orderedVotes.put(candidate, orderedVotes.getOrDefault(candidate, 0) + 1);
            sortedResults.put(candidate, sortedResults.getOrDefault(candidate, 0) + 1);
        }

        public Map<String, Integer> getVoteCounts() {
            return votes;
        }

        public Map<String, Integer> getOrderedVoteCounts() {
            return orderedVotes;
        }

        public Map<String, Integer> getSortedResults() {
            return sortedResults;
        }
    }
}
