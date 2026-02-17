package service.user;

import util.OTPUtil;
import dao.SecurityLogDAO;

public class MFAService {

    public int sendOTP(int userId) throws Exception {

        int otp = OTPUtil.generateOTP();

        // Simulate sending OTP
        System.out.println("OTP sent to user: " + otp);

        SecurityLogDAO dao = new SecurityLogDAO();
        dao.logActivity(userId, "OTP Generated", "SUCCESS");

        return otp;
    }

    public void verifyOTP(int userId, int enteredOtp, int realOtp) throws Exception {

        SecurityLogDAO dao = new SecurityLogDAO();

        if (enteredOtp == realOtp) {
            dao.logActivity(userId, "OTP Verified", "SUCCESS");
            System.out.println("MFA Verification Successful");
        } else {
            dao.logActivity(userId, "OTP Failed", "FAILED");
            throw new Exception("Invalid OTP");
        }
    }
}
