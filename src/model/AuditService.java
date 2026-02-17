package model;

import dao.AuditLogDAO;
import model.AuditLog;

public class AuditService {

    public void logAction(int userId, String action, String description) throws Exception {

        AuditLog log = new AuditLog(userId, action, description);
        AuditLogDAO dao = new AuditLogDAO();
        dao.saveAudit(log);
    }
}
