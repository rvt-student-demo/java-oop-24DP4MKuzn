package OnlineShop;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    Map<String, Item> products = new HashMap<>();

    ShoppingCart() {
    }

    public void add(String product, int price) {
        if (products.containsKey(product)) {
            Item productToUpdate = products.get(product);
            productToUpdate.increaseQuantity();
            products.put(product, productToUpdate);
        } else {
            Item item = new Item(product, 1, price);
            products.put(product, item);
        }
    }

    public int price() {
        int total = 0;

        for (Item item : products.values()) {
            total += item.price();
        }

        return total;
    }

    public void print() {
        for (Item item : products.values()) {
            System.out.println(item.toString());
        }
    }
}
