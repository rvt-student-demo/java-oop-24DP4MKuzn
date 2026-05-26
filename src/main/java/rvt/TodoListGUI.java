package rvt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListGUI extends JFrame {

    private TodoList list;
    private JList<String> taskJList;
    private DefaultListModel<String> listModel;
    private JTextField taskInput;
    private JButton addButton;
    private JButton removeButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton clearButton;

    public TodoListGUI() {
        list = new TodoList();
        initializeGUI();
    }

    private void initializeGUI() {
        // frame properties
        setTitle("Todo List - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // title label
        JLabel titleLabel = new JLabel("Mani Uzdevumi (My Tasks)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // center panel - Task list
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(5, 5));

        listModel = new DefaultListModel<>();
        taskJList = new JList<>(listModel);
        taskJList.setFont(new Font("Arial", Font.PLAIN, 12));
        taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskJList);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // bottom panel - Input and buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(5, 5));

        // input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        JLabel inputLabel = new JLabel("Jaunais uzdevums (New task):");
        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(inputLabel, BorderLayout.WEST);
        inputPanel.add(taskInput, BorderLayout.CENTER);

        bottomPanel.add(inputPanel, BorderLayout.NORTH);

        // buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        addButton = new JButton("Pievienot (Add)");
        removeButton = new JButton("Noņemt (Remove)");
        clearButton = new JButton("Notīrīt (Clear)");
        loadButton = new JButton("Ielādēt (Load)");
        saveButton = new JButton("Saglabāt (Save)");

        addButton.setFont(new Font("Arial", Font.PLAIN, 11));
        removeButton.setFont(new Font("Arial", Font.PLAIN, 11));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 11));
        loadButton.setFont(new Font("Arial", Font.PLAIN, 11));
        saveButton.setFont(new Font("Arial", Font.PLAIN, 11));

        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);

        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // main panel to frame
        add(mainPanel);

        // action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllTasks();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTasks();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTasks();
            }
        });

        // allow Enter key to add task
        taskInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            list.add(task);
            listModel.addElement(task);
            taskInput.setText("");
            taskInput.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Lūdzu ievadiet uzdevumu (Please enter a task)", 
                "Kļūda (Error)", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeTask() {
        int selectedIndex = taskJList.getSelectedIndex();
        if (selectedIndex != -1) {
            list.remove(selectedIndex + 1);
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Lūdzu atlasiet uzdevumu (Please select a task)", 
                "Kļūda (Error)", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearAllTasks() {
        int result = JOptionPane.showConfirmDialog(this,
            "Vai jūs pārliecināti? (Are you sure?)",
            "Notīrīt visus uzdevumus (Clear all tasks)",
            JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            listModel.clear();
            list.getTodoList().clear();
        }
    }

    private void loadTasks() {
        try {
            list.loadFromFile();
            listModel.clear();
            for (String task : list.getTodoList()) {
                listModel.addElement(task);
            }
            JOptionPane.showMessageDialog(this,
                "Uzdevumi ielādēti (Tasks loaded)",
                "Veiksmīgi (Success)",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Kļūda ielādējot failu: " + e.getMessage(),
                "Kļūda (Error)",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveTasks() {
        try {
            list.writeToFile();
            JOptionPane.showMessageDialog(this,
                "Uzdevumi saglabāti (Tasks saved)",
                "Veiksmīgi (Success)",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Kļūda saglabājot failu: " + e.getMessage(),
                "Kļūda (Error)",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoListGUI();
            }
        });
    }
}
