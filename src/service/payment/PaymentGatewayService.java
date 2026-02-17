package service.payment;

import dao.AccountDAO;
import dao.GatewayTransactionDAO;
import dao.TransactionDAO;
import java.util.UUID;
import model.GatewayTransaction;
import model.Transaction;

public class PaymentGatewayService {

    public void makePayment(int accountId, double amount, String gateway) throws Exception {

        AccountDAO accDAO = AccountDAO.getInstance();
        double balance = accDAO.getBalance(accountId);

        if (amount > balance) {
            throw new Exception("Insufficient balance for gateway payment");
        }

        // Deduct from account
        accDAO.withdrawAmount(accountId, amount);

        // Generate fake reference ID (like UPI ref)
        String reference = UUID.randomUUID().toString();

        // Save gateway transaction
        GatewayTransaction txn = new GatewayTransaction(
                accountId,
                gateway,
                amount,
                "SUCCESS",
                reference
        );

        GatewayTransactionDAO dao = new GatewayTransactionDAO();
        dao.saveGatewayTxn(txn);

        // Record normal transaction also
        Transaction normalTxn = new Transaction(accountId, "GATEWAY_PAY", amount, gateway);
        TransactionDAO txnDAO = TransactionDAO.getInstance();
        txnDAO.recordTransaction(normalTxn);

        System.out.println("Payment via " + gateway + " successful!");
        System.out.println("Reference ID: " + reference);
    }
}
