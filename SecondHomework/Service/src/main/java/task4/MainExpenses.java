package task4;

public class MainExpenses {
    public static void main(String[] args) {
        Expenses expenses = new Expenses();
        expenses.create(args);
        expenses.readAll();
    }
}
