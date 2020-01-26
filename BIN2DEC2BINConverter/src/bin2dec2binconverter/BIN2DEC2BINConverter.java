
package bin2dec2binconverter;
import bin2dec2binconverter.server.Server;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Class with main method
 * @author Antoni Krasińśki
 * @version 1.0
 */
public class BIN2DEC2BINConverter {

    /**
     * Main method
     * @param args the command line arguments
     * args[0] - option in a main menu (1 - bin2dec conversion 2 - dec2bin conversion)
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
        try (Server server = new Server(".properties")){
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
