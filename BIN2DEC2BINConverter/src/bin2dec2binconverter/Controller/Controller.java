
package bin2dec2binconverter.Controller;
import bin2dec2binconverter.Model.Bin2DecConverterModel;
import bin2dec2binconverter.Model.Dec2BinConverterModel;
import bin2dec2binconverter.Model.CheckBin;
import bin2dec2binconverter.Model.NotBinaryNumberException;
import bin2dec2binconverter.View.View;
/**
 * Class that includes method running the program and control over MVC pattern
 * @author Antoni Krasińśki
 * @version 1.0
 */
public class Controller 
{
    /**
     * View in MVC pattern
     */
    private View view;
    
    /**
     * Bin2Dec conversion model
     */
    private Bin2DecConverterModel bin2DecModel;
    
    /**
     * Dec2Bin conversion model
     */
    private Dec2BinConverterModel dec2BinModel;
    
    /**
     * Controller constructor
     * @param view - view in MVC pattern
     * @param bin2DecModel - Bin2Dec conversion model
     * @param dec2BinModel - Dec2Bin conversion model
     */
    public Controller(View view, Bin2DecConverterModel bin2DecModel, Dec2BinConverterModel dec2BinModel)
    {
        this.bin2DecModel = bin2DecModel;
        this.dec2BinModel = dec2BinModel;
        this.view = view;
    }
    
    /**
     * Method used to run the program
     * @param args - the command line arguments
     */
    public void runProgram(String args[])
    {
        int option = 0;
        int dec = 0;
        String bin = "";
        boolean error = false;
        
        if(args.length != 0)
        {
            option = Integer.valueOf(args[0]);
        }
        else 
        {
            option = view.showMenu();
        }
        if (option == 1) 
        {
            bin = view.getBin(); 
            
            CheckBin check = new CheckBin();
            
            try 
            {
                check.checkBinaryNumber(bin);
            } 
            catch (NotBinaryNumberException exc) 
            {
                System.err.println(exc.getMessage());
                error = true;
            }

            if (error == false) 
            {
                bin2DecModel.convertBin2Dec(bin);
                view.showResult(Integer.toString(bin2DecModel.getDec()));
            }
        } 
        else if (option == 2) 
        {
            dec = view.getDec();
            dec2BinModel.convertDec2Bin(dec);
            view.showResult(dec2BinModel.getBin());
        }
    }
}
