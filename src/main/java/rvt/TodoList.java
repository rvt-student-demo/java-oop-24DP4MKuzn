package rvt;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

    ArrayList<String> todoList = new ArrayList<>();
    private final String filePath = "data\\orders.csv";

    public void loadFromFile() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                todoList.add(row);
            }
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public void writeToFile() {
        try (PrintWriter pWriter = new PrintWriter(filePath)) {
            for (int i = 0; i < todoList.size(); i++) {
                pWriter.println(todoList.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(String task) {
        todoList.add(task);
    }

    public void print() {
        for (int i = 0; i < todoList.size(); i++) {
            System.err.println((i + 1) + ": " + todoList.get(i));
        }
    }

    public void print(int taskIndex) {
        System.out.println(todoList.get(taskIndex));
    }

    public void remove(int number) {
        int index = number - 1;
        todoList.remove(index);
    }
}
