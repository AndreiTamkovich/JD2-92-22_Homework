package task6.runner;

import task6.dao.ProductIncrementDaoImpl;
import task6.dao.ProductSequenceDaoImpl;
import task6.model.ProductIncrement;
import task6.model.ProductSequence;

import java.util.Scanner;

public class PersistentIdPrinter {
    public void print() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Hello! Choose command" + "\n" +
                    "1 - Increment strategy" + "\n" +
                    "2 - Sequence strategy" + "\n" +
                    "3 - Exit");
            int n = scan.nextInt();
            if (n == 1) {
                System.out.println("Your choose Increment strategy");
                new ProductIncrementDaoImpl().create(ProductIncrement.builder()
                        .name("test")
                        .price(1.0)
                        .build());
            } else if (n == 2) {
                System.out.println("Your choose Sequence strategy");
                new ProductSequenceDaoImpl().create(ProductSequence.builder()
                        .name("test")
                        .price(1.0)
                        .build());
            } else if (n == 3) {
                return;
            } else {
                System.out.println("Unknown choose");
            }
        }
    }
}
