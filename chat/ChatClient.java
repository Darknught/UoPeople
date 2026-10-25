import java.io.*;
import java.net.*;

/**
* ChatClient.java
* ----------------
* This class implements a simple chat client that connects to the ChatServer.
* Once connected, it allows the user to send and receive messages in real-time.
*
* The client uses two threads:
* 1. One for reading user input and sending it to the server.
* 2. Another for receiving messages from the server and printing them to the console.
*
* Key Concepts Demonstrated:
* - Client-side socket communication
* - Input/Output stream handling
* - Threading for simultaneous send/receive operations
*/

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("Connected to the chat server.");

            new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            }).start();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Error connecting to the server: " + e.getMessage());
        }
    }
}
