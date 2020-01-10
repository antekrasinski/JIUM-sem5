package bin2dec2binconverter.model;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class containing tests of Dec2Bin converter
 * 
 * @author Antek
 * @version 1.0
 */
public class Dec2BinTests {
    
   Dec2BinConverterModel model = new Dec2BinConverterModel();
   
   /*
   * Test of Dec2Bin conversion
   */
   @Test
   public void testConvertion()
   {
       int dec = 15;
       String correctResult = "1111";
       model.convertDec2Bin(dec);
       assertEquals("For: " + dec + " negative", correctResult, model.getBin());
   }
   
   /*
   * Test of Dec2Bin conversion, when given number equals zero
   */
   @Test
   public void testZero()
   {
       int dec = 0;
       String correctResult = "0";
       model.convertDec2Bin(dec);
       assertEquals("For: " + dec + " negative", correctResult, model.getBin());
   }
   
   /*
    * Test of Dec2Bin conversion for large decymal number
    */
   @Test 
    public void testLargeDec(){
        int dec = 555555555;
        String correctResult = "100001000111010001101011100011";
        model.convertDec2Bin(dec);
        assertEquals("For: " + dec + " negative", correctResult, model.getBin());
    }
    
}

