package Collection_Framework;

import java.util.*;

class Student implements Comparable<Student> {

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

    // Comparable: natural ordering by student ID
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Name: " + name
                + ", Age: " + age
                + ", Marks: " + marks;
    }
}

public class comparator_impl {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student(104, "Rohit", 22, 85.5));
        students.add(new Student(101, "Aman", 20, 92.0));
        students.add(new Student(103, "Rahul", 22, 78.5));
        students.add(new Student(102, "Priya", 21, 92.0));
        students.add(new Student(105, null, 19, 65.0));

        // 1. Display original list
        System.out.println("\n--- Original List ---");
        students.forEach(System.out::println);

        // 2. Sort by ID using Comparable
        Collections.sort(students);

        System.out.println("\n--- Sort by ID (Comparable) ---");
        students.forEach(System.out::println);

        // 3. Sort by age using anonymous inner class
        students.sort(new Comparator<Student>()   {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getAge(), s2.getAge());
            }
        });

        System.out.println("\n--- Sort by Age (Anonymous Comparator) ---");
        students.forEach(System.out::println);
        // 4. Sort by name using lambda expression
        students.sort((s1, s2) -> {
            if (s1.getName() == null) return s2.getName() == null ? 0 : 1;
            if (s2.getName() == null) return -1;
            return s1.getName().compareToIgnoreCase(s2.getName());
        });

        System.out.println("\n--- Sort by Name (Lambda) ---");
        students.forEach(System.out::println);

        // 5. Sort by marks in descending order
        students.sort(
                Comparator.comparingDouble(Student::getMarks).reversed()
        );

        System.out.println("\n--- Sort by Marks (Descending) ---");
        students.forEach(System.out::println);

        // 6. Sort by age, then name
        students.sort(
                Comparator.comparingInt(Student::getAge)
                        .thenComparing(
                                Student::getName,
                                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                        )
        );

        System.out.println("\n--- Sort by Age, Then Name ---");
        students.forEach(System.out::println);

        // 7. Sort by name with null values last
        students.sort(
                Comparator.comparing(
                        Student::getName,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
                )
        );

        System.out.println("\n--- Sort by Name (Nulls Last) ---");
        students.forEach(System.out::println);

        // 8. TreeSet with custom ordering
        TreeSet<Student> studentSet = new TreeSet<>(
                Comparator.comparingInt(Student::getAge)
                        .thenComparingInt(Student::getId)
        );

        studentSet.addAll(students);

        System.out.println("\n--- TreeSet: Age, Then ID ---");
        studentSet.forEach(System.out::println);

        // 9. Reverse natural ordering
        TreeSet<Integer> numbers =
                new TreeSet<>(Comparator.reverseOrder());

        numbers.add(10);
        numbers.add(40);
        numbers.add(20);
        numbers.add(30);

        System.out.println("\n--- Numbers in Descending Order ---");
        System.out.println(numbers);
    }
}
