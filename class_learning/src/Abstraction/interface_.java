package Abstraction;

//Important interface rules
//        - An interface cannot be instantiated directly.
//        - A class uses implements to implement an interface.
//        - An ordinary abstract interface method is implicitly public abstract.
//        - The implementing method must be public because it cannot reduce the visibility of the interface method.
//        - Interfaces can declare constants, default methods, static methods, and private helper methods.
//        - A class can implement multiple interfaces.

public class interface_ {
    interface payment{
        void pay();
    }
    class upipay implements payment{
        @Override
        public void pay(){
            System.out.println("Paymemt by UPI");
        }
    }
    class card implements payment {
        @Override
        public void pay(){
            System.out.println("Payment by CARD");
        }
    }
    public void main(String[] args){
        card c=new card();
        c.pay();
        upipay u=new upipay();
        u.pay();
    }

}
