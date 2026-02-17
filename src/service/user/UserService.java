package service.user;

import dao.AccountDAO;
import dao.UserDAO;
import exception.InvalidAccountTypeException;
import model.Account;
import model.User;
public class UserService {

    public void registerUserWithAccount(User user, String accountType, double initialBalance) throws Exception {

        if (!(accountType.equalsIgnoreCase("CHECKING") ||
              accountType.equalsIgnoreCase("SAVINGS") ||
              accountType.equalsIgnoreCase("INVESTMENT"))) {

            throw new InvalidAccountTypeException("Invalid account type selected");
        }

        UserDAO userDAO = UserDAO.getInstance();
        int userId = userDAO.createUser(user);

        Account account = new Account(userId, accountType, initialBalance);
        AccountDAO accountDAO = AccountDAO.getInstance();
        accountDAO.createAccount(account);

        System.out.println("User and Account Created Successfully!");
    }
}
