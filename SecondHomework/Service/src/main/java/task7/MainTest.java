package task7;

import task7.DAO.Dao;
import task7.DAO.DaoImpl;
import task7.DTO.Expense;
import task7.DTO.Receiver;

import java.util.List;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Укажите номер платежа: ");
        int n = sc.nextInt();
        Dao myDao = DaoImpl.getDao();
        Expense expense = myDao.getExpense(n);
        Receiver receiver = myDao.getReceiver(n);
        System.out.println("Платеж " + n + ": " + expense.getPaydate() +
                " " + receiver.getName() + " " + expense.getPrice() + "\n");
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Укажите имя добавляемого получателя платежа: ");
        Receiver receiver2 = myDao.getReceiver(n);
        receiver2.setName(sc2.nextLine());
        myDao.addReceiver(receiver2);
        System.out.println("Добавлен получатель: " + receiver2.getName() + "\n");
        List<Expense> expenses = myDao.getExpenses();
        System.out.println("Таблица платежей:");
        for (Expense expense2 : expenses) {
            System.out.println(expense2.getNum() + " " + expense2.getPaydate() +
                    " " + expense2.getPrice() + "\n");
        }
        List<Receiver> receivers = myDao.getReceivers();
        System.out.println("Таблица получателей:");
        for (Receiver receiver3 : receivers) {
            System.out.println(receiver3.getNum() + " " + receiver3.getName());
        }
    }
}
