package Abstraction;

public class MultiInheritance_Interface {
    interface A{
        void callA();
    }
    interface B{
        void callB();
    }
    class c implements A,B{

        @Override
        public void callA() {
            System.out.println("A is called");
        }

        @Override
        public void callB() {
            System.out.println("B is caled");
        }
    }
    public void main(String []args){
        c c1=new c();
        c1.callA();
        c1.callB();
    }
}
