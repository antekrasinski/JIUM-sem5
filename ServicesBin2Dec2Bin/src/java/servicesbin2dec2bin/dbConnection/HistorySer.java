package servicesbin2dec2bin.dbConnection;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;

/**
 * Service required for accessing the history of conversions.
 * @author Antek
 * @version 1.0
 */
@WebService(serviceName = "HistorySer")
@Stateless()
public class HistorySer {
    
    /**
     * Method required for getting history of conversions.
     * @return - history of conversions
     */
    @WebMethod(operationName = "getHistory")
    public String getHistory() {
        Connection dbCon = new Connection();
        return dbCon.getHistory();
    }
}
