
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket clientSocket;

    public void chatClient() throws IOException {



        System.out.println("Trying connection");

        try {
            clientSocket = new Socket("localhost", 9990);
            System.out.println("Connected to: " + clientSocket.getInetAddress().getHostName());
        } catch (UnknownHostException ex) {
            System.out.println("Host unknown: " + ex.getMessage());
            System.exit(1);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        Thread t1  = new Thread(new ClientDispatcher(clientSocket));
        t1.start();


        BufferedReader inBuf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter outBuf = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        System.out.println("My message is:");
        String line = "";

        while (!line.equals("/quit")) {

            try {
                line = inBuf.readLine();
                outBuf.write(line);
                outBuf.newLine();
                outBuf.flush();

            } catch (IOException ex) {

                System.out.println("Sending error: " + ex.getMessage() + ", closing client...");

            }
        }
        }
    }
