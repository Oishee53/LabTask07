import java.io.IOException;

public interface IAdminPrivileges extends IWriter {
    void updateUser(User user) throws IOException;
    void renameUserFile(String newName) throws IOException;
}

