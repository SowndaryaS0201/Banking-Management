package model;

public class GatewayTransaction {

    private int accountId;
    private String gatewayName;
    private double amount;
    private String status;
    private String reference;

    public GatewayTransaction(int accountId, String gatewayName,
                              double amount, String status, String reference) {
        this.accountId = accountId;
        this.gatewayName = gatewayName;
        this.amount = amount;
        this.status = status;
        this.reference = reference;
    }

    public int getAccountId() { return accountId; }
    public String getGatewayName() { return gatewayName; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getReference() { return reference; }
}
