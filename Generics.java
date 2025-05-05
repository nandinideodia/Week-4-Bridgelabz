public class Generics {
    abstract class WarehouseItem {}
    class Electronics extends WarehouseItem {}
    class Groceries extends WarehouseItem {}
    class Furniture extends WarehouseItem {}

    class Storage<T extends WarehouseItem> {
        private java.util.List<T> items = new java.util.ArrayList<>();
        public void addItem(T item) {
            items.add(item);
        }
        public T getItem(int index) {
            return items.get(index);
        }
        public java.util.List<T> getAllItems() {
            return items;
        }
    }

    class WarehouseSystem {
        public static void displayAllItems(java.util.List<? extends WarehouseItem> items) {
            for (WarehouseItem item : items) {
                System.out.println("Item: " + item.getClass().getSimpleName());
            }
        }

        public static void main(String[] args) {
            Storage<Electronics> electronicsStorage = new Storage<>();
            electronicsStorage.addItem(new Electronics());
            Storage<Groceries> groceriesStorage = new Storage<>();
            groceriesStorage.addItem(new Groceries());
            java.util.List<Storage<? extends WarehouseItem>> allStorages = java.util.Arrays.asList(electronicsStorage, groceriesStorage);
            java.util.List<WarehouseItem> allItems = new java.util.ArrayList<>();
            for (Storage<? extends WarehouseItem> storage : allStorages) {
                allItems.addAll(storage.getAllItems());
            }
            displayAllItems(allItems);
        }
    }

    interface ProductCategory {}
    enum BookCategory implements ProductCategory { FICTION, SCIENCE }
    enum ClothingCategory implements ProductCategory { CASUAL, FORMAL }
    enum GadgetCategory implements ProductCategory { MOBILE, LAPTOP }

    class Product<T extends ProductCategory> {
        private String name;
        private double price;
        private T category;
        public Product(String name, double price, T category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public T getCategory() { return category; }
        public void setPrice(double price) { this.price = price; }
    }

    class Marketplace {
        public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
            double discountedPrice = product.getPrice() * (1 - percentage / 100);
            product.setPrice(discountedPrice);
        }

        public static void main(String[] args) {
            Product<BookCategory> book = new Product<>("The Lord of the Rings", 25.00, BookCategory.FICTION);
            Product<ClothingCategory> shirt = new Product<>("Casual Shirt", 30.00, ClothingCategory.CASUAL);
            applyDiscount(book, 10.0);
            applyDiscount(shirt, 20.0);
            System.out.println(book.getName() + " Price: " + book.getPrice());
            System.out.println(shirt.getName() + " Price: " + shirt.getPrice());
        }
    }

    abstract class CourseType {}
    class ExamCourse extends CourseType {}
    class AssignmentCourse extends CourseType {}
    class ResearchCourse extends CourseType {}

    class Course<T extends CourseType> {
        private String name;
        private T evaluationType;
        public Course(String name, T evaluationType) {
            this.name = name;
            this.evaluationType = evaluationType;
        }
        public String getName() { return name; }
        public T getEvaluationType() { return evaluationType; }
    }

    class CourseManagementSystem {
        public static void displayCourseInfo(java.util.List<? extends CourseType> courses) {
            for (CourseType course : courses) {
                System.out.println("Course Type: " + course.getClass().getSimpleName());
            }
        }

        public static void main(String[] args) {
            java.util.List<Course<ExamCourse>> examCourses = java.util.Arrays.asList(new Course<>("Math", new ExamCourse()), new Course<>("Physics", new ExamCourse()));
            java.util.List<Course<AssignmentCourse>> assignmentCourses = java.util.Arrays.asList(new Course<>("History", new AssignmentCourse()), new Course<>("English", new AssignmentCourse()));
            java.util.List<Course<? extends CourseType>> allCourses = new java.util.ArrayList<>();
            allCourses.addAll(examCourses);
            allCourses.addAll(assignmentCourses);
            java.util.List<CourseType> courseTypes = new java.util.ArrayList<>();
            for (Course<? extends CourseType> course : allCourses) {
                courseTypes.add(course.getEvaluationType());
            }
            displayCourseInfo(courseTypes);
        }
    }

    interface MealPlan {}
    class VegetarianMeal implements MealPlan {}
    class VeganMeal implements MealPlan {}
    class KetoMeal implements MealPlan {}
    class HighProteinMeal implements MealPlan {}

    class Meal<T extends MealPlan> {
        private String name;
        private T planType;
        public Meal(String name, T planType) {
            this.name = name;
            this.planType = planType;
        }
        public String getName() { return name; }
        public T getPlanType() { return planType; }
    }

    class MealPlanGenerator {
        public static <T extends MealPlan> Meal<T> generateMealPlan(String preference) {
            if (preference.equalsIgnoreCase("Vegetarian")) {
                return (Meal<T>) new Meal<>("Vegetarian Delight", new VegetarianMeal());
            } else if (preference.equalsIgnoreCase("Vegan")) {
                return (Meal<T>) new Meal<>("Vegan Power Bowl", new VeganMeal());
            } else if (preference.equalsIgnoreCase("Keto")) {
                return (Meal<T>) new Meal<>("Keto Feast", new KetoMeal());
            } else if (preference.equalsIgnoreCase("High-Protein")) {
                return (Meal<T>) new Meal<>("Protein Packed Plate", new HighProteinMeal());
            } else {
                throw new IllegalArgumentException("Invalid meal preference");
            }
        }

        public static void main(String[] args) {
            Meal<VegetarianMeal> vegetarianMeal = generateMealPlan("Vegetarian");
            System.out.println("Generated Meal: " + vegetarianMeal.getName() + " (" + vegetarianMeal.getPlanType().getClass().getSimpleName() + ")");
            try {
                Meal<VeganMeal> invalidMeal = generateMealPlan("Paleo");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    abstract class JobRole {}
    class SoftwareEngineer extends JobRole {}
    class DataScientist extends JobRole {}
    class ProductManager extends JobRole {}

    class Resume<T extends JobRole> {
        private String candidateName;
        private T jobRole;
        public Resume(String candidateName, T jobRole) {
            this.candidateName = candidateName;
            this.jobRole = jobRole;
        }
        public String getCandidateName() { return candidateName; }
        public T getJobRole() { return jobRole; }
    }

    class ResumeScreeningSystem {
        public static void processResumes(java.util.List<? extends JobRole> jobRoles) {
            for (JobRole role : jobRoles) {
                System.out.println("Processing resumes for: " + role.getClass().getSimpleName());
            }
        }

        public static <T extends JobRole> void screenResume(Resume<T> resume) {
            System.out.println("Screening resume of " + resume.getCandidateName() + " for " + resume.getJobRole().getClass().getSimpleName());
        }

        public static void main(String[] args) {
            java.util.List<Resume<SoftwareEngineer>> softwareEngineerResumes = java.util.Arrays.asList(new Resume<>("Alice", new SoftwareEngineer()), new Resume<>("Bob", new SoftwareEngineer()));
            java.util.List<Resume<DataScientist>> dataScientistResumes = java.util.Arrays.asList(new Resume<>("Charlie", new DataScientist()));
            java.util.List<JobRole> targetRoles = java.util.Arrays.asList(new SoftwareEngineer(), new DataScientist());
            processResumes(targetRoles);
            for (Resume<SoftwareEngineer> resume : softwareEngineerResumes) {
                screenResume(resume);
            }
            for (Resume<DataScientist> resume : dataScientistResumes) {
                screenResume(resume);
            }
        }
    }
}
