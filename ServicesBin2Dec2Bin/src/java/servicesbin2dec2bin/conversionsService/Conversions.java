package servicesbin2dec2bin.conversionsService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import servicesbin2dec2bin.model.Bin2DecConverterModel;
import servicesbin2dec2bin.model.CheckBin;
import servicesbin2dec2bin.model.Dec2BinConverterModel;
import servicesbin2dec2bin.model.NotBinaryNumberException;

/**
 * Service required for conversion methods.
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebService(serviceName = "Conversions")
@Stateless()
public class Conversions {

    /**
     * Method required for bin2dec conversion.
     * @param bin - binary value to be converted
     * @return - result of conversion
     */
    @WebMethod(operationName = "bin2dec")
    public String bin2dec(@WebParam(name = "bin") String bin) {
        Bin2DecConverterModel model = new Bin2DecConverterModel();
        CheckBin check = new CheckBin();
        //Connection dbCon = new Connection();
        try
        {
        check.checkBinaryNumber(bin);
        } catch (NotBinaryNumberException e)
        {
            return e.getMessage();
        }
        model.convertBin2Dec(bin);
        //dbCon.addEntity("Bin: " + bin + " Dec: " + Integer.toString(model.getDec()));
        return Integer.toString(model.getDec());
    }

    /**
     * Method required for dec2bin conversion.
     * @param dec - decimal value to be converted
     * @return - result of conversion
     */
    @WebMethod(operationName = "dec2bin")
    public String dec2bin(@WebParam(name = "dec") String dec) {
        Dec2BinConverterModel model = new Dec2BinConverterModel();
        //Connection dbCon = new Connection();
        model.convertDec2Bin(Integer.parseInt(dec));
        //dbCon.addEntity("Bin: " + model.getBin() + " Dec: " + dec);
        return model.getBin();
    }
}
