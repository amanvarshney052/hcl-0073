package seald_class;

sealed class Animal permits Dog, Cat {
    void sound() {
        System.out.println("Some sound");
    }
}

final class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

final class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class practice {
    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Cat();

        a1.sound();
        a2.sound();
    }
}
