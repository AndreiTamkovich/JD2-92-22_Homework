package task5;

import task5.PrecompileExpenses;

public class MainPrecompileExpenses {
    public static void main(String[] args) {
        PrecompileExpenses expenses = new PrecompileExpenses();
        expenses.create(args);
        expenses.readAll();
    }
}
