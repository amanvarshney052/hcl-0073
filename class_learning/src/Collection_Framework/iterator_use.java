package Collection_Framework;

import java.util.ArrayList;
import java.util.Iterator;

public class iterator_use {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        Iterator<Integer> itr = numbers.iterator();

        while (itr.hasNext()) {
            int num=itr.next();
            if(num==40){
                itr.remove();
            }

        }
        System.out.println(numbers);
    }
}
