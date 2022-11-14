package my.loader;

import my.dao.PersonDaoImpl;
import my.model.Person;

import java.util.Scanner;

public class PersonScanner {
    private final PersonDaoImpl personDao = new PersonDaoImpl();

    String scanFirstName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert Person Firstname: ");
        return scan.nextLine();
    }

    String scanSurname() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert Person Surname: ");
        return scan.nextLine();
    }

    int scanAge() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert Person age: ");
        return scan.nextInt();
    }

    long scanId() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert Person ID: ");
        return scan.nextLong();
    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please, insert command: " + "\n" +
                "1 - Save Person" + "\n" +
                "2 - Find Person by ID" + "\n" +
                "3 - Delete Person by ID" + "\n" +
                "4 - Find Person by ID (load method //practical)" + "\n" +
                "5 - Flush Person (task3)" + "\n" +
                "6 - Exit");
        int choice = scan.nextInt();
        if (choice == 1) {
            personDao.create(Person.builder().name(scanFirstName())
                    .surname(scanSurname()).age(scanAge()).build());
        } else if (choice == 2) {
            personDao.get(scanId());
        } else if (choice == 3) {
            personDao.delete(personDao.get(scanId()));
        } else if (choice == 4) {
            personDao.delete(personDao.load(scanId()));
        } else if (choice == 5) {
            personDao.flush(scanId(), scanAge(), scanFirstName(), scanSurname());
        } else if (choice == 6) {
            System.out.println("Exiting from the application...");
        } else {
            System.out.println("Invalid Number. Please, try again.");
        }
    }
}
