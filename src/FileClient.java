import java.net.*;
import java.io.*;

/**
 * Yasmin Datario
 * Marissa Villaceran
 */
public class FileClient
{
    public static void main(String[] args)
    {
        String sServerAddress = args[0];
        int nPort = Integer.parseInt(args[1]);

        try
        {
            Socket clientEndpoint = new Socket(sServerAddress, nPort);

            System.out.println("Client: Connected to server at" + clientEndpoint.getRemoteSocketAddress());

            DataInputStream disReader = new DataInputStream(clientEndpoint.getInputStream());
            int fileSize = disReader.readInt();
            byte[] buffer = new byte[fileSize];
            disReader.readFully(buffer, 0, fileSize);
            File file = new File("Received.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer);
            System.out.println("Client: Downloaded file " + file.getName());

            clientEndpoint.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("Client: Connection is terminated.");
        }
    }
}