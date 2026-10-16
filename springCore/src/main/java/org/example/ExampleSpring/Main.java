package org.example.ExampleSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);

        Employee emp1=applicationContext.getBean(Employee.class);
        Employee emp2=applicationContext.getBean(Employee.class);
        IO.println(emp1.hashCode());
        IO.println(emp2.hashCode());

        emp1.display();
    }
}
