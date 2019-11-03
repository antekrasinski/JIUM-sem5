
package bin2dec2binconverter;
import bin2dec2binconverter.Controller.Controller;
import bin2dec2binconverter.Model.Bin2DecConverterModel;
import bin2dec2binconverter.Model.Dec2BinConverterModel;
import bin2dec2binconverter.View.View;
/**
 * Class with main method
 * @author Antoni Krasińśki
 * @version 1.0
 */
public class BIN2DEC2BINConverter {

    /**
     * Main method
     * @param args the command line arguments
     * args[0] - option in a main menu (1 - bin2dec convertion 2 - dec2bin converion)
     */
    public static void main(String[] args) 
    {
        Bin2DecConverterModel bin2DecModel = new Bin2DecConverterModel();
        Dec2BinConverterModel dec2BinModel = new Dec2BinConverterModel();
        View consoleView = new View();
        Controller controller = new Controller (consoleView, bin2DecModel, dec2BinModel);
        controller.runProgram(args);
    }
    
}
