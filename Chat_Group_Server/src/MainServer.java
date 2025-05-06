import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class MainServer {
    private static final int PORT = 9990;
    private static LinkedList<Socket> clients = new LinkedList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                synchronized (clients) {
                    clients.add(clientSocket);
                }
                Thread t = new Thread(new ServerDispatcher(clientSocket));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void sendMessage (String message) {
        synchronized (clients) {
            for (Socket client : clients) {
                try {
                    BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    bufOut.write(message);
                    bufOut.newLine();
                    bufOut.flush();
                } catch (IOException e) {
                    System.out.println("Error sending message to a client.");
                }
            }
        }
    }
}