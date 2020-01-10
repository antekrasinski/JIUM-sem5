
package bin2dec2binconverter.view;
import bin2dec2binconverter.model.MenuEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Class containing the communication with a user by console application
 * @author Antoni Krasiński
 * @version 1.0
 */
public class View {
   
    /**
     * Method showing the main menu
     * @return option - option in a main menu (1 - bin2dec convertion, 2 - dec2bin converion, 3 - closing the program)
     */
    public MenuEnum showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-----------------");
        System.out.println("BIN2DEC2BIN Converter");
        System.out.println("-----------------");
        System.out.println("1) BIN2DEC");
        System.out.println("2) DEC2BIN");
        System.out.println("3) Multiple BIN2DEC");
        System.out.println("4) EXIT");
        System.out.println("-----------------");
        
        int option = 0;
        while (option == 0)
        {
            option = Integer.parseInt(scanner.next());
            if (option < 1 &&  option > 4)
            {
                System.out.println("Choose one of the options");
                option = 0;
            }
        }
        return MenuEnum.values()[option-1];
    }
    
    /** 
     * Method getting binary number from a user
     * @return bin - binary number put by the user
     */
    public String getBin()
    {
        Scanner scanner = new Scanner(System.in);
        String bin;
        
        System.out.println("Which binary number do you want to convert?");
        bin = scanner.next();
        
        return bin;
    }
    
    /**
     * Method getting binary number from user
     * @return dec - decimal number put by the user
     */
    public int getDec()
    {
        Scanner scanner = new Scanner(System.in);
        int dec;
        
        System.out.println("Which decimal number do you want to convert?");
        dec = Integer.valueOf(scanner.next());
        if(dec<0)
           System.out.println("Positive dec convertion only.");
        return dec;
    }
    /**
     * Method getting multiple binary numbers to be converted
     * @param msg - message shown to user
     * @return list - list of binary numbers separated with blank space
     * @deprecated 
     */
    @Deprecated
    public String getMultipleBin(String msg)
    {
        String tmp = "";
        String list = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        for(int i=0; i<=2; i++)
        {
            tmp = scanner.next();
            list = list + " " + tmp;
        }
        return list;
    }
    
    /**
     * Method getting multiple binary numbers to be converted
     * @return BinList - list containing numbers to be converted
     */
    public List<String> getMultipleBin()
    {
        Scanner scanner = new Scanner(System.in);
        List<String> binList = new ArrayList<>();
        String tmp;
        System.out.println("Which three binary numbers do you want to convert?");
        for(int i=0; i<=2; i++)
        {
            tmp = scanner.next();
            binList.add(tmp);
        }
        return binList;
    }
    
    /**
     * Method showing the results of the operations
     * @param result - result of the conversion
     */
    public void showResult(String result)
    {
        System.out.println("Result of convertion: " + result);
    }
  
}
