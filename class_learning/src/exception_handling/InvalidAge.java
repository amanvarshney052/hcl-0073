package exception_handling;

public class InvalidAge extends Exception {
    InvalidAge(String message){
        super(message);
    }

    static void validage(int age) throws InvalidAge{
        if (age<18){
            throw new InvalidAge("age must be greater than 18");
        }
        System.out.println("Eligible");
    }

    public static void main(String[] args) {
        try{
            validage(16);
        } catch (InvalidAge e) {
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println("Vote dene ke liye age 18 above zaruri honda si");
        }
    }

}
