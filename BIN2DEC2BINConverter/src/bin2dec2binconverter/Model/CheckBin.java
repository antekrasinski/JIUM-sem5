
package bin2dec2binconverter.Model;
/**
 * Class checking if the binary number is correct 
 * @author Antoni Krasiński
 * @version 1.0
 */
public class CheckBin {
    
    /**
     * Method checking if the binary number is correct
     * @param bin - number to be checked
     * @throws NotBinaryNumberException - thrown when number is wrong 
     */
    public void checkBinaryNumber(String bin) throws NotBinaryNumberException
    {
        for(int i = 0; i < bin.length(); i++)
        {
            if(bin.charAt(i) != '0' && bin.charAt(i) != '1')
            {
                throw new NotBinaryNumberException(bin);
            }
        }
    }
}
