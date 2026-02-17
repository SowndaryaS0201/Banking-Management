package main;

import dao.AccountDAO;
import dao.UserDAO;
import java.util.Scanner;
import model.Account;
import model.User;
import service.account.DepositService;
import service.account.TransferService;
import service.account.WithdrawService;
import service.admin.AdminService;
import service.loan.CreditCardService;
import service.loan.LoanService;
import service.payment.InternationalWireService;
import service.planning.BudgetPlannerService;
import service.recurring.RecurringPaymentService;
import service.support.CustomerSupportService;
import service.transaction.StatementService;

public class BankingApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepositService depositService = new DepositService();
        WithdrawService withdrawService = new WithdrawService();
        TransferService transferService = new TransferService();
        InternationalWireService wireService = new InternationalWireService();
        RecurringPaymentService recurringService = new RecurringPaymentService();
        LoanService loanService = new LoanService();
        CreditCardService cardService = new CreditCardService();
        StatementService statementService = new StatementService();
        CustomerSupportService supportService = new CustomerSupportService();
        BudgetPlannerService plannerService = new BudgetPlannerService();
        AdminService adminService = new AdminService();

        String role = "";
        int loggedUserId = 0;

        System.out.println("===== BANK SYSTEM =====");
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.print("Choose: ");
        int startChoice = sc.nextInt();

        // ================= SIGNUP =================
        if (startChoice == 1) {

            System.out.println("Select Role:");
            System.out.println("1. USER");
            System.out.println("2. ADMIN");
            int roleChoice = sc.nextInt();

            role = (roleChoice == 1) ? "USER" : "ADMIN";

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter PIN: ");
            String pin = sc.next();

            System.out.print("Confirm PIN: ");
            String confirmPin = sc.next();

            if (!pin.equals(confirmPin)) {
                System.out.println("PIN mismatch! Signup failed.");
                return;
            }

            System.out.print("Enter Email: ");
            String email = sc.next();

            try {
                UserDAO userDAO = UserDAO.getInstance();

                int newId;
                if (role.equals("ADMIN"))
                    newId = 900000 + (int)(Math.random() * 10000);
                else
                    newId = 100000 + (int)(Math.random() * 10000);

                User user = new User(newId, name, email, pin, role, "ACTIVE");
                userDAO.createUser(user);

                System.out.println("Signup successful!");
                System.out.println("Your ID: " + newId);

                loggedUserId = newId;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        // ================= LOGIN =================
        else if (startChoice == 2) {

            System.out.print("Enter User ID: ");
            loggedUserId = sc.nextInt();

            System.out.print("Enter PIN: ");
            String pin = sc.next();

            try {
                UserDAO userDAO = UserDAO.getInstance();
                role = userDAO.validateLogin(loggedUserId, pin);

                if (role == null) {
                    System.out.println("Invalid login!");
                    return;
                }

                System.out.println("Logged in as: " + role);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        // ================= USER MENU =================
        if (role.equalsIgnoreCase("USER")) {

            while (true) {

                System.out.println("\n------ USER MENU ------");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. International Transfer");
                System.out.println("6. Recurring Payment");
                System.out.println("7. Apply Loan");
                System.out.println("8. Apply Credit Card");
                System.out.println("9. Budget Planner");
                System.out.println("10. Raise Support Ticket");
                System.out.println("11. View Statement");
                System.out.println("12. Exit");

                int choice = sc.nextInt();
                if (choice == 12) break;

                System.out.print("Enter Account ID: ");
                int accId = sc.nextInt();

                // PIN verification
                if (choice >= 2 && choice <= 5) {
                    System.out.print("Enter PIN: ");
                    String enteredPin = sc.next();

                    try {
                        String realPin = UserDAO.getInstance().getUserPin(loggedUserId);

                        if (realPin == null || !enteredPin.equals(realPin)) {
                            System.out.println("Invalid PIN!");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }

                try {
                    switch (choice) {

                        case 1:
                            System.out.println("Select Account Type:");
                            System.out.println("1. SAVINGS");
                            System.out.println("2. CURRENT");
                            int typeChoice = sc.nextInt();
                            String type = (typeChoice == 1) ? "SAVINGS" : "CURRENT";

                            System.out.print("Enter Initial Balance: ");
                            double bal = sc.nextDouble();

                            Account acc = new Account(loggedUserId, type, bal);
                            AccountDAO.getInstance().createAccount(acc);
                            System.out.println("Account Created!");
                            break;

                        case 2:
                            System.out.print("Amount: ");
                            double dep = sc.nextDouble();
                            System.out.println("Channel: 1. ATM  2. ONLINE  3. MOBILE");
                            int ch = sc.nextInt();
                            String chType = (ch==1)?"ATM":(ch==2)?"ONLINE":"MOBILE";
                            depositService.deposit(accId, dep, chType);
                            break;

                        case 3:
                            System.out.print("Amount: ");
                            double wd = sc.nextDouble();
                            System.out.println("Channel: 1. ATM  2. BRANCH");
                            int wc = sc.nextInt();
                            String wcType = (wc==1)?"ATM":"BRANCH";
                            withdrawService.withdraw(accId, wd, wcType);
                            break;

                        case 4:
                            System.out.print("To Account ID: ");
                            int toAcc = sc.nextInt();
                            System.out.print("Amount: ");
                            double amt = sc.nextDouble();
                            transferService.transfer(accId, toAcc, amt);
                            break;

                        case 5:
                            System.out.print("Receiver Bank: ");
                            String bank = sc.next();
                            System.out.print("Receiver Account: ");
                            String rAcc = sc.next();
                            System.out.print("SWIFT Code: ");
                            String swift = sc.next();
                            System.out.print("Country: ");
                            String country = sc.next();
                            System.out.print("Amount: ");
                            double wireAmt = sc.nextDouble();
                            wireService.sendWire(accId, bank, rAcc, swift, country, wireAmt);
                            break;

                        case 6:
                            System.out.print("Amount: ");
                            double recAmt = sc.nextDouble();
                            System.out.println("Frequency: 1.DAILY 2.WEEKLY 3.MONTHLY");
                            int freq = sc.nextInt();
                            String freqType = (freq==1)?"DAILY":(freq==2)?"WEEKLY":"MONTHLY";
                            recurringService.schedulePayment(accId, recAmt, freqType, null);
                            break;

                        case 7:
                            System.out.println("Loan Type: 1.HOME 2.PERSONAL 3.EDUCATION 4.CAR");
                            int lt = sc.nextInt();
                            String loanType = (lt==1)?"HOME":(lt==2)?"PERSONAL":(lt==3)?"EDUCATION":"CAR";
                            System.out.print("Amount: ");
                            double lam = sc.nextDouble();
                            loanService.applyForLoan(loggedUserId, loanType, lam);
                            break;

                        case 8:
                            System.out.println("Card Type: 1.GOLD 2.SILVER 3.PLATINUM");
                            int ct = sc.nextInt();
                            String ctype = (ct==1)?"GOLD":(ct==2)?"SILVER":"PLATINUM";
                            System.out.print("Limit: ");
                            double limit = sc.nextDouble();
                            cardService.applyForCard(loggedUserId, ctype, limit);
                            break;

                        case 9:
                            System.out.print("Monthly Income: ");
                            double income = sc.nextDouble();
                            System.out.print("Monthly Expense: ");
                            double expense = sc.nextDouble();
                            plannerService.calculateBudget(loggedUserId, income, expense);
                            break;

                        case 10:
                            System.out.println("Issue: 1.ATM 2.LOGIN 3.TRANSFER 4.CARD");
                            int issue = sc.nextInt();
                            String issueType = (issue==1)?"ATM":(issue==2)?"LOGIN":(issue==3)?"TRANSFER":"CARD";

                            System.out.println("Channel: 1.PHONE 2.EMAIL 3.CHAT");
                            int sch = sc.nextInt();
                            String schType = (sch==1)?"PHONE":(sch==2)?"EMAIL":"CHAT";

                            supportService.raiseTicket(loggedUserId, issueType, "Problem reported", schType);
                            break;

                        case 11:
                            statementService.viewStatement(accId);
                            break;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // ================= ADMIN MENU =================
        else if (role.equalsIgnoreCase("ADMIN")) {

            while (true) {
                System.out.println("\n------ ADMIN MENU ------");
                System.out.println("1. Block User");
                System.out.println("2. Unblock User");
                System.out.println("3. Exit");

                int choice = sc.nextInt();
                if (choice == 3) break;

                System.out.print("Enter User ID: ");
                int uid = sc.nextInt();

                try {
                    if (choice == 1)
                        adminService.blockUserAccount(uid);
                    else
                        adminService.unblockUserAccount(uid);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }
}
