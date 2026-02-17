package service.loan;

import dao.LoanDAO;
import dao.UserDAO;
import exception.AccountBlockedException;
import exception.InvalidAmountException;
import model.LoanApplication;

public class LoanService {

    public void applyForLoan(int userId, String loanType, double amount) throws Exception {

        // 1️⃣ Check if user is BLOCKED
        UserDAO userDAO = UserDAO.getInstance();
        String status = userDAO.getUserStatus(userId);

        if (status != null && status.equalsIgnoreCase("BLOCKED")) {
            throw new AccountBlockedException("Blocked users cannot apply for loans");
        }

        // 2️⃣ Validate loan amount
        if (amount <= 0) {
            throw new InvalidAmountException("Loan amount must be greater than zero");
        }

        // 3️⃣ Create loan object
        LoanApplication loan = new LoanApplication(
                userId,
                loanType,
                amount,
                "PENDING"
        );

        // 4️⃣ Save to database
        LoanDAO dao = new LoanDAO();
        dao.applyLoan(loan);

        System.out.println("Loan application submitted successfully!");
    }
}

