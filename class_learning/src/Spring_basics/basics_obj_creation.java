package Spring_basics;

public class basics_obj_creation {
    static class objcreate{
        final static objcreate obj=new objcreate();
        private objcreate(){
            System.out.println("Obj created");
        }
        public static objcreate createinstance(){
            return obj;
        }
        void hello(){
            System.out.println("Say hello");
        }
    }
    public static void main(String[] args) {
        objcreate o1=objcreate.createinstance();
        objcreate o2=objcreate.createinstance();
        objcreate o3=objcreate.createinstance();

        System.out.println(o1==o2);

        o1.hello();
        o2.hello();
        o3.hello();

        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
        System.out.println(o3.hashCode());



    }
}
