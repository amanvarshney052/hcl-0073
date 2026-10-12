package Collection_Framework;

import java.util.LinkedList;
import java.util.Queue;

public class Queue_Impl_LinkedList {
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        queue.offer(40);

        System.out.println( queue);

        System.out.println( queue.peek()); // .peek() -first element ko dekhata hh

        System.out.println( queue.poll()); // .pool() -first element ko hatata hh

        queue.offer(10);
        System.out.println(queue);  // updated queue ko dhekhe


        queue.isEmpty();

        queue.size();
    }

}
