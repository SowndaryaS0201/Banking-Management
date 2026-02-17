package service.account;

import dao.AccountDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import exception.AccountBlockedException;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.Transaction;
import service.notification.NotificationService;

public class WithdrawService {

    public void withdraw(int accountId, double amount, String channel) throws Exception {

        // 🔥 Singleton DAO usage
        AccountDAO accDAO = AccountDAO.getInstance();
        int userId = accDAO.getUserIdByAccountId(accountId);

        UserDAO userDAO = UserDAO.getInstance();
        String status = userDAO.getUserStatus(userId);

        if (status.equals("BLOCKED")) {
            throw new AccountBlockedException("Account is blocked by admin");
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }

        if (!(channel.equalsIgnoreCase("ATM") || channel.equalsIgnoreCase("BRANCH"))) {
            throw new Exception("Invalid withdrawal channel");
        }

        double currentBalance = accDAO.getBalance(accountId);

        if (amount > currentBalance) {
            throw new InsufficientBalanceException("Not enough balance!");
        }

        accDAO.withdrawAmount(accountId, amount);

        Transaction txn = new Transaction(accountId, "WITHDRAW", amount, channel);
        TransactionDAO txnDAO = TransactionDAO.getInstance();
        txnDAO.recordTransaction(txn);

        NotificationService notify = new NotificationService();
        notify.checkAndSendAlert(accountId, amount, "WITHDRAW");

        System.out.println("Withdrawal Successful!");
    }
}
