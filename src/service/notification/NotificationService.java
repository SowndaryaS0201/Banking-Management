package service.notification;

import dao.NotificationDAO;
import model.Notification;

public class NotificationService {

    private static final double LARGE_TXN_LIMIT = 100000;

    public void checkAndSendAlert(int accountId, double amount, String txnType) throws Exception {

        if (amount >= LARGE_TXN_LIMIT) {

            String msg = "ALERT: Large " + txnType + " of Rs." + amount + " detected.";

            // Simulate SMS
            System.out.println("SMS SENT: " + msg);

            Notification notification = new Notification(accountId, msg, "SMS");
            NotificationDAO dao = new NotificationDAO();
            dao.saveNotification(notification);
        }
    }
}
