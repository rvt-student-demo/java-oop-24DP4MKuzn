package OnlineShop;

import java.util.Scanner;
import java.util.Set;

public class Store {

    private Warehouse warehouse;
    private Scanner scanner;

    public Store(Warehouse warehouse, Scanner scanner) {
        this.warehouse = warehouse;
        this.scanner = scanner;
    }

    // the method that handles the customers visit to the store.
    public void shop(String customer) {
        ShoppingCart cart = new ShoppingCart();
        System.out.println("Welcome to the store " + customer);
        System.out.println("our selection:");

        for (String product : this.warehouse.products()) {
            System.out.println(product);
        }

        while (true) {
            System.out.print("What to put in the cart (press enter to go to the register): ");
            String product = scanner.nextLine();
            if (product.isEmpty()) {
                break;
            }

            Set<String> availableProducts = warehouse.products();
            if (availableProducts.contains(product)) {
                if (warehouse.stock(product) > 0) {
                    cart.add(product, warehouse.price(product));
                    warehouse.take(product);
                } else {
                    System.out.println("We're sorry, but the product is not in stock.");
                }
            } else {
                System.out.println("We're sorry, but the product is not available.");
            }

            // Add code here that adds the product to the cart,
            // If there is any in the warehouse, and reduces the stock in the warehouse
            // Don't touch any of the other code!
        }

        System.out.println("your shoppingcart contents:");
        cart.print();
        System.out.println("total: " + cart.price());
    }
}