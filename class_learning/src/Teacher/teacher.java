package Teacher;
import Student.student;
import demo.OOPS;



public class teacher {
    class Animal {

        Animal() {
            System.out.println("Animal constructor");
        }
    }

    class Dog extends Animal {

        Dog() {
            super();
            System.out.println("Dog constructor");
        }
    }

    public static void main(String []args){
        student s1=new student();
        s1.name="Aman";
        s1.roll_no=24;
        System.out.println(s1.name);
        System.out.println(s1.roll_no);
        OOPS c2=new OOPS();
        OOPS c1=new OOPS(20);
        OOPS c3=new OOPS("Aman");
        OOPS c4=new OOPS("Aman VArshney",24);
        c4.display();


    }
}
