package service.payment;

import dao.AccountDAO;
import dao.TransactionDAO;
import dao.WireTransferDAO;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.InternationalWire;
import model.Transaction;

public class InternationalWireService {

    public void sendWire(int senderAccountId,
                         String receiverBank,
                         String receiverAccount,
                         String swiftCode,
                         String country,
                         double amount) throws Exception {

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        AccountDAO accDAO = AccountDAO.getInstance();
        double balance = accDAO.getBalance(senderAccountId);

        if (amount > balance) {
            throw new InsufficientBalanceException("Not enough balance for international transfer");
        }

        // Simulated verification step
        String verificationStatus = "VERIFIED";

        // Deduct money
        accDAO.withdrawAmount(senderAccountId, amount);

        // Save wire transfer record
        InternationalWire wire = new InternationalWire(
                senderAccountId,
                receiverBank,
                receiverAccount,
                swiftCode,
                country,
                amount,
                verificationStatus
        );

        WireTransferDAO wireDAO = new WireTransferDAO();
        wireDAO.saveWireTransfer(wire);

        // Record transaction
        Transaction txn = new Transaction(senderAccountId, "INT_WIRE", amount, "ONLINE");
        TransactionDAO txnDAO = TransactionDAO.getInstance();
        txnDAO.recordTransaction(txn);

        System.out.println("International Wire Transfer Successful!");
    }
}
