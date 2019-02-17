
package xyz.dingjiacheng.hbasewebservice.service.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xyz.dingjiacheng.hbasewebservice.service.impl package. 
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

    private final static QName _SayHelloResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "sayHelloResponse");
    private final static QName _ScanPositionByOrderResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanPositionByOrderResponse");
    private final static QName _ScanOrderByDriver_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanOrderByDriver");
    private final static QName _SayHello_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "sayHello");
    private final static QName _ScanOrderByDriverResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanOrderByDriverResponse");
    private final static QName _ScanPositionByOrder_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanPositionByOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xyz.dingjiacheng.hbasewebservice.service.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ScanOrderByDriverResponse }
     * 
     */
    public ScanOrderByDriverResponse createScanOrderByDriverResponse() {
        return new ScanOrderByDriverResponse();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link ScanPositionByOrder }
     * 
     */
    public ScanPositionByOrder createScanPositionByOrder() {
        return new ScanPositionByOrder();
    }

    /**
     * Create an instance of {@link ScanPositionByOrderResponse }
     * 
     */
    public ScanPositionByOrderResponse createScanPositionByOrderResponse() {
        return new ScanPositionByOrderResponse();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link ScanOrderByDriver }
     * 
     */
    public ScanOrderByDriver createScanOrderByDriver() {
        return new ScanOrderByDriver();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScanPositionByOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "scanPositionByOrderResponse")
    public JAXBElement<ScanPositionByOrderResponse> createScanPositionByOrderResponse(ScanPositionByOrderResponse value) {
        return new JAXBElement<ScanPositionByOrderResponse>(_ScanPositionByOrderResponse_QNAME, ScanPositionByOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScanOrderByDriver }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "scanOrderByDriver")
    public JAXBElement<ScanOrderByDriver> createScanOrderByDriver(ScanOrderByDriver value) {
        return new JAXBElement<ScanOrderByDriver>(_ScanOrderByDriver_QNAME, ScanOrderByDriver.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScanOrderByDriverResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "scanOrderByDriverResponse")
    public JAXBElement<ScanOrderByDriverResponse> createScanOrderByDriverResponse(ScanOrderByDriverResponse value) {
        return new JAXBElement<ScanOrderByDriverResponse>(_ScanOrderByDriverResponse_QNAME, ScanOrderByDriverResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScanPositionByOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "scanPositionByOrder")
    public JAXBElement<ScanPositionByOrder> createScanPositionByOrder(ScanPositionByOrder value) {
        return new JAXBElement<ScanPositionByOrder>(_ScanPositionByOrder_QNAME, ScanPositionByOrder.class, null, value);
    }

}
