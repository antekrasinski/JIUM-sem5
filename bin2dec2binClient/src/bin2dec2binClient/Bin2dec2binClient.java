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
 *
 * @author Antek
 */
public class Bin2dec2binClient {

    private int port;
    
    private String serverAddress;
    
    private Socket socket;
    
    private BufferedReader reader;
    
    private PrintWriter writer;
    
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
    
    public void close() throws IOException
    {
        if(socket!=null)
            socket.close();
    }
}
