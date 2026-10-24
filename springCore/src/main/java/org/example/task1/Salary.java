package org.example.task1;

import org.springframework.stereotype.Component;

@Component
public class Salary {


    private double basic=50000;

    private final double daPercentage = 10;
    private final double hraPercentage = 20;

    public double getBasic() {
        return basic;
    }

    public double getDA() {
        return basic * daPercentage / 100;
    }

    public double getHRA() {
        return basic * hraPercentage / 100;
    }

    public double getGrossSalary() {
        return basic + getDA() + getHRA();
    }

    public void displaySalary() {
        System.out.println("Basic Salary: " + getBasic());
        System.out.println("DA: " + getDA());
        System.out.println("HRA: " + getHRA());
        System.out.println("Gross Salary: " + getGrossSalary());
    }

}
