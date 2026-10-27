package Collection_Framework;

import java.util.LinkedList;

public class linked_list {
    public static void main(String[] args) {
        LinkedList<String> tasks = new LinkedList<>();

        tasks.add("Study Java");
        tasks.add("Practice DSA");
        tasks.add("Build project");

        tasks.addFirst("Wake up");
        tasks.addLast("Sleep");

        System.out.println(tasks);

        tasks.removeFirst();
        tasks.removeLast();

        System.out.println(tasks);


    }
}
