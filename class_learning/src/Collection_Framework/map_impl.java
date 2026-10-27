package Collection_Framework;

import java.util.HashMap;
import java.util.Map;

public class map_impl {
    public static void main(String[] args) {
        HashMap<Integer,String>map=new HashMap<>();
        map.put(1,"Aman");
        map.put(2,"Ujjawal");
        map.put(3,"Pradeep");
        map.put(4,"Prince");
        for(int i:map.keySet()){
            System.out.println(i + " "+ map.get(i));
        }
        System.out.println(map.containsKey(3));
        System.out.println(map.keySet());
        System.out.println(map.containsValue("Aman"));
        map.forEach((k,v)-> System.out.println("Key : "+k+" "+"Value : "+v));
    }
}
