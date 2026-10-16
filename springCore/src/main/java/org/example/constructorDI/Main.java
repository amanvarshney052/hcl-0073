package org.example.constructorDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        Employee emp1=applicationContext.getBean(Employee.class);
        emp1.getEmpAddress();

    }
}
