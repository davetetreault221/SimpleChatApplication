import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteClient extends Thread{

    //Class Variables
    //****************************
    private Socket socket;
    private Client client;
    private PrintWriter writer;
    //****************************

    //WriteClient Constructor
    //*****************************************************************************************
    WriteClient(Socket currentSocket, Client currentClient)
    {
        this.socket = currentSocket;
        this.client = currentClient;

        try {

            //Linking the PrintWriter to the OutputStream of Socket to be able to write to the server
            //*********************************************
             OutputStream output = socket.getOutputStream();
             writer = new PrintWriter(output, true);
            //*********************************************

        }
        catch(IOException e)
        {
            String message = e.getMessage();
            System.out.println("There was a error: " + message);
        }

    }
    //*****************************************************************************************


    //Run Method which is called in the Client Class
    //*****************************************************************************************
    public void run() {

        Console console = System.console();
        String userName = console.readLine("\nEnter your name: ");
        client.setUserName(userName);
        writer.println(userName);
        String text;


        //Infinite Loop that will continue to write to the server until the client wishes to close the socket
        //****************************************************
        do {
            text = console.readLine("[" + userName + "]: ");
            writer.println(text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
        //****************************************************

    }
    //*****************************************************************************************


//End of the WriteClient Class
//*****************************************************************************************
}
