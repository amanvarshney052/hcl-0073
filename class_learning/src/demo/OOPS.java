package demo;

public class OOPS {
    private String Full_name;
    private int roll;


    public OOPS(){
        System.out.println("Constructor OOPS called");
    }
    public OOPS(int num){
        System.out.println("Paramerized constructor called :"+num);
    }
    public OOPS(String Name){
        System.out.println("Name :"+Name);
    }
    public OOPS(String Full_name, int roll){
        this.Full_name=Full_name;
        this.roll=roll;

    }
    public void display(){
        System.out.println("Full Name= "+Full_name);
        System.out.println("Roll number= "+roll);
    }


}
