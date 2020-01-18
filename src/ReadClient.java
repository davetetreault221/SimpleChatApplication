import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadClient extends Thread {

    //Class Variables
    //*******************************
    private BufferedReader reader;
    private Socket socket;
    private Client client;
    //*******************************


    //Note
    //*************************************************
    /*
        When reading from a socket in java we must use an InputStream Class

        InputStream Class: Used to read data FROM a socket (whatever was passed into the other end of the socket)

        OutputStream Class: Used to write data TO a socket (for whoever it might concern)

    */
    //*************************************************


    //ReadClient Constructor
    //*****************************************************************************
    ReadClient(Socket currentSocket, Client currentClient)
    {
        this.socket = currentSocket;
        this.client = currentClient;

        try {

            //This is used to read input from a socket
            //***************************************************
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            //***************************************************


        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    //*****************************************************************************

    //Run
    //*****************************************************************************
    public void run() {

        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);

                // prints the username after displaying the server's message
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }

            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                //ex.printStackTrace();
                break;
            }
        }
    }
    //*****************************************************************************



}
