import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Map<Integer, ClientHandler> clients = new HashMap<>();
    private static int clientIdCounter = 1;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started on port: " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = clientIdCounter++;
                ClientHandler handler = new ClientHandler(socket, clientId);
                clients.put(clientId, handler);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    static void broadcast(String message, int senderId) {
        for (ClientHandler client : clients.values()) {
            if (client.getClientId() != senderId) {
                client.sendMessage(message);
            }
        }
    }

    static void removeClient(int clientId) {
        clients.remove(clientId);
        System.out.println("Client " + clientId + " disconnected.");
    }
}

class ClientHandler extends Thread {
    private Socket socket;
    private int clientId;
    private PrintWriter out;

    public ClientHandler(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Welcome! Your ID is: " + clientId);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client " + clientId + ": " + message);
                ChatServer.broadcast("Client " + clientId + ": " + message, clientId);
            }
        } catch (IOException e) {
            System.out.println("Client " + clientId + " connection error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatServer.removeClient(clientId);
        }
    }
}


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
