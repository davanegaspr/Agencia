
package BusinessLogic.Service2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the BusinessLogic.Service2 package. 
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

    private final static QName _OfrecerApoyoResponse_QNAME = new QName("http://Servicio.Control.Bienestar.ArquitecturaDeSoftware.UNal.edu.co/", "ofrecerApoyoResponse");
    private final static QName _OfrecerApoyo_QNAME = new QName("http://Servicio.Control.Bienestar.ArquitecturaDeSoftware.UNal.edu.co/", "ofrecerApoyo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: BusinessLogic.Service2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OfrecerApoyoResponse }
     * 
     */
    public OfrecerApoyoResponse createOfrecerApoyoResponse() {
        return new OfrecerApoyoResponse();
    }

    /**
     * Create an instance of {@link OfrecerApoyo }
     * 
     */
    public OfrecerApoyo createOfrecerApoyo() {
        return new OfrecerApoyo();
    }

    /**
     * Create an instance of {@link Rob }
     * 
     */
    public Rob createRob() {
        return new Rob();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfrecerApoyoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicio.Control.Bienestar.ArquitecturaDeSoftware.UNal.edu.co/", name = "ofrecerApoyoResponse")
    public JAXBElement<OfrecerApoyoResponse> createOfrecerApoyoResponse(OfrecerApoyoResponse value) {
        return new JAXBElement<OfrecerApoyoResponse>(_OfrecerApoyoResponse_QNAME, OfrecerApoyoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfrecerApoyo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicio.Control.Bienestar.ArquitecturaDeSoftware.UNal.edu.co/", name = "ofrecerApoyo")
    public JAXBElement<OfrecerApoyo> createOfrecerApoyo(OfrecerApoyo value) {
        return new JAXBElement<OfrecerApoyo>(_OfrecerApoyo_QNAME, OfrecerApoyo.class, null, value);
    }

}
