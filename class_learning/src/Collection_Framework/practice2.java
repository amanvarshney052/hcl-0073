package Collection_Framework;

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
    }
}
