/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bin2dec2binconverter.server;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

/**
 *
 * @author Antek
 */
public class Server implements Closeable{
    private int port;
    private final ServerSocket socket;
    
    public Server() throws IOException
    {
        socket = new ServerSocket(port);
    }
    
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
    
    @Override
    public void close() throws IOException
    {
        if(socket != null)
        {
            socket.close();
        }
    }
}
