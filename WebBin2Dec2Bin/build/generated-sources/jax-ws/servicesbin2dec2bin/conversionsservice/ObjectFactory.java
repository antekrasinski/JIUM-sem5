
package servicesbin2dec2bin.conversionsservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicesbin2dec2bin.conversionsservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Bin2Dec_QNAME = new QName("http://conversionsService.servicesbin2dec2bin/", "bin2dec");
    private final static QName _Bin2DecResponse_QNAME = new QName("http://conversionsService.servicesbin2dec2bin/", "bin2decResponse");
    private final static QName _Dec2Bin_QNAME = new QName("http://conversionsService.servicesbin2dec2bin/", "dec2bin");
    private final static QName _Dec2BinResponse_QNAME = new QName("http://conversionsService.servicesbin2dec2bin/", "dec2binResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicesbin2dec2bin.conversionsservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bin2Dec }
     * 
     */
    public Bin2Dec createBin2Dec() {
        return new Bin2Dec();
    }

    /**
     * Create an instance of {@link Bin2DecResponse }
     * 
     */
    public Bin2DecResponse createBin2DecResponse() {
        return new Bin2DecResponse();
    }

    /**
     * Create an instance of {@link Dec2Bin }
     * 
     */
    public Dec2Bin createDec2Bin() {
        return new Dec2Bin();
    }

    /**
     * Create an instance of {@link Dec2BinResponse }
     * 
     */
    public Dec2BinResponse createDec2BinResponse() {
        return new Dec2BinResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bin2Dec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://conversionsService.servicesbin2dec2bin/", name = "bin2dec")
    public JAXBElement<Bin2Dec> createBin2Dec(Bin2Dec value) {
        return new JAXBElement<Bin2Dec>(_Bin2Dec_QNAME, Bin2Dec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bin2DecResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://conversionsService.servicesbin2dec2bin/", name = "bin2decResponse")
    public JAXBElement<Bin2DecResponse> createBin2DecResponse(Bin2DecResponse value) {
        return new JAXBElement<Bin2DecResponse>(_Bin2DecResponse_QNAME, Bin2DecResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dec2Bin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://conversionsService.servicesbin2dec2bin/", name = "dec2bin")
    public JAXBElement<Dec2Bin> createDec2Bin(Dec2Bin value) {
        return new JAXBElement<Dec2Bin>(_Dec2Bin_QNAME, Dec2Bin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dec2BinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://conversionsService.servicesbin2dec2bin/", name = "dec2binResponse")
    public JAXBElement<Dec2BinResponse> createDec2BinResponse(Dec2BinResponse value) {
        return new JAXBElement<Dec2BinResponse>(_Dec2BinResponse_QNAME, Dec2BinResponse.class, null, value);
    }

}
