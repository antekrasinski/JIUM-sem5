package bin2dec2binconverter.controller;

import bin2dec2binconverter.model.Bin2DecConverterModel;
import bin2dec2binconverter.model.Dec2BinConverterModel;
import bin2dec2binconverter.model.CheckBin;
import bin2dec2binconverter.model.MenuEnum;
import bin2dec2binconverter.model.NotBinaryNumberException;
import bin2dec2binconverter.view.View;
import java.util.List;

/**
 * Class that includes method running the program and control over MVC pattern
 *
 * @author Antoni Krasińśki
 * @version 1.0
 */
public class Controller {

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
     *
     * @param view - view in MVC pattern
     * @param bin2DecModel - Bin2Dec conversion model
     * @param dec2BinModel - Dec2Bin conversion model
     */
    public Controller(View view, Bin2DecConverterModel bin2DecModel, Dec2BinConverterModel dec2BinModel) {
        this.bin2DecModel = bin2DecModel;
        this.dec2BinModel = dec2BinModel;
        this.view = view;
    }

    /**
     * Method used to run the program
     *
     * @param args - the command line arguments
     */
    public void runProgram(String args[]) {
        MenuEnum option;
        Integer dec = 0;
        String bin = "";
        boolean error = false;
        boolean exit = false;
        while (exit == false) {
            if (args.length != 0) {
                option = MenuEnum.values()[Integer.valueOf(args[0])];
            } else {
                option = view.showMenu();
            }
            switch (option) {
                case BIN2DEC:

                    bin = view.getBin();

                    CheckBin check = new CheckBin();

                    try {
                        check.checkBinaryNumber(bin);
                    } catch (NotBinaryNumberException exc) {
                        System.err.println(exc.getMessage());
                        error = true;
                    }

                    if (error == false) {
                        bin2DecModel.convertBin2Dec(bin);
                        view.showResult(Integer.toString(bin2DecModel.getDec()));
                    }
                    break;

                case DEC2BIN:

                    dec = view.getDec();
                    if(dec>0){
                    dec2BinModel.convertDec2Bin(dec);
                    view.showResult(dec2BinModel.getBin());
                    }
                    break;
                    
                case MULTIPLE_BIN2DEC:

                    List<String> binList = view.getMultipleBin();
                    for (String element : binList) {
                        CheckBin checkBins = new CheckBin();

                        try {
                            checkBins.checkBinaryNumber(element);
                        } catch (NotBinaryNumberException exc) {
                            System.err.println(exc.getMessage());
                            error = true;
                        }

                        if (error == false) {
                            bin2DecModel.convertBin2Dec(element);
                            view.showResult(Integer.toString(bin2DecModel.getDec()));
                            bin2DecModel.setDec(0);
                        }
                    }
                    break;
                    
                case EXIT:
                    exit = true;
                    break;
            }
        }
    }
}