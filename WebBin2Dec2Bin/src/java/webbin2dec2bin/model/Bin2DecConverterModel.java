
package webbin2dec2bin.model;

/**
 * Class with bin2dec conversion logic
 * @author Antoni Krasiński
 * @version 1.0
 */
public class Bin2DecConverterModel {
    
    /**
     * Variable containing final result
     */
    private int dec;
    
    /**
     * Getter of a decimal number
     * @return dec
     */
    public int getDec()
    {
        return dec;
    }
    
    /**
     * Setter of a decimal number
     * @param dec - value to set the dec variable
     */
    public void setDec(int dec)
    {
        this.dec = dec;
    }
    
    /**
     * Non parametrical constructor
     */
    public Bin2DecConverterModel()
    {
        dec = 0;
    }
    
    /**
     * Logic behind bin2dec conversion
     * @param bin - binary number that user wants to convert
     */
    public void convertBin2Dec(String bin)
    {
        dec = 0;
        int counter = 0;
        for(int i = (bin.length()-1); i >= 0; i--)
        {
            if(bin.charAt(i) == '1')
            {
               dec += Math.pow(2, counter);
            }
            counter++;
        }
    }
}
