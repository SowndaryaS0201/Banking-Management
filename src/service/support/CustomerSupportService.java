package service.support;

import dao.SupportTicketDAO;
import model.SupportTicket;

public class CustomerSupportService {

    public void raiseTicket(int userId,
                            String issueType,
                            String description,
                            String channel) throws Exception {

        if (!(channel.equalsIgnoreCase("PHONE") ||
              channel.equalsIgnoreCase("EMAIL") ||
              channel.equalsIgnoreCase("CHAT"))) {
            throw new Exception("Invalid support channel");
        }

        SupportTicket ticket = new SupportTicket(
                userId,
                issueType,
                description,
                channel,
                "OPEN"
        );

        SupportTicketDAO dao = new SupportTicketDAO();
        dao.createTicket(ticket);

        System.out.println("Support ticket raised successfully!");
    }
}
