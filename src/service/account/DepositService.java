package service.account;

import dao.AccountDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import exception.InvalidAmountException;
import model.Transaction;
import service.notification.NotificationService;

public class DepositService {

    public void deposit(int accountId, double amount, String channel) {

        try {

            // 🔥 Singleton DAO usage
            AccountDAO accDAO = AccountDAO.getInstance();
            int userId = accDAO.getUserIdByAccountId(accountId);

            UserDAO userDAO = UserDAO.getInstance();
            String status = userDAO.getUserStatus(userId);

            // Block check
            if (status != null && status.equalsIgnoreCase("BLOCKED")) {
                System.out.println("Account is blocked by admin");
                return;
            }

            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be greater than zero");
            }

            if (!(channel.equalsIgnoreCase("ATM") ||
                  channel.equalsIgnoreCase("ONLINE") ||
                  channel.equalsIgnoreCase("MOBILE"))) {
                System.out.println("Invalid deposit channel");
                return;
            }

            // Update balance
            accDAO.depositAmount(accountId, amount);

            // Record transaction
            Transaction txn = new Transaction(accountId, "DEPOSIT", amount, channel);
            TransactionDAO txnDAO = TransactionDAO.getInstance();
            txnDAO.recordTransaction(txn);

            NotificationService notify = new NotificationService();
            notify.checkAndSendAlert(accountId, amount, "DEPOSIT");

            System.out.println("Deposit Successful!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
