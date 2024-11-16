import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements IAdminPrivileges {
    ArrayList<User> users= new ArrayList<User>();
    // ArrayList<IAdminPrivileges> admins = new ArrayList<IAdminPrivileges>();
    private static UserManager instance;
    private static final String USER_FILE = "User.csv";

    private UserManager() {}

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    public ArrayList<User> getArraylist() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");


                // Create the Member object
                User user = new User(details[0], details[1], details[2],
                        details[3]);
                users.add(user);

            }
        } catch (NumberFormatException ex) {
            System.out.println("invalid data");
        }
        return users;
    }

    @Override
    public void viewUsers() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (String d : data) {
                    System.out.print(d.trim() + " ");
                }
                System.out.println("\n");
            }
        }
    }

    @Override
    public void addUser(User user) throws IOException {

        users.add(user);
        saveUsers(users);
    }

    @Override
    public void updateUser(User user) throws IOException {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID().equals(user.getUserID())) {
                users.set(i, user);
                break;
            }
        }
        saveUsers(users);
    }

    @Override
    public void renameUserFile(String newName) throws IOException {
        File oldFile = new File(USER_FILE);
        File newFile = new File("resources/" + newName);
        if (!oldFile.renameTo(newFile)) {
            throw new IOException("Failed to rename file");
        }
    }

    private void saveUsers(List<User> users) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                bw.write(user.toString());
                bw.newLine();
            }
        }
    }
}
