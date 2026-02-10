package RegSys;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentRegistrator {
    ArrayList<Student> studentList = new ArrayList<Student>();
    private final String filePath = "data\\students.csv";

    // TODO: implement file loading
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

        // horizontal separator line
        String separator = String.format("+-%s-+-%s-+-%s-+-%s-+-%s-+",
                "-".repeat(w1), "-".repeat(w2), "-".repeat(w3), "-".repeat(w4), "-".repeat(w5));

        // Header
        System.out.println(separator);
        System.out.printf("| %-" + w1 + "s | %-" + w2 + "s | %-" + w3 + "s | %-" + w4 + "s | %-" + w5 + "s |\n",
                "First Name", "Last Name", "Email", "ID Code", "Reg. Date");
        System.out.println(separator);

        // Student data rows
        for (Student s : studentList) {
            System.out.printf("| %-" + w1 + "s | %-" + w2 + "s | %-" + w3 + "s | %-" + w4 + "s | %-" + w5 + "s |\n",
                    s.getFirstName(),
                    s.getLastName(),
                    s.getEmail(),
                    s.getPersonalCode(),
                    s.getRegistrationDate());
        }

        // bottom border
        System.out.println(separator);
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
    }
}
