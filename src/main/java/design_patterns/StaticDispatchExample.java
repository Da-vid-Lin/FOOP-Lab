package design_patterns;

public class StaticDispatchExample {

    // --- Class hierarchy ---
    static class Animal {
        void speak() {
            System.out.println("Animal speaks");
        }
    }

    static class Dog extends Animal {
        @Override
        void speak() {
            System.out.println("Dog barks");
        }
    }

    static class Cat extends Animal {
        @Override
        void speak() {
            System.out.println("Cat meows");
        }
    }

    // --- Overloaded method: single dispatch in action ---
    static class Trainer {
        void train(Animal a) {
            System.out.println("Training an Animal");
            a.speak();
        }

        void train(Dog d) {
            System.out.println("Training a Dog");
            d.speak();
        }

        void train(Cat c) {
            System.out.println("Training a Cat");
            c.speak();
        }
    }

    public static void main(String[] args) {
        Trainer trainer = new Trainer();

        Animal a1 = new Dog();
        Animal a2 = new Cat();
        Dog d = new Dog();
        Cat c = new Cat();

        System.out.println("=== Static dispatch demo ===");

        trainer.train(a1);   // which train(...) is chosen?
        trainer.train(a2);

        // These two calls use the exact declared types
        trainer.train(d);
        trainer.train(c);
    }
}

