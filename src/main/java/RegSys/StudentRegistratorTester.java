package RegSys;

import java.util.Scanner;
public class StudentRegistratorTester
{
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRegistrator registrator = new StudentRegistrator();
        StudentRegistrationCLI cli = new StudentRegistrationCLI(registrator, scanner);

        cli.start();
    }
}