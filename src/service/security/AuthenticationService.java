package service.security;

public class AuthenticationService {

    public boolean login(String email, String password) {
        return email.equals("admin@gmail.com") && password.equals("admin");
    }
}

