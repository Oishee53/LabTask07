import java.util.UUID;

public class User {
    private String userID;
    private String username;
    private String email;
    private String password;
    private String userType;

    // Constructor for creating new users
    public User(String username, String email, String password, String userType) {
        this.userID = UUID.randomUUID().toString(); // Generate unique user ID
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Constructor for loading existing users
    public User(String userID, String username, String email, String password, String userType) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters
    public String getUserID() { return userID; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getUserType() { return userType; }

    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUserType(String userType) { this.userType = userType; }

    // String representation for file operations
    @Override
    public String toString() {
        return userID + "," + username + "," + email + "," + password + "," + userType;
    }
}
