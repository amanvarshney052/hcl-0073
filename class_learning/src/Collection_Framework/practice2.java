package Collection_Framework;

import java.util.function.Predicate;

@FunctionalInterface
interface EvenChecker {
    boolean check();


}


public class practice2 {
    public static void main(String[] args) {
        int a=80;
        EvenChecker isEven=()->a%2==0;
        System.out.println(isEven.check() );
        EvenChecker isOdd=()->a%2!=0;
        System.out.println(isOdd.check());

        Predicate<Integer> IsEven = n -> n % 2 == 0;

        int n = 25;

        if (IsEven.test(n)) {
            System.out.println("Even number");
        } else {
            System.out.println("Odd number");
        }

    }
}
