import java.io.*;
import java.net.*;
import java.util.*;

/**
* ChatServer.java
* ----------------
* This class implements a simple multithreaded chat server using Java sockets.
* It listens for incoming client connections on a fixed port (12345) and assigns
* each connected client a unique ID. Each client runs in its own thread (ClientHandler),
* allowing multiple clients to chat concurrently.
*
* When a client sends a message, the server broadcasts it to all connected clients.
* If a client disconnects, the server removes it from the list and notifies the rest.
*
* Key Concepts Demonstrated:
* - Java Networking (ServerSocket, Socket)
* - Multithreading using Thread class
* - HashMap for managing multiple clients
* - Message broadcasting
*/

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

/**
* ClientHandler.java (Inner Class)
* --------------------------------
* This class runs as a separate thread for each connected client.
* It listens for messages from that client and sends them to the server for broadcasting.
*
* Each ClientHandler maintains its own input/output streams for communication.
*/

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
