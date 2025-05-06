import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Client c1 = new Client();

        try {
            c1.chatClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}