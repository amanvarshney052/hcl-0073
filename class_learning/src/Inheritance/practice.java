package Inheritance;

public class practice {
    static class parent{
        void callparent(){
            System.out.println("Parent called");
        }
        void runparent(){
            System.out.println("Parent run");
        }
    }
    static class child extends parent{
        void callchild(){
            System.out.println("Child called");
        }

        //@Override
        void runparent(){
            System.out.println("Child Run");
        }
    }

    public static void main(String[] args) {
        child c1=new child();
        c1.callchild();
        c1.runparent();
        c1.callparent();
        parent p1=new parent();
        p1.runparent();
        p1.callparent();
        parent p2= new child();
        p2.callparent();
        p2.runparent();


    }


}
