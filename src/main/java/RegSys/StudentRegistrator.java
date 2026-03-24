package RegSys;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import rvt.utils.ConsoleColor;

public class StudentRegistrator {
    ArrayList<Student> studentList = new ArrayList<Student>();
    private final String filePath = "data\\students.csv";
    private final String ansiRegex = "\\u001B\\[[;\\d]*m";

    public void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists())
            return;

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine())
                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] data = row.split(",");

                if (data.length == 6) {
                    String firstName = data[0];
                    String lastName = data[1];
                    String email = data[2];
                    String code = data[3];
                    LocalDate date = LocalDate.parse(data[4]);
                    LocalTime time = LocalTime.parse(data[5]);

                    registerStudent(firstName, lastName, email, code, date, time);
                }
            }
        } catch (Exception e) {
            System.out.println("Loading error: " + e.getMessage());
        }
    }

    public void writeToFile() {
        try (PrintWriter pWriter = new PrintWriter(filePath)) {
            pWriter.println("firstName,lastName,email,personalCode,regDate,regTime");

            for (Student student : studentList) {
                pWriter.print(student.getFirstName());
                pWriter.print(",");
                pWriter.print(student.getLastName());
                pWriter.print(",");
                pWriter.print(student.getEmail());
                pWriter.print(",");
                pWriter.print(student.getPersonalCode());
                pWriter.print(",");
                pWriter.print(student.getRegistrationDate());
                pWriter.print(",");
                pWriter.print(student.getRegistrationTime());
                pWriter.print("\n");

            }
            System.out.println("Data saved successfully");
        } catch (Exception e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public void registerStudent(String firstName, String lastName, String email, String personalCode,
            LocalDate registrationDate, LocalTime registrationTime) {
        Student registeredStudent = new Student();
        registeredStudent.registerStudent(firstName, lastName, email, personalCode, registrationDate, registrationTime);
        studentList.add(registeredStudent);
    }

    public void printStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students registered");
            return;
        }

        // column width
        int w1 = 15; // firstName
        int w2 = 15; // lastName
        int w3 = 25; // email
        int w4 = 14; // personalCode
        int w5 = 12; // date
        int w6 = 10; // time

        // horizontal separator line
        String separator = String.format("+-%s-+-%s-+-%s-+-%s-+-%s-+-%s-+",
                "-".repeat(w1), "-".repeat(w2), "-".repeat(w3), "-".repeat(w4), "-".repeat(w5), "-".repeat(w6));
        
        String coloredSeparator = color(separator, ConsoleColor.WHITE);

        // header
        System.out.println(coloredSeparator);
        String h1 = colorAndPad("First Name", w1, ConsoleColor.CYAN);
        String h2 = colorAndPad("Last Name", w2, ConsoleColor.CYAN);
        String h3 = colorAndPad("Email", w3, ConsoleColor.GREEN);
        String h4 = colorAndPad("ID Code", w4, ConsoleColor.RED);
        String h5 = colorAndPad("Reg. Date", w5, ConsoleColor.YELLOW);
        String h6 = colorAndPad("Reg. Time", w6, ConsoleColor.YELLOW);
        System.out.printf("| %s | %s | %s | %s | %s | %s |%n", h1, h2, h3, h4, h5, h6);
        System.out.println(coloredSeparator);

        // Student data rows
        for (Student s : studentList) {
            String c1 = padVisible(s.getFirstName(), w1);
            String c2 = padVisible(s.getLastName(), w2);
            String c3 = padVisible(s.getEmail(), w3);
            String c4 = padVisible(s.getPersonalCode(), w4);
            String c5 = padVisible(s.getRegistrationDate().toString(), w5);
            String c6 = padVisible(s.getRegistrationTime().toString(), w6);
            System.out.printf(" %s | %s | %s | %s | %s | %s |%n", c1, c2, c3, c4, c5, c6);
        }

        // bottom border
        System.out.println(coloredSeparator);
    }

    public Student findStudent(String personalCode) {
        for (Student student : studentList) {
            if (student.getPersonalCode().equals(personalCode)) {
                return student;
            }
        }
        return null;
    }

    public void removeStudent(String personalCode) {
        studentList.removeIf(s -> s.getPersonalCode().equals(personalCode));

        writeToFile();
    }

    private int visibleLength(String s) {
        return s.replaceAll(ansiRegex, "").length();
    }

    private String padVisible(String s, int width) {
        int visible = visibleLength(s);
        int padding = Math.max(0, width - visible);
        return s + " ".repeat(padding);
    }

    private String colorAndPad(String text, int width, ConsoleColor color) {
        String colored = color.getCode() + text + ConsoleColor.RESET.getCode();
        return padVisible(colored, width);
    }

    private String color(String text, ConsoleColor color)
    {
        String colored = color.getCode() + text + ConsoleColor.RESET.getCode();
        return colored;
    }
}
