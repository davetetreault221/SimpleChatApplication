import java.io.*;
import java.net.Socket;

public class UserThreads extends Thread{


    //Global Variables
    private Socket currentSocket;
    private ChatServer selectedServer;
    private PrintWriter writer;


    //Constructor
    UserThreads(Socket socket, ChatServer server)
    {
        this.selectedServer = server;
        this.currentSocket = socket;
    }


    public void run()
    {

        try
        {
            InputStream input = currentSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = currentSocket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers();

            String userName = reader.readLine();
            selectedServer.addUserName(userName);

            String serverMessage = "New user connected: " + userName;
            selectedServer.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                selectedServer.broadcast(serverMessage, this);

            } while (!clientMessage.equals("bye"));

            selectedServer.removeUser(userName, this);
            currentSocket.close();

            serverMessage = userName + " has quitted.";
            selectedServer.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }

    }


    public void printUsers()
    {
        //Check if the server has users
        if(selectedServer.hasUsers())
        {
            selectedServer.printUsers();
        }
    }

    public void sendMessage(String message)
    {
        writer.println(message);
    }

}
