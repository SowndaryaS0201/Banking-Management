package exception;

public class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}
