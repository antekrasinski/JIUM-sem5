
package servicesbin2dec2bin.dbconnection;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HistorySer", targetNamespace = "http://dbConnection.servicesbin2dec2bin/", wsdlLocation = "http://localhost:8080/HistorySer/HistorySer?wsdl")
public class HistorySer_Service
    extends Service
{

    private final static URL HISTORYSER_WSDL_LOCATION;
    private final static WebServiceException HISTORYSER_EXCEPTION;
    private final static QName HISTORYSER_QNAME = new QName("http://dbConnection.servicesbin2dec2bin/", "HistorySer");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/HistorySer/HistorySer?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HISTORYSER_WSDL_LOCATION = url;
        HISTORYSER_EXCEPTION = e;
    }

    public HistorySer_Service() {
        super(__getWsdlLocation(), HISTORYSER_QNAME);
    }

    public HistorySer_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), HISTORYSER_QNAME, features);
    }

    public HistorySer_Service(URL wsdlLocation) {
        super(wsdlLocation, HISTORYSER_QNAME);
    }

    public HistorySer_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HISTORYSER_QNAME, features);
    }

    public HistorySer_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HistorySer_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HistorySer
     */
    @WebEndpoint(name = "HistorySerPort")
    public HistorySer getHistorySerPort() {
        return super.getPort(new QName("http://dbConnection.servicesbin2dec2bin/", "HistorySerPort"), HistorySer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HistorySer
     */
    @WebEndpoint(name = "HistorySerPort")
    public HistorySer getHistorySerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://dbConnection.servicesbin2dec2bin/", "HistorySerPort"), HistorySer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HISTORYSER_EXCEPTION!= null) {
            throw HISTORYSER_EXCEPTION;
        }
        return HISTORYSER_WSDL_LOCATION;
    }

}