package model;

import java.sql.Date;

public class RecurringTransaction {

    private int accountId;
    private double amount;
    private String frequency;
    private Date nextRunDate;
    private String status;

    public RecurringTransaction(int accountId, double amount,
                                String frequency, Date nextRunDate, String status) {
        this.accountId = accountId;
        this.amount = amount;
        this.frequency = frequency;
        this.nextRunDate = nextRunDate;
        this.status = status;
    }

    public int getAccountId() { return accountId; }
    public double getAmount() { return amount; }
    public String getFrequency() { return frequency; }
    public Date getNextRunDate() { return nextRunDate; }
    public String getStatus() { return status; }
}
