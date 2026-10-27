package Teacher;


//this()   → calls current class constructor
//super()  → calls parent class constructor

//this.x   → current object's variable
//super.x  → parent class variable

//this.m() → current class method
//super.m() → parent class method

//@Override → tells compiler that you're overriding a parent method


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
