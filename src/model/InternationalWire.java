package model;

public class InternationalWire {

    private int senderAccountId;
    private String receiverBank;
    private String receiverAccount;
    private String swiftCode;
    private String country;
    private double amount;
    private String status;

    public InternationalWire(int senderAccountId, String receiverBank,
                             String receiverAccount, String swiftCode,
                             String country, double amount, String status) {
        this.senderAccountId = senderAccountId;
        this.receiverBank = receiverBank;
        this.receiverAccount = receiverAccount;
        this.swiftCode = swiftCode;
        this.country = country;
        this.amount = amount;
        this.status = status;
    }

    public int getSenderAccountId() { return senderAccountId; }
    public String getReceiverBank() { return receiverBank; }
    public String getReceiverAccount() { return receiverAccount; }
    public String getSwiftCode() { return swiftCode; }
    public String getCountry() { return country; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}
