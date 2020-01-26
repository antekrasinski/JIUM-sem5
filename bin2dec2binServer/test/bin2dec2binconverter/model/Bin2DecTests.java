package bin2dec2binconverter.model;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class containing tests of Bin2Dec converter
 * 
 * @author Antek
 * @version 1.0
 */
public class Bin2DecTests {
    
    Bin2DecConverterModel model = new Bin2DecConverterModel();
    
    /*
    * Test of Bin2Dec conversion
    */
    @Test
    public void testConvertion(){
        String bin = "1111";
        int correctResult = 15;
        model.convertBin2Dec(bin);
        assertEquals("For: " + bin + " negative", correctResult, model.getDec());
    }
    
    /*
    * Test of Bin2Dec conversion, when given number equals zero
    */
    @Test 
    public void testZero(){
        String bin = "0";
        int correctResult = 0;
        model.convertBin2Dec(bin);
        assertEquals("For: " + bin + " negative", correctResult, model.getDec());
    }
    
    /*
    * Test of Bin2Dec conversion for large binary number
    */
    @Test 
    public void testLargeBin(){
        String bin = "‭‭‭100001000111010001101011100011";
        int correctResult = 555555555;
        model.convertBin2Dec(bin);
        assertEquals("For: " + bin + " negative", correctResult, model.getDec());
    }
    
    /*
    * Test of Bin2Dec conversion for binary number starting with zeros 
    */
    @Test 
    public void testMeaninglessZeros(){
        String bin = "000000000011";
        int correctResult = 3;
        model.convertBin2Dec(bin);
        assertEquals("For: " + bin + " negative", correctResult, model.getDec());
    }
    
    /*
    * Test of Bin2Dec conversion, when given binary number is incorrect 
    */
    @Test
    public void testIncorrectBin(){
        String bin = "200";
        int correctResult = 0;
        model.convertBin2Dec(bin);
        assertEquals("For: " + bin + " negative", correctResult, model.getDec());
    }
}
