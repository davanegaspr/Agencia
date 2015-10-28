
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

    private final static QName _MakeTransactionResponse_QNAME = new QName("http://Service2.BusinessLogic/", "makeTransactionResponse");
    private final static QName _MakeTransaction_QNAME = new QName("http://Service2.BusinessLogic/", "makeTransaction");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: BusinessLogic.Service2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakeTransaction }
     * 
     */
    public MakeTransaction createMakeTransaction() {
        return new MakeTransaction();
    }

    /**
     * Create an instance of {@link MakeTransactionResponse }
     * 
     */
    public MakeTransactionResponse createMakeTransactionResponse() {
        return new MakeTransactionResponse();
    }

    /**
     * Create an instance of {@link Rob }
     * 
     */
    public Rob createRob() {
        return new Rob();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeTransactionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service2.BusinessLogic/", name = "makeTransactionResponse")
    public JAXBElement<MakeTransactionResponse> createMakeTransactionResponse(MakeTransactionResponse value) {
        return new JAXBElement<MakeTransactionResponse>(_MakeTransactionResponse_QNAME, MakeTransactionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeTransaction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Service2.BusinessLogic/", name = "makeTransaction")
    public JAXBElement<MakeTransaction> createMakeTransaction(MakeTransaction value) {
        return new JAXBElement<MakeTransaction>(_MakeTransaction_QNAME, MakeTransaction.class, null, value);
    }

}
