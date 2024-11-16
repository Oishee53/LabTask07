import java.io.IOException;

public class Authentication {
    private User currentUser;

    public boolean login(String username, String password) throws IOException {
        UserManager userManager = UserManager.getInstance();
        for (User user : userManager.getArraylist()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                return true;
            }
        }

        // Handle admin login logic if required
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}
