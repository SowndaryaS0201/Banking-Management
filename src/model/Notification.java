package model;

public class Notification {

    private int accountId;
    private String message;
    private String type;

    public Notification(int accountId, String message, String type) {
        this.accountId = accountId;
        this.message = message;
        this.type = type;
    }

    public int getAccountId() { return accountId; }
    public String getMessage() { return message; }
    public String getType() { return type; }
}
