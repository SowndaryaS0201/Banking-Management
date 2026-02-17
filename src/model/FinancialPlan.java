package model;

public class FinancialPlan {

    private int userId;
    private double income;
    private double expense;
    private double savings;

    public FinancialPlan(int userId, double income, double expense, double savings) {
        this.userId = userId;
        this.income = income;
        this.expense = expense;
        this.savings = savings;
    }

    public int getUserId() { return userId; }
    public double getIncome() { return income; }
    public double getExpense() { return expense; }
    public double getSavings() { return savings; }
}
