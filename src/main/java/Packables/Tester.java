package Packables;

public class Tester {
    public static void main(String[] args) {
        Box box = new Box(10);

        box.Add(new Book("Fyodor Dostoevsky", "Crime and Punishment", 2));
        box.Add(new Book("Robert Martin", "Clean Code", 1));
        box.Add(new Book("Kent Beck", "Test Driven Development", 0.7));

        box.Add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
        box.Add(new CD("Wigwam", "Nuclear Nightclub", 1975));
        box.Add(new CD("Rendezvous Park", "Closer to Being Here", 2012));

        System.out.println(box);

        Box box1 = new Box(10);

        box1.Add(new Book("Fyodor Dostoevsky", "Crime and Punishment", 2));
        box1.Add(new Book("Robert Martin", "Clean Code", 1));
        box1.Add(new Book("Kent Beck", "Test Driven Development", 0.7));

        box1.Add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
        box1.Add(new CD("Wigwam", "Nuclear Nightclub", 1975));
        box1.Add(new CD("Rendezvous Park", "Closer to Being Here", 2012));

        Box box2 = new Box(4);

        box2.Add(box);
        box2.Add(box1);
        System.out.println(box2);
    }
}
