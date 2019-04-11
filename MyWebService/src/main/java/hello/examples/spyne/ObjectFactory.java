
package hello.examples.spyne;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hello.examples.spyne package. 
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

    private final static QName _Mymlppre_QNAME = new QName("spyne.examples.hello", "mymlppre");
    private final static QName _Myknnpre_QNAME = new QName("spyne.examples.hello", "myknnpre");
    private final static QName _MymlppreResponse_QNAME = new QName("spyne.examples.hello", "mymlppreResponse");
    private final static QName _MyknnpreResponse_QNAME = new QName("spyne.examples.hello", "myknnpreResponse");
    private final static QName _MymlpprePositiontype_QNAME = new QName("spyne.examples.hello", "positiontype");
    private final static QName _MymlppreLast1_QNAME = new QName("spyne.examples.hello", "last1");
    private final static QName _MymlppreDay1_QNAME = new QName("spyne.examples.hello", "day1");
    private final static QName _MymlppreTime_QNAME = new QName("spyne.examples.hello", "time");
    private final static QName _MymlppreDay2_QNAME = new QName("spyne.examples.hello", "day2");
    private final static QName _MymlppreIsrain_QNAME = new QName("spyne.examples.hello", "israin");
    private final static QName _MyknnpreLat_QNAME = new QName("spyne.examples.hello", "lat");
    private final static QName _MyknnpreLon_QNAME = new QName("spyne.examples.hello", "lon");
    private final static QName _MymlppreResponseMymlppreResult_QNAME = new QName("spyne.examples.hello", "mymlppreResult");
    private final static QName _MyknnpreResponseMyknnpreResult_QNAME = new QName("spyne.examples.hello", "myknnpreResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hello.examples.spyne
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Mymlppre }
     * 
     */
    public Mymlppre createMymlppre() {
        return new Mymlppre();
    }

    /**
     * Create an instance of {@link MyknnpreResponse }
     * 
     */
    public MyknnpreResponse createMyknnpreResponse() {
        return new MyknnpreResponse();
    }

    /**
     * Create an instance of {@link MymlppreResponse }
     * 
     */
    public MymlppreResponse createMymlppreResponse() {
        return new MymlppreResponse();
    }

    /**
     * Create an instance of {@link Myknnpre }
     * 
     */
    public Myknnpre createMyknnpre() {
        return new Myknnpre();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mymlppre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "mymlppre")
    public JAXBElement<Mymlppre> createMymlppre(Mymlppre value) {
        return new JAXBElement<Mymlppre>(_Mymlppre_QNAME, Mymlppre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Myknnpre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "myknnpre")
    public JAXBElement<Myknnpre> createMyknnpre(Myknnpre value) {
        return new JAXBElement<Myknnpre>(_Myknnpre_QNAME, Myknnpre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MymlppreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "mymlppreResponse")
    public JAXBElement<MymlppreResponse> createMymlppreResponse(MymlppreResponse value) {
        return new JAXBElement<MymlppreResponse>(_MymlppreResponse_QNAME, MymlppreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MyknnpreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "myknnpreResponse")
    public JAXBElement<MyknnpreResponse> createMyknnpreResponse(MyknnpreResponse value) {
        return new JAXBElement<MyknnpreResponse>(_MyknnpreResponse_QNAME, MyknnpreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "positiontype", scope = Mymlppre.class)
    public JAXBElement<Double> createMymlpprePositiontype(Double value) {
        return new JAXBElement<Double>(_MymlpprePositiontype_QNAME, Double.class, Mymlppre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "last1", scope = Mymlppre.class)
    public JAXBElement<Double> createMymlppreLast1(Double value) {
        return new JAXBElement<Double>(_MymlppreLast1_QNAME, Double.class, Mymlppre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "day1", scope = Mymlppre.class)
    public JAXBElement<Double> createMymlppreDay1(Double value) {
        return new JAXBElement<Double>(_MymlppreDay1_QNAME, Double.class, Mymlppre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "time", scope = Mymlppre.class)
    public JAXBElement<Double> createMymlppreTime(Double value) {
        return new JAXBElement<Double>(_MymlppreTime_QNAME, Double.class, Mymlppre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "day2", scope = Mymlppre.class)
    public JAXBElement<Double> createMymlppreDay2(Double value) {
        return new JAXBElement<Double>(_MymlppreDay2_QNAME, Double.class, Mymlppre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "israin", scope = Mymlppre.class)
    public JAXBElement<Double> createMymlppreIsrain(Double value) {
        return new JAXBElement<Double>(_MymlppreIsrain_QNAME, Double.class, Mymlppre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "lat", scope = Myknnpre.class)
    public JAXBElement<Double> createMyknnpreLat(Double value) {
        return new JAXBElement<Double>(_MyknnpreLat_QNAME, Double.class, Myknnpre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "lon", scope = Myknnpre.class)
    public JAXBElement<Double> createMyknnpreLon(Double value) {
        return new JAXBElement<Double>(_MyknnpreLon_QNAME, Double.class, Myknnpre.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "mymlppreResult", scope = MymlppreResponse.class)
    public JAXBElement<Double> createMymlppreResponseMymlppreResult(Double value) {
        return new JAXBElement<Double>(_MymlppreResponseMymlppreResult_QNAME, Double.class, MymlppreResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "spyne.examples.hello", name = "myknnpreResult", scope = MyknnpreResponse.class)
    public JAXBElement<Double> createMyknnpreResponseMyknnpreResult(Double value) {
        return new JAXBElement<Double>(_MyknnpreResponseMyknnpreResult_QNAME, Double.class, MyknnpreResponse.class, value);
    }

}
