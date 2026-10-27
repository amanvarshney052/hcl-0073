package Collection_Framework;

import java.util.ArrayList;

public class Test1 {
    public static class student{
        String name;
        int roll_num;
        int marks;
        int age;
        student(String name,int roll_num, int marks,int age){
            this.name=name;
            this.age=age;
            this.marks=marks;
            this.roll_num=roll_num;
        }
    }
    public static void main(String[]args){
        ArrayList<student>list=new ArrayList<>();
        list.add(new student("Aman",22,98,21));
        list.add(new student("Ujjawal",27,21,24));
        list.add(new student("Aman Yadav",23,100,23));
        list.add(new student("Pradeep",26,35,28));
        list.add(new student("Prince",21,67,24));
        list.add(new student("Aman Upadhyay",12,98,29));
        list.add(new student("Adarsh",28,06,32));
        list.add(new student("Ankit",29,89,26));

//        for(student s:list){
//            System.out.println(s.name);
//        }
        list.sort((a,b)->Integer.compare(a.age,b.age));

        for(student s:list){
            System.out.println("Name : "+s.name+" Roll Number : "+s.roll_num+" Age : "+s.age+"  Marks : "+s.marks);
        }

    }
}
