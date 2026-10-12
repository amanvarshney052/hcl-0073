package Collection_Framework;

import java.util.HashSet;
import java.util.Set;

public class Set_impl {
    static void main() {
        Set<Integer> set=new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(26);
        set.add(73);
        set.add(20);
        System.out.println(set);
        set.size();
        System.out.println( set.contains(26));
        System.out.println( set.contains(98));


    }
}
