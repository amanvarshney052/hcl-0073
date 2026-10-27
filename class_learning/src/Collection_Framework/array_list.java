package Collection_Framework;


import java.util.ArrayList;

public class array_list {
    public static void main(String []args){
        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);
        arrayList.add(60);
        arrayList.add(70);
        System.out.println(arrayList.toString());
        arrayList.set(3,80);
        arrayList.set(5,90);
        System.out.println("Updated index 3 val :"+arrayList.get(3));
        System.out.println("Updated index 5 val :"+arrayList.get(5));
        System.out.println(arrayList.toString());

    }

}
