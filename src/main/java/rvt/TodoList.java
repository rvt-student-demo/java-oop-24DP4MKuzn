package rvt;

import java.util.ArrayList;

public class TodoList {

    ArrayList<String> todoList = new ArrayList<>();

    public void add(String task)
    {
        todoList.add(task);
    }

    public void print()
    {
        for (int i = 0; i < todoList.size(); i++)
            {
                System.err.println((i + 1) + ": " + todoList.get(i));
            }
    }

    public void print(int taskIndex)
    {
        System.out.println(todoList.get(taskIndex));
    }

    public void remove(int number)
    {
        int index = number - 1;
        todoList.remove(index);
    }
}
