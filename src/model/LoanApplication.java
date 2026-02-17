package model;

public class LoanApplication {

    private int userId;
    private String loanType;
    private double amount;
    private String status;

    public LoanApplication(int userId, String loanType, double amount, String status) {
        this.userId = userId;
        this.loanType = loanType;
        this.amount = amount;
        this.status = status;
    }

    public int getUserId() { return userId; }
    public String getLoanType() { return loanType; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}
