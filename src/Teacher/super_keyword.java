package Teacher;


//  this()- calls another constructor
//  super()- calls parent constructor
//  .this - Refers to the current object
//  .super - Accesses parent class members
public class super_keyword {
    static class Person {

        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class Student extends Person {

        int rollNo;

        Student(String name, int age, int rollNo) {

            super(name, age);

            this.rollNo = rollNo;
        }
    }
    public static void main(String[] args){
        Student s = new Student("Aman", 21, 101);
    }
}
