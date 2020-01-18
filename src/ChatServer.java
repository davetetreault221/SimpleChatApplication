import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {

    //Global Variables
    //**************************************************
    private int portNum;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThreads> userThreads = new HashSet<>();
    //**************************************************

    //Sets the Port Number Which the Server will be listening on
    //**************************************************
    ChatServer(int portNumber){
        this.portNum = portNumber;
    }
    //**************************************************


    public void start()
    {

        System.out.println("The Server is listening on port " + portNum + " for new client connections.");

        try{

            ServerSocket serverSocket = new ServerSocket(portNum);

            //Used to make sure that all new clients have their own threads
            //*******************************************************************
            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New User connected");

                UserThreads newUser = new UserThreads(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
            //*******************************************************************


        }
        catch(IOException e)
        {
            System.out.println("There was an error on the Chat Server" + e.getMessage());
        }
    }

    public static void main(String args[])
    {
        int port = 0;

        if(args.length < 1)
        {
            System.out.println("Must provide a port number for the server to listen on ");
            System.exit(0);
        }
        else if(args.length >= 2)
        {
            System.out.println("Please simply only provide one argument(the port number)");
            System.exit(0);
        }

        if(!args[0].isEmpty())
        {
            port = Integer.parseInt(args[0]);
        }

        ChatServer theServer = new ChatServer(port);
        theServer.start();

    }

    public void addUserName(String userName)
    {
        this.userNames.add(userName);
    }

    public Boolean hasUsers()
    {
        if(userThreads.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void printUsers()
    {
        System.out.println("These are all the users currently connected:");

        for(String currentUser: userNames)
        {
            System.out.println(currentUser);
        }
    }

    public void broadcast(String message, UserThreads currentUserThread)
    {
        for (UserThreads aUser : userThreads) {
            if (aUser != currentUserThread) {
                aUser.sendMessage(message);
            }
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    public void removeUser(String username, UserThreads currentUserThread)
    {
        userNames.remove(username);
        userThreads.remove(currentUserThread);
    }



}
