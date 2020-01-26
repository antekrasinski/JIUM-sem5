/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bin2dec2binClient;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

/**
 * Class responsible for communication with server.
 * @author Antoni Krasinski
 * @version 1.0
 */
public class Bin2dec2binClient {

    /**
     * Port number.
     */
    private int port;
    
    /**
     * Server address.
     */
    private String serverAddress;
    
    /**
     * Socket necessary for connection between server and client.
     */
    private Socket socket;
    
    /**
     * Buffered reader for receiving messages from server.
     */
    private BufferedReader reader;
    
    /**
     * Print writer for sending messages to server.
     */
    private PrintWriter writer;
    
    /**
     * Constructor of client class.
     * @param propertiesFile - name of file containing properties
     */
    public Bin2dec2binClient (String propertiesFile) 
    {
        try{
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(propertiesFile);
        properties.load(input);
        port = Integer.parseInt(properties.getProperty("port"));
        serverAddress = properties.getProperty("serverAdress");
        socket = new Socket(serverAddress, port);
        reader = new BufferedReader(new InputStreamReader (socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(),true);
        }
        catch(IOException e)
        {
            System.err.println("Connection failed.");
        }
    }
 
    /**
     * Method sending messages containing chosen option from menu and binary number to be converted.
     * @param bin - binary value to be converted
     * @return - message from the server
     */
    public String bin2dec(String bin)
    {
        try{
            writer.println("BIN2DEC");
            String message=reader.readLine();
            if(message.contains("100"))
            {
                writer.println(bin);
                message=reader.readLine();
                if(message.contains("101"))
                {
                    return reader.readLine();
                }
            }
            if(message.contains("200"))
            {
                return "Connection error";
            }
            else
            {
                return message;
            }
        }
        catch(Exception e)
        {
            return "Incorrect data";
        }
    }
    
    /**
     * Method sending messages containing chosen option from menu and decimal number to be converted.
     * @param dec - decimal value to be converted
     * @return - message from the server
     */
    public String dec2bin(Integer dec)
    {
        try{
            writer.println("DEC2BIN");
            String message=reader.readLine();
            if(message.contains("100"))
            {
                writer.println(dec);
                message=reader.readLine();
                if(message.contains("101"))
                {
                    return reader.readLine();
                }
            }
            if(message.contains("200"))
            {
                return "Connection error";
            }
            else
            {
                return message;
            }
        }
        catch(Exception e)
        {
            return "Incorrect data";
        }
    }
    
    /**
     * Method sending messages containing chosen option from menu and list of binary numbers to be converted.
     * @param multipleBin - list of binary values to be converted
     * @return - message from the server
     */
    public String multipleBin2Dec(List<String> multipleBin)
    {
        try{
            writer.println("MULTIPLEBIN2DEC");
            String message=reader.readLine();
            if(message.contains("100"))
            {
                writer.println(multipleBin.get(0));
                message=reader.readLine();
                if(message.contains("101"))
                {
                    writer.println(multipleBin.get(1));
                    message=reader.readLine();
                    if(message.contains("102"))
                    {
                        writer.println(multipleBin.get(2));
                        message = reader.readLine();
                        if(message.contains("103"))
                        {
                            return reader.readLine();
                        }
                    }
                }
            }
            if(message.contains("200"))
            {
                return "Connection error";
            }
            else
            {
                return message;
            }
        }
        catch(Exception e)
        {
            return "Incorrect data";
        }
    }
    
    /**
    * Method closing the socket.
    * @throws IOException - thrown by socket
    */
    public void close() throws IOException
    {
        if(socket!=null)
            socket.close();
    }
}
