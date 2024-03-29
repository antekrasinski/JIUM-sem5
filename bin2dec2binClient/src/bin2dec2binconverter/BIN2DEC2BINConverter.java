package bin2dec2binconverter;

import bin2dec2binView.Converter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class containing the main method
 * @author Antoni Krasinski
 * @version 1.0
 */
public class BIN2DEC2BINConverter {
    /**
     * Main method.
     * @param args - arguments from command line
     */
    public static void main(String[] args)
    {
        Properties properties = new Properties();
        properties.setProperty("port", "8888");
        properties.setProperty("server_adress", "192.168.1.20"); 
        
        try (FileOutputStream out = new FileOutputStream(".properties")) {
            properties.store(out, "--Konfiguracja--");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        Converter converter = new Converter();
        converter.run();
    }
}
