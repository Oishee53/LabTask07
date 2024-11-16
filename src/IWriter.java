import java.io.IOException;

public interface IWriter extends IReader {
    void addUser(User user) throws IOException;
}
