package Collection_Framework;

public class practice1 {

    @FunctionalInterface
    interface Calculator {
        double calculate(double a, double b);
    }

    abstract class Calculate implements Calculator {

    }

    public static void main(String[] args) {

        double a = 20;
        double b = 5;


        Calculator addition = (x, y) -> x + y;
        System.out.println("Addition: " + addition.calculate(a, b));


        Calculator subtraction = (x, y) -> x - y;
        System.out.println("Subtraction: " + subtraction.calculate(a, b));


        Calculator multiplication = (x, y) -> x * y;
        System.out.println("Multiplication: " + multiplication.calculate(a, b));


        Calculator division = (x, y) -> {
            if (y == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return x / y;
        };
        System.out.println("Division: " + division.calculate(a, b));
    }
}
