package OnlineShop;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class Warehouse {
    private Map<String, Integer> products = new HashMap<>();
    private Map<String, Integer> availableStock = new HashMap<>();

    public Warehouse() {
    }

    public void addProduct(String product, int price, int stock) {
        products.put(product, price);
        availableStock.put(product, stock);
    }

    public int price(String product) {
        try {
            return products.get(product);
        } catch (Exception e) {
            return -99;
        }
    }

    public int stock(String product) {
        try {
            return availableStock.get(product);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean take(String product) {
        try {
            Integer stock = availableStock.get(product);
            if (stock > 0) {
                stock -= 1;
                availableStock.put(product, stock);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Set<String> products()
    {
        return products.keySet();
    }
}
