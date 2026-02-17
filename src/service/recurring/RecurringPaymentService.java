package service.recurring;

import dao.RecurringTransactionDAO;
import model.RecurringTransaction;
import java.sql.Date;

public class RecurringPaymentService {

    public void schedulePayment(int accountId, double amount,
                                String frequency, Date nextDate) throws Exception {

        RecurringTransaction rt = new RecurringTransaction(
                accountId,
                amount,
                frequency,
                nextDate,
                "ACTIVE"
        );

        RecurringTransactionDAO dao = new RecurringTransactionDAO();
        dao.createRecurringPayment(rt);

        System.out.println("Recurring payment scheduled successfully!");
    }
}
