import java.io.*;
import java.net.Socket;

public class ClientDispatcher implements  Runnable{
    Socket clientSocket;

    public ClientDispatcher(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inBufServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = inBufServer.readLine();
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

