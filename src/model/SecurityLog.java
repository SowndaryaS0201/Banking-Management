package model;

public class SecurityLog {

    private int userId;
    private String activity;
    private String status;

    public SecurityLog(int userId, String activity, String status) {
        this.userId = userId;
        this.activity = activity;
        this.status = status;
    }

    public int getUserId() { return userId; }
    public String getActivity() { return activity; }
    public String getStatus() { return status; }
}
