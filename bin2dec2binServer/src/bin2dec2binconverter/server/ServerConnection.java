package bin2dec2binconverter.server;

import bin2dec2binconverter.model.Bin2DecConverterModel;
import bin2dec2binconverter.model.CheckBin;
import bin2dec2binconverter.model.Dec2BinConverterModel;
import bin2dec2binconverter.model.NotBinaryNumberException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class providing connection between server and client.
 * @author Antoni Krasinski
 * @version 1.0
 */
public class ServerConnection extends Thread{
    
    /**
     * Socket necessary for connection between server and client.
     */
    private final Socket socket;
    
    /**
     * Buffered reader for receiving messages from client.
     */
    private final BufferedReader reader;
    
    /**
     * Print writer for sending messages to client.
     */
    private final PrintWriter writer;
    
    private Dec2BinConverterModel dec2BinModel;

    private Bin2DecConverterModel bin2DecModel;
    
    /**
     * Constructor of ServerConnection setting PrintWriter and BufferedReader with a socket received in parameter.
     * @param socket - necessary for the connection
     * @throws IOException - thrown by socket
     */
    public ServerConnection(Socket socket) throws IOException
    {
        this.socket = socket;
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    /**
     * Method invoking functions chosen by user.
     */
    @Override
    public final void run()
    {
        try
        {
            while(true)
            {
                String message = reader.readLine();
                System.out.println(message);
                switch(message)
                {
                    case "BIN2DEC":
                    {
                        bin2Dec();
                        break;
                    }
                    case "DEC2BIN":
                    {
                        dec2Bin();
                        break;
                    }
                    case "MULTIPLEBIN2DEC":
                    {
                        multipleBin2Dec();
                        break;
                    }
                    case "HELP":
                    {
                        serverHelp();
                        break;
                    }
                }
            }
        }
        catch(IOException e)
        {
            System.err.println("ServerConnection error: " + e.getMessage());
        }
    }
    
    /**
     * Method for communicating with Client through bin2Dec conversion.
     */
    private void bin2Dec()
    {
        boolean error = false;
        try
        {
            writer.println("100: BIN2DEC methode");
            String bin = reader.readLine();
            writer.println("101 : received binary number");
            bin2DecModel = new Bin2DecConverterModel();
            CheckBin check = new CheckBin();

                    try {
                        check.checkBinaryNumber(bin);
                    } catch (NotBinaryNumberException exc) {
                        writer.println("300: " + exc.getMessage());
                        error = true;
                    }

                    if (error == false) {
                        bin2DecModel.convertBin2Dec(bin);
                        writer.println(Integer.toString(bin2DecModel.getDec()));
                        System.out.println("Bin: " + bin + " DEC: " + bin2DecModel.getDec());
                    }
        }
        catch(IOException e)
        {
            writer.println("400: IOException" + e.getMessage());
        }
    }
    
    /**
     * Method for communicating with Client through dec2Bin conversion.
     */
    private void dec2Bin()
    {
        try
        {
            writer.println("100: DEC2BIN methode");
            int dec = Integer.parseInt(reader.readLine());
            writer.println("101 : received decymal number");
            dec2BinModel = new Dec2BinConverterModel();
            dec2BinModel.convertDec2Bin(dec);
            
            writer.println(dec2BinModel.getBin());
            System.out.println("DEC: " + dec + " BIN: " + dec2BinModel.getBin());
        }
        catch(IOException e)
        {
            writer.println("400: IOException" + e.getMessage());
        }
    }
    
    /**
     * Method for communicating with Client through multipleBin2Dec conversion.
     */
   private void multipleBin2Dec()
    {
        boolean error = false;
        String input = "";
        String result = "";
        try
        {
            writer.println("100: BIN2DEC methode");
            String tmp = reader.readLine();
            writer.println("101 : received first binary number");

            List<String> binList = new ArrayList();
            binList.add(tmp);
            
            tmp = reader.readLine();
            writer.println("102 : received second binary number");

            binList.add(tmp);
            
            tmp = reader.readLine();
            writer.println("103 : received third binary number");

            binList.add(tmp);
            
            bin2DecModel = new Bin2DecConverterModel();
            for (String element : binList) {
                        CheckBin checkBins = new CheckBin();

                        try {
                            checkBins.checkBinaryNumber(element);
                        } catch (NotBinaryNumberException exc) {
                            writer.println("300: " + exc.getMessage());
                            error = true;
                        }

                        if (error == false) {
                            bin2DecModel.convertBin2Dec(element);
                            result += (Integer.toString(bin2DecModel.getDec()) + " ");
                            input += element + " ";
                            bin2DecModel.setDec(0);
                        }
            }
            System.out.println("BIN: " + input);
            System.out.println("DEC: " + result);
            writer.println(result);
        }
        catch(IOException e)
        {
            writer.println("400: IOException" + e.getMessage());
        }
    }
    
   /**
    * Method showing list of avaliable commands. 
    */
   public void serverHelp() 
    {
        writer.println("HELP");
        writer.println("BIN2DEC DEC2BIN MULTIPLEBIN2DEC HELP");
    }
   
   /**
    * Method closing the socket.
    * @throws IOException - thrown by socket
    */
    public void close() throws IOException
    {
        if(socket !=null)
        {
            socket.close();
        }
    }
}
