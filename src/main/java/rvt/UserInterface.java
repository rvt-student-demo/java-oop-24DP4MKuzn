package rvt;

import java.util.Scanner;

public class UserInterface {

    private TodoList list;
    private Scanner scanner;

    public UserInterface(TodoList list, Scanner scanner) {
        this.list = list;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {
                System.out.print("To add: ");
                String task = scanner.nextLine();
                list.add(task);
            }

            if (command.equals("list")) {
                list.print();
            }

            if (command.equals("completed")) {
                System.out.print("Which task is completed? ");
                int id = Integer.valueOf(scanner.nextLine());
                list.remove(id);
            }
        }
    }
}
