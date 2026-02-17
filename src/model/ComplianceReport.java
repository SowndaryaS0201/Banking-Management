package model;

public class ComplianceReport {

    private int userId;
    private String reportType;

    public ComplianceReport(int userId, String reportType) {
        this.userId = userId;
        this.reportType = reportType;
    }

    public int getUserId() { return userId; }
    public String getReportType() { return reportType; }
}
