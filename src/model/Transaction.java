package model;

public class Transaction {

    private int accountId;
    private String type;
    private double amount;
    private String channel;

    public Transaction(int accountId, String type, double amount, String channel) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.channel = channel;
    }

    public int getAccountId() { return accountId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getChannel() { return channel; }
}

