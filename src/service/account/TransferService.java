package service.account;

import dao.AccountDAO;
import dao.TransactionDAO;
import java.sql.Connection;
import model.Transaction;
import util.DBConnection;

public class TransferService {

    public void transfer(int fromAcc, int toAcc, double amount) throws Exception {

    Connection con = DBConnection.getConnection();
    con.setAutoCommit(false);

    try {
        AccountDAO accDAO = AccountDAO.getInstance();
        TransactionDAO txnDAO = TransactionDAO.getInstance();

        double balance = accDAO.getBalance(fromAcc);

        if (amount > balance)
            throw new Exception("Insufficient Balance");

        // Step 1: Withdraw
        accDAO.withdrawAmount(fromAcc, amount);

        // Step 2: Deposit
        accDAO.depositAmount(toAcc, amount);

        // Step 3: Record transaction
        Transaction t1 = new Transaction(fromAcc, "TRANSFER_OUT", amount, "ONLINE");
        Transaction t2 = new Transaction(toAcc, "TRANSFER_IN", amount, "ONLINE");

        txnDAO.recordTransaction(t1);
        txnDAO.recordTransaction(t2);

        // COMMIT
        con.commit();

        System.out.println("Transfer Successful!");

    } catch (Exception e) {

        // ROLLBACK if ANY step fails
        con.rollback();
        System.out.println("Transfer Failed: " + e.getMessage());
    }
}

}
