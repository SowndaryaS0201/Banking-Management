package model;

public class CreditCardApplication {

    private int userId;
    private String cardType;
    private double limit;
    private String status;

    public CreditCardApplication(int userId, String cardType, double limit, String status) {
        this.userId = userId;
        this.cardType = cardType;
        this.limit = limit;
        this.status = status;
    }

    public int getUserId() { return userId; }
    public String getCardType() { return cardType; }
    public double getLimit() { return limit; }
    public String getStatus() { return status; }
}
