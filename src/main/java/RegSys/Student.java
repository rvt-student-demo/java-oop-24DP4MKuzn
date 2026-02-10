package RegSys;

import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;

public class Student {

    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String personalCode = "";
    private LocalDate registrationDate;
    private LocalTime registrationTime;

    public void registerStudent(String firstName, String lastName, String email, String personalCode,
            LocalDate registrationDate, LocalTime registrationTime) {
        setName(firstName, lastName);
        setEmail(email);
        setPersonalCode(personalCode);
        setRegistrationDateAndTime(registrationDate, registrationTime);
    }

    private void setName(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }

        if (firstName.length() < 3) {
            throw new IllegalArgumentException(
                    "First name '" + firstName + "' is too short! Names should have at least three characters");
        }

        if (Pattern.matches("[a-zA-Z]+", firstName) == false) {
            throw new IllegalArgumentException("First name cannot contain anything other than letters!");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name cannot be null");
        }

        if (lastName.length() < 3) {
            throw new IllegalArgumentException(
                    "Last name '" + lastName + "' is too short! Names should have at least three characters");
        }

        if (Pattern.matches("[a-zA-Z]+", lastName) == false) {
            throw new IllegalArgumentException("Last name cannot contain anything other than letters!");
        }

        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Email must contain a local part, '@', and a domain");
        }

        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1);

        if (!domainPart.contains(".")) {
            throw new IllegalArgumentException("Email domain must contain a provider (example, gmail.com)");
        }

        if (!localPart.matches("[A-Za-z0-9._%+-]+")) {
            throw new IllegalArgumentException("Local part contains invalid characters");
        }

        if (!domainPart.matches("[A-Za-z0-9.-]+")) {
            throw new IllegalArgumentException("Domain contains invalid characters");
        }

        this.email = email;
    }

    private void setPersonalCode(String personalCode) {
        if (personalCode == null) {
            throw new IllegalArgumentException("Personal code cannot be null");
        }

        if (personalCode.length() > 12) {
            throw new IllegalArgumentException("Personal code cannot contain more than 12 characters!");
        }

        if (personalCode.length() < 12) {
            throw new IllegalArgumentException("Personal code cannot contain less than 12 characters!");
        }

        int atIndex = personalCode.indexOf('-');

        if (atIndex <= 0 || atIndex == personalCode.length() - 1) {
            throw new IllegalArgumentException("Personal code must contain a dash separator!");
        }

        String firstPart = personalCode.substring(0, atIndex);
        String secondPart = personalCode.substring(atIndex + 1);

        if (firstPart.length() > 6) {
            throw new IllegalArgumentException("Personal code first part cannot contain more than 6 characters!");
        }

        if (firstPart.length() < 6) {
            throw new IllegalArgumentException("Personal code first part cannot contain less than 6 characters!");
        }

        if (secondPart.length() > 5) {
            throw new IllegalArgumentException("Personal code second part cannot contain more than 5 characters!");
        }

        if (secondPart.length() < 5) {
            throw new IllegalArgumentException("Personal code second part cannot contain less than 5 characters!");
        }

        if (Pattern.matches("[a-zA-Z]+", firstPart) == true) {
            throw new IllegalArgumentException("Personal code first part cannot contain anything other than numbers!");
        }

        if (Pattern.matches("[a-zA-Z]+", secondPart) == true) {
            throw new IllegalArgumentException("Personal code second part cannot contain anything other than numbers!");
        }

        this.personalCode = personalCode;
    }

    private void setRegistrationDateAndTime(LocalDate registrationDate, LocalTime registrationTime) {
        setRegistrationDate(registrationDate);
        setRegistrationTime(registrationTime);
    }

    private void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    private void setRegistrationTime(LocalTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalTime getRegistrationTime()
    {
        return registrationTime;
    }
}
