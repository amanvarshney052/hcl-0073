package lambda_Expression;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

class Student {

    private int id;
    private String name;
    private int age;
    private double marks;

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Name: " + name
                + ", Age: " + age
                + ", Marks: " + marks;
    }
}

// Custom functional interface
@FunctionalInterface
interface StudentAction {
    void perform(Student student);
}

public class lambda_impl {
    public static void main(String[] args) {

        // 1. Create student collection
        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "Aman", 21, 90));
        students.add(new Student(2, "Rahul", 22, 75));
        students.add(new Student(3, "Priya", 20, 95));
        students.add(new Student(4, "Rohit", 23, 60));
        students.add(new Student(5, "Neha", 21, 85));

        // 2. Custom lambda expression
        StudentAction action =
                s -> System.out.println(s.getName());

        System.out.println("\n--- Custom Functional Interface ---");
        action.perform(students.get(0));

        // 3. Predicate: Check whether marks >= 80
        Predicate<Student> highScorer =
                s -> s.getMarks() >= 80;

        System.out.println("\n--- High Scorers ---");

        students.stream()
                .filter(highScorer)
                .forEach(System.out::println);

        // 4. Function: Convert student to a string
        Function<Student, String> studentDetails =
                s -> s.getName() + " scored " + s.getMarks();

        System.out.println("\n--- Student Details ---");

        students.stream()
                .map(studentDetails)
                .forEach(System.out::println);

        // 5. Consumer: Print student information
        Consumer<Student> printer =
                s -> System.out.println(s);

        System.out.println("\n--- Consumer ---");

        printer.accept(students.get(0));

        // 6. Supplier: Provide a message
        Supplier<String> welcomeMessage =
                () -> "Welcome to Student Management System";

        System.out.println("\n--- Supplier ---");
        System.out.println(welcomeMessage.get());

        // 7. BiFunction: Add marks of two students
        BiFunction<Student, Student, Double> totalMarks =
                (s1, s2) -> s1.getMarks() + s2.getMarks();

        System.out.println("\n--- BiFunction ---");

        System.out.println(
                "Total marks of Aman and Rahul: "
                        + totalMarks.apply(students.get(0), students.get(1))
        );

        // 8. Comparator: Sort by marks descending
        System.out.println("\n--- Sort by Marks Descending ---");

        students.stream()
                .sorted(Comparator.comparingDouble(Student::getMarks)
                        .reversed())
                .forEach(System.out::println);

        // 9. Sort by age, then name
        System.out.println("\n--- Sort by Age, Then Name ---");

        students.stream()
                .sorted(Comparator.comparingInt(Student::getAge)
                        .thenComparing(Student::getName))
                .forEach(System.out::println);

        // 10. Predicate composition: AND
        Predicate<Student> youngStudent =
                s -> s.getAge() <= 21;

        System.out.println("\n--- Young High Scorers ---");

        students.stream()
                .filter(highScorer.and(youngStudent))
                .forEach(System.out::println);

        // 11. Predicate composition: OR
        Predicate<Student> excellentMarks =
                s -> s.getMarks() >= 90;

        Predicate<Student> seniorStudent =
                s -> s.getAge() >= 22;

        System.out.println("\n--- Excellent Marks OR Senior ---");

        students.stream()
                .filter(excellentMarks.or(seniorStudent))
                .forEach(System.out::println);

        // 12. Predicate negate()
        System.out.println("\n--- Students Who Are Not High Scorers ---");

        students.stream()
                .filter(highScorer.negate())
                .forEach(System.out::println);

        // 13. Method reference
        System.out.println("\n--- Method Reference ---");

        students.forEach(System.out::println);

        // 14. Collect names into a list
        List<String> names = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("\n--- Student Names ---");
        System.out.println(names);

        // 15. Variable capture
        double passingMarks = 70;

        Predicate<Student> passed =
                s -> s.getMarks() >= passingMarks;

        System.out.println("\n--- Students Who Passed ---");

        students.stream()
                .filter(passed)
                .forEach(s ->
                        System.out.println(s.getName() + " passed")
                );
    }
}
