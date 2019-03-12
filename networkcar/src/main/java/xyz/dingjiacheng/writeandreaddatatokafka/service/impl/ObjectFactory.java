
package xyz.dingjiacheng.writeandreaddatatokafka.service.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xyz.dingjiacheng.writeandreaddatatokafka.service.impl package. 
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

    private final static QName _GetPosition_QNAME = new QName("http://impl.service.writeAndReadDataToKafka.dingjiacheng.xyz/", "getPosition");
    private final static QName _GetPositionResponse_QNAME = new QName("http://impl.service.writeAndReadDataToKafka.dingjiacheng.xyz/", "getPositionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xyz.dingjiacheng.writeandreaddatatokafka.service.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPosition }
     * 
     */
    public GetPosition createGetPosition() {
        return new GetPosition();
    }

    /**
     * Create an instance of {@link GetPositionResponse }
     * 
     */
    public GetPositionResponse createGetPositionResponse() {
        return new GetPositionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPosition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.writeAndReadDataToKafka.dingjiacheng.xyz/", name = "getPosition")
    public JAXBElement<GetPosition> createGetPosition(GetPosition value) {
        return new JAXBElement<GetPosition>(_GetPosition_QNAME, GetPosition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPositionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.writeAndReadDataToKafka.dingjiacheng.xyz/", name = "getPositionResponse")
    public JAXBElement<GetPositionResponse> createGetPositionResponse(GetPositionResponse value) {
        return new JAXBElement<GetPositionResponse>(_GetPositionResponse_QNAME, GetPositionResponse.class, null, value);
    }

}
