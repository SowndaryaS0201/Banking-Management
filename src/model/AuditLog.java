package model;

public class AuditLog {

    private int userId;
    private String action;
    private String description;

    public AuditLog(int userId, String action, String description) {
        this.userId = userId;
        this.action = action;
        this.description = description;
    }

    public int getUserId() { return userId; }
    public String getAction() { return action; }
    public String getDescription() { return description; }
}

