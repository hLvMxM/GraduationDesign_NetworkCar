
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
    private final static QName _WriteDataToPosition_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "writeDataToPosition");
    private final static QName _WriteDataToPositionResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "writeDataToPositionResponse");
    private final static QName _ScanPositionByOrderResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanPositionByOrderResponse");
    private final static QName _Exception_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "Exception");
    private final static QName _UpdateDataResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "updateDataResponse");
    private final static QName _ScanOrderByDriver_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanOrderByDriver");
    private final static QName _SayHello_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "sayHello");
    private final static QName _ScanOrderByDriverResponse_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "scanOrderByDriverResponse");
    private final static QName _UpdateData_QNAME = new QName("http://impl.service.hbaseWebService.dingjiacheng.xyz/", "updateData");
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
     * Create an instance of {@link UpdateData }
     * 
     */
    public UpdateData createUpdateData() {
        return new UpdateData();
    }

    /**
     * Create an instance of {@link ScanPositionByOrderResponse }
     * 
     */
    public ScanPositionByOrderResponse createScanPositionByOrderResponse() {
        return new ScanPositionByOrderResponse();
    }

    /**
     * Create an instance of {@link WriteDataToPosition }
     * 
     */
    public WriteDataToPosition createWriteDataToPosition() {
        return new WriteDataToPosition();
    }

    /**
     * Create an instance of {@link WriteDataToPositionResponse }
     * 
     */
    public WriteDataToPositionResponse createWriteDataToPositionResponse() {
        return new WriteDataToPositionResponse();
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
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link UpdateDataResponse }
     * 
     */
    public UpdateDataResponse createUpdateDataResponse() {
        return new UpdateDataResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteDataToPosition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "writeDataToPosition")
    public JAXBElement<WriteDataToPosition> createWriteDataToPosition(WriteDataToPosition value) {
        return new JAXBElement<WriteDataToPosition>(_WriteDataToPosition_QNAME, WriteDataToPosition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteDataToPositionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "writeDataToPositionResponse")
    public JAXBElement<WriteDataToPositionResponse> createWriteDataToPositionResponse(WriteDataToPositionResponse value) {
        return new JAXBElement<WriteDataToPositionResponse>(_WriteDataToPositionResponse_QNAME, WriteDataToPositionResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "updateDataResponse")
    public JAXBElement<UpdateDataResponse> createUpdateDataResponse(UpdateDataResponse value) {
        return new JAXBElement<UpdateDataResponse>(_UpdateDataResponse_QNAME, UpdateDataResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.hbaseWebService.dingjiacheng.xyz/", name = "updateData")
    public JAXBElement<UpdateData> createUpdateData(UpdateData value) {
        return new JAXBElement<UpdateData>(_UpdateData_QNAME, UpdateData.class, null, value);
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
