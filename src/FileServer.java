import java.net.*;
import java.io.*;

/**
 * Yasmin Datario
 * Marissa Villaceran
 */
public class FileServer
{
    public static void main(String[] args)
    {
        int nPort = Integer.parseInt(args[0]);
        System.out.println("Server: Listening on port " + args[0] + "...");
        ServerSocket serverSocket;
        Socket serverEndpoint;

        try
        {
            serverSocket = new ServerSocket(nPort);
            serverEndpoint = serverSocket.accept();

            System.out.println("Server: New client connected: " + serverEndpoint.getRemoteSocketAddress());

            // Send file to client
            DataOutputStream dosWriter = new DataOutputStream(serverEndpoint.getOutputStream());
            File file = new File("Download.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            dosWriter.writeInt(buffer.length);
            dosWriter.write(buffer);
            System.out.println("Server: Sending file " + file.getName() + " (" + buffer.length + " bytes)");

            serverEndpoint.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("Server: Connection is terminated.");
        }
    }
}