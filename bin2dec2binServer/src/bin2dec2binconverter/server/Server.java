package bin2dec2binconverter.server;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

/**
 * Class responsible for starting the connection.
 * @author Antoni Krasinski
 * @version 1.0
 */
public class Server implements Closeable{
    
    /**
     * Port number.
     */
    private int port;
    
    /**
     * Socket necessary for connection between server and client.
     */
    private final ServerSocket socket;
    
    /**
     * Constructor setting the socket.
     * @throws IOException - thrown by socket
     */
    public Server() throws IOException
    {
        socket = new ServerSocket(port);
    }
    
    /**
     * Constructor using properties file to make the connection.
     * @param propertiesFile - name of a file containing port number
     * @throws IOException - thrown when there will be problems with initialization.
     */
    public Server(String propertiesFile) throws IOException
    {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(propertiesFile);
        properties.load(input);
        port = Integer.parseInt(properties.getProperty("port"));
        socket = new ServerSocket(port);
        System.out.println("Server ready for client");
        while(true)
        {
            new ServerConnection(socket.accept()).start();
        }
    }
    
    /**
     * Method closing the connection on server site.
     * @throws IOException - thrown by socket
     */
    @Override
    public void close() throws IOException
    {
        if(socket != null)
        {
            socket.close();
        }
    }
}
