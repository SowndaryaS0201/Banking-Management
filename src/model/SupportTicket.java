package model;

public class SupportTicket {

    private int userId;
    private String issueType;
    private String description;
    private String channel;
    private String status;

    public SupportTicket(int userId, String issueType,
                         String description, String channel, String status) {
        this.userId = userId;
        this.issueType = issueType;
        this.description = description;
        this.channel = channel;
        this.status = status;
    }

    public int getUserId() { return userId; }
    public String getIssueType() { return issueType; }
    public String getDescription() { return description; }
    public String getChannel() { return channel; }
    public String getStatus() { return status; }
}
