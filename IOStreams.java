import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class IOStreams {
    // 1. File Handling - Read and Write a Text File
    public static void copyTextFile(String sourceFile, String destinationFile) {
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }
            System.out.println("File copied successfully from " + sourceFile + " to " + destinationFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Source file '" + sourceFile + "' not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. Buffered Streams - Efficient File Copy
    private static final int BUFFER_SIZE = 4096;

    public static void bufferedCopy(String source, String dest) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unbufferedCopy(String source, String dest) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(dest)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compareBufferedAndUnbufferedCopy(String sourceFile, String destFileBuffered, String destFileUnbuffered, long fileSize) {
        createLargeFile(sourceFile, fileSize);

        long startTime, endTime;
        double bufferedTime, unbufferedTime;

        startTime = System.nanoTime();
        bufferedCopy(sourceFile, destFileBuffered);
        endTime = System.nanoTime();
        bufferedTime = (endTime - startTime) / 1_000_000.0;
        System.out.println("Buffered Copy Time: " + bufferedTime + " ms");

        startTime = System.nanoTime();
        unbufferedCopy(sourceFile, destFileUnbuffered);
        endTime = System.nanoTime();
        unbufferedTime = (endTime - startTime) / 1_000_000.0;
        System.out.println("Unbuffered Copy Time: " + unbufferedTime + " ms");

        System.out.println("Performance Comparison:");
        if (bufferedTime > 0) {
            System.out.println("Buffered is " + (unbufferedTime / bufferedTime) + " times faster than Unbuffered");
        } else {
            System.out.println("Buffered copy took no time.");
        }
    }

    private static void createLargeFile(String filename, long sizeInBytes) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] buffer = new byte[1024];
            long bytesWritten = 0;
            while (bytesWritten < sizeInBytes) {
                int bytesToWrite = (int) Math.min(buffer.length, sizeInBytes - bytesWritten);
                fos.write(buffer, 0, bytesToWrite);
                bytesWritten += bytesToWrite;
            }
            System.out.println("Created large file: " + filename + " (" + sizeInBytes + " bytes)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. Read User Input from Console
    public static void getUserInfoToFile(String outputFile) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             FileWriter fw = new FileWriter(outputFile)) {
            System.out.print("Enter your name: ");
            String name = br.readLine();
            System.out.print("Enter your age: ");
            String ageStr = br.readLine();
            int age = Integer.parseInt(ageStr);
            System.out.print("Enter your favorite programming language: ");
            String language = br.readLine();

            fw.write("Name: " + name + "\n");
            fw.write("Age: " + age + "\n");
            fw.write("Language: " + language + "\n");
            System.out.println("Data saved to " + outputFile);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid age format. Please enter a number.");
        }
    }

    // 4. Serialization - Save and Retrieve an Object
    public static class Employee implements Serializable {
        private static final long serialVersionUID = 1L;
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

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Dept: " + department + ", Salary: " + salary;
        }
    }

    public static void serializeEmployees(List<Employee> employees, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
            System.out.println("Serialized employees to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Employee> deserializeEmployees(String filename) {
        List<Employee> employees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Deserialized employees from " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // 5. ByteArray Stream - Convert Image to ByteArray
    public static byte[] readImageToByteArray(String imageFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(imageFile);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    public static void writeByteArrayToImage(byte[] imageBytes, String newImageFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(newImageFile);
             ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bais.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    public static boolean areFilesIdentical(String file1, String file2) throws IOException {
        byte[] bytes1 = Files.readAllBytes(Paths.get(file1));
        byte[] bytes2 = Files.readAllBytes(Paths.get(file2));
        return Arrays.equals(bytes1, bytes2);
    }

    public static void convertImageToByteArrayAndBack(String imageFile, String newImageFile) {
        try {
            byte[] imageBytes = readImageToByteArray(imageFile);
            writeByteArrayToImage(imageBytes, newImageFile);
            boolean filesIdentical = areFilesIdentical(imageFile, newImageFile);
            System.out.println("Files are identical: " + filesIdentical);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 6. Filter Streams - Convert Uppercase to Lowercase
    public static void convertUppercaseToLowercase(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            int c;
            while ((c = br.read()) != -1) {
                char ch = (char) c;
                if (Character.isUpperCase(ch)) {
                    ch = Character.toLowerCase(ch);
                }
                bw.write(ch);
            }
            System.out.println("File converted successfully. Check " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 7. Data Streams - Store and Retrieve Primitive Data
    private static final String DATA_FILE = "student_data.dat";

    public static void storeStudentData(int rollNumber, String name, double gpa) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(DATA_FILE, true))) {
            dos.writeInt(rollNumber);
            dos.writeUTF(name);
            dos.writeDouble(gpa);
            System.out.println("Stored student data: " + rollNumber + ", " + name + ", " + gpa);
        }
    }

    public static void retrieveStudentData() throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(DATA_FILE))) {
            while (dis.available() > 0) {
                int rollNumber = dis.readInt();
                String name = dis.readUTF();
                double gpa = dis.readDouble();
                System.out.println("Retrieved student data: " + rollNumber + ", " + name + ", " + gpa);
            }
        } catch (EOFException e) {
            System.out.println("End of student data reached.");
        }
    }

    public static void processStudentData() {
        try {
            storeStudentData(101, "Alice", 3.85);
            storeStudentData(102, "Bob", 3.70);
            retrieveStudentData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 8. Piped Streams - Inter-Thread Communication
    public static void demonstratePipedStreams() {
        try (PipedOutputStream pos = new PipedOutputStream();
             PipedInputStream pis = new PipedInputStream(pos)) {

            Thread writerThread = new Thread(new WriterThread(pos));
            Thread readerThread = new Thread(new ReaderThread(pis));

            writerThread.start();
            readerThread.start();

            try {
                writerThread.join();
                readerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Main thread finished.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class WriterThread implements Runnable {
        private PipedOutputStream pos;

        public WriterThread(PipedOutputStream pos) {
            this.pos = pos;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = "Message from writer: " + i + "\n";
                    pos.write(message.getBytes());
                    pos.flush();
                    Thread.sleep(500);
                }
                pos.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ReaderThread implements Runnable {
        private PipedInputStream pis;

        public ReaderThread(PipedInputStream pis) {
            this.pis = pis;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(pis))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Reader received: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 9. Read a Large File Line by Line
    public static void findErrorsInLargeFile(String filename, long fileSize) {
        createLargeFileWithError(filename, fileSize);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains("error")) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createLargeFileWithError(String filename, long sizeInBytes) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] buffer = new byte[1024];
            long bytesWritten = 0;
            while (bytesWritten < sizeInBytes) {
                int bytesToWrite = (int) Math.min(buffer.length, sizeInBytes - bytesWritten);
                for (int i = 0; i < bytesToWrite / 10; ++i) {
                    String s = "This is a line without error\n";
                    if (i == 5) {
                        s = "This line has an ERROR!!\n";
                    }
                    byte[] bytes = s.getBytes();
                    if (bytesWritten + bytes.length <= sizeInBytes) {
                        fos.write(bytes);
                        bytesWritten += bytes.length;
                    } else {
                        break;
                    }
                }
                if (bytesWritten >= sizeInBytes) {
                    break;
                }
            }
            System.out.println("Created large file: " + filename + " (" + sizeInBytes + " bytes) with some error lines.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 10. Count Words in a File
    public static void countTopWords(String filename) {
        createWordFile(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Map<String, Integer> wordCounts = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("[\\s\\p{Punct}]+");
                for (String word : words) {
                    if (word.isEmpty()) {
                        continue;
                    }
                    word = word.toLowerCase();
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCounts.entrySet());
            sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            System.out.println("Top 5 words in " + filename + ":");
            for (int i = 0; i < Math.min(5, sortedEntries.size()); i++) {
                Map.Entry<String, Integer> entry = sortedEntries.get(i);
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createWordFile(String filename) {
        try (PrintWriter pw = new PrintWriter(filename)) {
            String content = "The quick brown fox jumps over the lazy fox. The fox is quick.\n" +
                    "Brown fox, the lazy dog, and a quick fox.";
            pw.print(content);
            System.out.println("Created word file: " + filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
