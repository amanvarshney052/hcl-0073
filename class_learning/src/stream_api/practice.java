package stream_api;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class practice {
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(13);
        list.add(17);
        list.add(93);
        list.add(83);
        list.add(19);
        list.add(15);
        list.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        ArrayList<String> list2=new ArrayList<>();
        list2.add("Aman");
        list2.add("Aman Yadav");
        list2.add("Ujjawal");
        list2.add("Adarsh");
        list2.add("Prince");
        list2.add("Pradeep");
        list2.stream()
                .filter(a->a.startsWith("A"))
                .forEach(System.out::println);


    }
}
