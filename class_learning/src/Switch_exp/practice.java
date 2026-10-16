package Switch_exp;

public class practice {
    public static void main(String[] args) {
        String day = "Sunday";

        String typeStatement;  

        switch (day) {
            case "Saturday":
            case "Sunday":
                typeStatement = "Weekend";
                break;
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                typeStatement = "Weekday";
                break;
            default:
                typeStatement = "Invalid Day";
                break;
        }

        System.out.println("Day: " + day +" "+ typeStatement);


        String typeExpression = switch (day) {
            case "Saturday", "Sunday" -> "Weekend";
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
            default -> "Invalid Day";
        };

        System.out.println("Day: " + day + " " + typeExpression);
    }
}
