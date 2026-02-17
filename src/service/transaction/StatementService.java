package service.transaction;

import dao.TransactionDAO;

public class StatementService {

    public void viewStatement(int accountId) throws Exception {

        TransactionDAO dao = TransactionDAO.getInstance();
        dao.getTransactionsByAccount(accountId);
    }
}
