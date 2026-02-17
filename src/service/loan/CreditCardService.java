package service.loan;

import dao.CreditCardDAO;
import model.CreditCardApplication;

public class CreditCardService {

    public void applyForCard(int userId, String cardType, double limit) throws Exception {

        CreditCardApplication card = new CreditCardApplication(
                userId,
                cardType,
                limit,
                "PENDING"
        );

        CreditCardDAO dao = new CreditCardDAO();
        dao.applyCard(card);

        System.out.println("Credit card application submitted!");
    }
}
