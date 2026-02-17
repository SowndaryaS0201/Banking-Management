package model;

public class User {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String status;

    // Constructor used during signup
    public User(int userId, String name, String email, String password, String role, String status) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}
