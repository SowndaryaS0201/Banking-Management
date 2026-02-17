package service.planning;

public class RetirementPlannerService {

    public void calculateRetirement(double monthlySaving, int years) {

        int months = years * 12;
        double totalSavings = monthlySaving * months;

        System.out.println("Estimated Retirement Savings after "
                + years + " years: Rs." + totalSavings);
    }
}
