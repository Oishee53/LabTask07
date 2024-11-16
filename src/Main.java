import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Authentication authentication = new Authentication();
        String[] csvFiles = {
                "User.csv",
        };

        // Create each file
        for (String csvFile : csvFiles) {
            createCSVFile(csvFile);
        }
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authentication.login(username, password)) {
            User currentUser = authentication.getCurrentUser();
            UserManager userManager = UserManager.getInstance();

            System.out.println("Login successful! User type: " + currentUser.getUserType());
            if (currentUser.getUserType().equals("Admin")) {
                // Admin operations
                System.out.println("1. View Users");
                System.out.println("2. Add User");
                System.out.println("3. Update User");
                System.out.println("4. Rename User File");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> userManager.viewUsers();
                    case 2 -> {
                        System.out.print("Enter new username: ");
                        String newUsername = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String newPassword = scanner.nextLine();
                        System.out.print("Enter user type: ");
                        String userType = scanner.nextLine();
                        userManager.addUser(new User(newUsername, email, newPassword,userType));
                    }
                    case 3 -> {
                        System.out.print("Enter UserID to update: ");
                        String userID = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        String newPass = scanner.nextLine();
                        System.out.print("Enter new type: ");
                        String newType = scanner.nextLine();
                        User userToUpdate = new User("", "", "", ""); // Fill with loaded data
                        userToUpdate.setEmail(newEmail);
                        userToUpdate.setPassword(newPass);
                        userToUpdate.setUserType(newType);
                        userManager.updateUser(userToUpdate);
                    }
                    case 4 -> {
                        System.out.print("Enter new file name: ");
                        String newName = scanner.nextLine();
                        userManager.renameUserFile(newName);
                    }
                }
            } else if (currentUser.getUserType().equals("Power")) {
                // Power user operations
                System.out.println("1. View Users");
                System.out.println("2. Add User");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    userManager.viewUsers();
                } else if (choice == 2) {
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    userManager.addUser(new User(newUsername, email, newPassword, "Regular"));
                }
            } else {
                // Regular user operations
                userManager.viewUsers();
            }
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private static void createCSVFile(String fileName) {
        File file = new File(fileName);
        try {
            // Create the file
            if (file.createNewFile()) {
                System.out.println("CSV file created: " + file.getName());
            } else {
                // System.out.println("CSV file already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating: " + fileName);
            e.printStackTrace();
        }
    }
}
