import java.io.*;
import java.net.Socket;

public class ServerDispatcher implements Runnable {
    private Socket clientSocket;

    public ServerDispatcher(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inBuf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message;
            while ((message = inBuf.readLine()) != null) {
                System.out.println("Message received: " + message);
                MainServer.sendMessage(message);
            }

        } catch (IOException e) {
            System.out.println("Client connection lost.");
        }
    }
}
