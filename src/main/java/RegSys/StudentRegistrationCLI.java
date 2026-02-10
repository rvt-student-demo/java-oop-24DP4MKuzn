package RegSys;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class StudentRegistrationCLI {

    private StudentRegistrator registrator;
    private Scanner scanner;

    public void UserInterface(StudentRegistrator registrator, Scanner scanner) {
        this.registrator = registrator;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.print("Command (enter 'help' to list all commands): ");
            String command = scanner.nextLine();

            if (command.equals("help")) {
                System.out.println("help");
                System.out.println("register");
                System.out.println("show");
                System.out.println("remove");
                System.out.println("edit");
                System.out.println("exit");
            }

            if (command.equals("exit")) {
                break;
            }

            if (command.equals("register")) {
                try {
                    System.out.println("Enter student first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter student last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter student email: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter student personal code: ");
                    String personalCode = scanner.nextLine();
                    LocalDate registrationDate = LocalDate.now();
                    LocalTime registrLocalTime = LocalTime.now();
    
                    registrator.registerStudent(firstName, lastName, email, personalCode, registrationDate,
                            registrLocalTime);
                } catch (Exception e) {
                    System.out.println("Registration failed: " + e.getMessage());
                }
            }

            if (command.equals("show")) {
                registrator.printStudents();
            }

            if (command.equals("remove")) {
                System.out.println("Which student to remove? (enter personal code): ");
                String personalCode = scanner.nextLine();
                registrator.removeStudent(personalCode);
            }

            if (command.equals("edit")) {
                System.out.print("Enter personal code of student to edit: ");
                String code = scanner.nextLine();
                Student student = registrator.findStudent(code);

                if (student == null) {
                    System.out.println("Error: Student with that code not found.");
                } else {
                    System.out.println("Editing: " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("1. First Name | 2. Last Name | 3. Email | 4. Cancel");
                    System.out.print("Choose option: ");
                    String choice = scanner.nextLine();

                    try {
                        switch (choice) {
                            case "1":
                                System.out.print("Enter new First Name: ");
                                student.setFirstName(scanner.nextLine());
                                System.out.println("Updated successfully!");
                                break;
                            case "2":
                                System.out.print("Enter new Last Name: ");
                                student.setLastName(scanner.nextLine());
                                System.out.println("Updated successfully!");
                                break;
                            case "3":
                                System.out.print("Enter new Email: ");
                                student.setEmail(scanner.nextLine());
                                System.out.println("Updated successfully!");
                                break;
                            case "4":
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    } catch (Exception e) {
                        System.out.println("Update failed: " + e.getMessage());
                    }
                }
            }
        }
    }
}
