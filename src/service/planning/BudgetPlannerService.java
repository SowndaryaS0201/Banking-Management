package service.planning;

import dao.FinancialPlanDAO;
import model.FinancialPlan;

public class BudgetPlannerService {

    public void calculateBudget(int userId, double income, double expense) throws Exception {

        double savings = income - expense;

        System.out.println("Monthly Savings: Rs." + savings);

        FinancialPlan plan = new FinancialPlan(userId, income, expense, savings);
        FinancialPlanDAO dao = new FinancialPlanDAO();
        dao.savePlan(plan);
    }
}
