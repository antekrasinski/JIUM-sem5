
package bin2dec2binconverter.model;

/**
 * Exception class defining the exception of incorrect binary number
 * @author Antoni Krasiński 
 * @version 1.0
 */
public class NotBinaryNumberException extends Exception 
{
    /**
     * Constructor of the exception of incorrect binary number
     * @param bin - wrong number that exception is thrown by
     */
    public NotBinaryNumberException(String bin) 
    {
        super(bin + " is not a binary number.");
    }
    
}
