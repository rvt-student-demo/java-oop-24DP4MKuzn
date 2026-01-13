package rvt;

import java.io.File;
import java.util.Scanner;

public class PasutijumuVesture {
    public static void main(String[] args) {
        File file = new File("data/orders.csv");

        Float totalSum = 0f;

        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(",");

                Integer quantity = Integer.parseInt(data[3]);
                Float price = Float.parseFloat(data[4]);

                Float sum = quantity * price;

                totalSum += sum;

                String formattedString = String.format(
                        "Pasūtījums #%s: %s pasūtīja %s x %s (%s) -> Kopā: %.2f",
                        data[0], data[1], data[2], data[3], data[4], sum);
                System.out.println(formattedString);

            }
        } catch (Exception e) {
            System.out.println("Failu kļūda: " + e.getMessage());
        }

        System.out.println("Kopējā pasūtījumu summa: " + totalSum + " EUR");
    }
}
