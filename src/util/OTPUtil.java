package util;

import java.util.Random;

public class OTPUtil {

    public static int generateOTP() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000);
    }
}
