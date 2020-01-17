import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {


    //Global Variables
    //*******************************************
    private String hostname;
    private int port;
    private String userName;
    //*******************************************


    //Constructor
    //*****************************************************************************
    Client(String hostname, int desiredPort)
    {
        this.port = desiredPort;
        this.hostname = hostname;
    }
    //*****************************************************************************


    //Execute Function: Allows the client to start reading and writting to the server using declared socket
    //*****************************************************************************
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the chat server");

            new ReadClient(socket, this).start();
            new WriteClient(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
    }
    //*****************************************************************************


    //Setters and Getters
    //*****************************************************************************

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }
    //*****************************************************************************


    //Main Function
    //*****************************************************************************
    public static void main(String args[])
    {
        //Check if proper amount of Arguments Are Provided

        if(args.length < 2)
        {
            System.out.println("There are not enough arguments provided for this mini chat program!");
        }
        else if(args.length > 2)
        {
            System.out.println("There are too many arguments getting provided to this mini chat program");

        }

        //Call Execute and start interaction with server
        //*********************************
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        Client client = new Client(hostname, port);
        client.execute();
        //*********************************
    }
    //*****************************************************************************



//End of Client Class
//*****************************************************************************
}
