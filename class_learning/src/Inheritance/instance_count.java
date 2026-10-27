package Inheritance;

class demo{
    static int count;
    demo(){
        count++;
    }
}

public class instance_count {



    public static void main(String[] args) {
        demo d1=new demo();
        demo d2=new demo();
        demo d3=new demo();
        demo d4=new demo();

        System.out.println("Total instance : "+demo.count);
    }
}
