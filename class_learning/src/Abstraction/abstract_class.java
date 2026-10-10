package Abstraction;

//An abstract class can contain:
//        - Abstract methods — methods declared without a method body.
//        - Concrete methods — methods with a method body.
//        - Instance variables and constants.
//        - Constructors.
//        - Static methods.
//        - Other members permitted by Java's class rules.
// we can not create object of abstract class

public class abstract_class {
    abstract class A{
        abstract void m1();
        abstract void m2(int a);
    }
    class B extends A{
        @Override
        void m1(){
            System.out.println("Print M1!!!!!");
        }
        @Override
        void m2(int a){
            System.out.println("Print M2 value :"+a);
        }
    }

    public  void main(String[] args) {
        B b=new B();
        b.m1();
        b.m2(83);
    }
}



//Remember these rules for interviews:
//        1. An abstract class is declared using abstract.
//        2. An abstract class cannot be instantiated directly.
//        3. An abstract class can contain both abstract and concrete methods.
//        4. An abstract class can have constructors and instance variables.
//        5. An abstract method cannot have a body.
//        6. A concrete subclass must implement all inherited abstract methods, unless those methods have already been implemented.
//        7. If a subclass does not implement all inherited abstract methods, that subclass must also be declared abstract.
//        8. An abstract class can extend another class and can implement interfaces.
//        9. A class cannot be declared both abstract and final, because an abstract class is designed to be extended, while a final class cannot be subclassed.
//        10. An abstract method cannot be declared private, static, or final, because it must be eligible for implementation by a subclass.
//One more subtle point: an abstract class does not have to contain an abstract method. You can declare a class abstract simply to prevent direct instantiation and establish a common base type.
