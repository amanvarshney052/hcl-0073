package Collection_Framework;

import java.util.PriorityQueue;
import java.util.Queue;

public class Queue_impl_priorityQueue {
    public static void main(String[] args) {

        Queue<Integer> queue = new PriorityQueue<>();

        queue.offer(30);
        queue.offer(10);
        queue.offer(40);
        queue.offer(20);

        System.out.println(queue);
        queue.poll();
    }
}
