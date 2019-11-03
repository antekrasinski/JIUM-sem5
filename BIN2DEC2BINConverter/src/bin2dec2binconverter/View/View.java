
package bin2dec2binconverter.View;
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
    public int showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-----------------");
        System.out.println("BIN2DEC2BIN Converter");
        System.out.println("-----------------");
        System.out.println("1) BIN2DEC");
        System.out.println("2) DEC2BIN");
        System.out.println("3) EXIT");
        System.out.println("-----------------");
        
        int option = 0;
        while (option == 0)
        {
            option = Integer.parseInt(scanner.next());
            if (option != 1 && option != 2 && option != 3)
            {
                System.out.println("Choose one of the options");
                option = 0;
            }
        }
        return option;
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
        
        System.out.println("Which decymal number do you want to convert?");
        dec = Integer.valueOf(scanner.next());
        
        return dec;
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
