package bin2dec2binconverter.model;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class containing tests of NotBinaryNumberException
 * 
 * @author Antek
 * @version 1.0
 */
public class CheckBinTests {
    
    CheckBin check = new CheckBin(); 
    
    /*
    * Test of NotBinaryNumberException, when binary number is correct
    */
    @Test
    public void testCorrectBin() throws Exception {
        String bin = "1001";
        try
        {
            check.checkBinaryNumber(bin);
        }
        catch(NotBinaryNumberException exception) {
        }
    }
    
    /*
    * Test of NotBinaryNumberException, when binary number is incorrect, because of other numbers than 1 or 0
    */
    @Test
    public void testIncorrectBin() throws Exception{
        String bin = "2002";
        try
        {
            check.checkBinaryNumber(bin);
            fail("Should be thrown when bin is not a binary.");
        }
        catch(NotBinaryNumberException e) {
        }
    }
    
    /*
    * Test of NotBinaryNumberException, when binary number is incorrect, because of special signs 
    */
    @Test
    public void testChars() throws Exception{
        String bin = "a!@#$%^&*,.";
        try
        {
            check.checkBinaryNumber(bin);
            fail("Should be thrown when bin is not a binary.");
        }
        catch(NotBinaryNumberException e){
        }
    }
}
