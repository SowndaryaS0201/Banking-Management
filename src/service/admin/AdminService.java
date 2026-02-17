package service.admin;

import dao.UserDAO;

public class AdminService {

    public void blockUserAccount(int userId) throws Exception {
        UserDAO dao = UserDAO.getInstance();
        dao.blockUser(userId);
        System.out.println("User account BLOCKED.");
    }

    public void unblockUserAccount(int userId) throws Exception {
        UserDAO dao = UserDAO.getInstance();
        dao.unblockUser(userId);
        System.out.println("User account UNBLOCKED.");
    }
}
