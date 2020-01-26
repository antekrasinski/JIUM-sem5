
package bin2dec2binconverter.model;

/**
 * Class with dec2bin conversion logic
 * @author Antoni Krasiński
 * @version 1.0
 */
public class Dec2BinConverterModel {
    
    /**
     * Variable containing final result
     */
    private String bin;
    
    /**
     * Getter of a binary number
     * @return bin
     */
    public String getBin()
    {
        return bin;
    }
    
    /**
     * Setter of a binary number
     * @param bin - value to set the bin variable
     */
    public void setBin(String bin)
    {
        this.bin = bin;
    }
    
    /**
     * Non parametrical constructor
     */
    public Dec2BinConverterModel()
    {
        bin = "";
    }
    
     /**
     * Logic behind dec2bin converion
     * @param dec - decimal number that user wants to convert
     */
    public void convertDec2Bin(int dec)
    {
        bin = "";
        if(dec == 0)
        {
            bin = "0";
        }
        else 
        {
            while (dec != 0) 
            {
                bin = dec % 2 + bin;
                dec = dec / 2;
            }
        }
    }
}
