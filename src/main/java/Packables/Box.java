package Packables;

import java.util.ArrayList;

public class Box implements Packable {
    ArrayList<Packable> items = new ArrayList<>();
    private Float capacity;
    private double weight;

    public Box(float capacity) {
        this.capacity = capacity;
    }

    public void Add(Packable item) {
        CalculateWeight();

        double weightPostAdd = weight + item.getWeight();

        if (weightPostAdd > capacity) {
            System.out.println("Cannot add item! Box capacity is overflown!");
            return;
        }

        items.add(item);
    }

    public double getWeight() {
        CalculateWeight();
        return weight;
    }

    private void CalculateWeight() {
        weight = 0;

        for (Packable item : items) {
            weight += item.getWeight();
        }
    }

    public String toString() {
        return "Box: " + items.size() + " items, total weight " + getWeight() + " kg";
    }
}
