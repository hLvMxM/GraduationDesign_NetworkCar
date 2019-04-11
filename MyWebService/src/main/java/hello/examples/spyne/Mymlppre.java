
package hello.examples.spyne;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mymlppre complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mymlppre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="day1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="day2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="last1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="israin" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="positiontype" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mymlppre", propOrder = {
    "day1",
    "day2",
    "last1",
    "israin",
    "time",
    "positiontype"
})
public class Mymlppre {

    @XmlElementRef(name = "day1", namespace = "spyne.examples.hello", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> day1;
    @XmlElementRef(name = "day2", namespace = "spyne.examples.hello", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> day2;
    @XmlElementRef(name = "last1", namespace = "spyne.examples.hello", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> last1;
    @XmlElementRef(name = "israin", namespace = "spyne.examples.hello", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> israin;
    @XmlElementRef(name = "time", namespace = "spyne.examples.hello", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> time;
    @XmlElementRef(name = "positiontype", namespace = "spyne.examples.hello", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> positiontype;

    /**
     * 获取day1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDay1() {
        return day1;
    }

    /**
     * 设置day1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDay1(JAXBElement<Double> value) {
        this.day1 = value;
    }

    /**
     * 获取day2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDay2() {
        return day2;
    }

    /**
     * 设置day2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDay2(JAXBElement<Double> value) {
        this.day2 = value;
    }

    /**
     * 获取last1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getLast1() {
        return last1;
    }

    /**
     * 设置last1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setLast1(JAXBElement<Double> value) {
        this.last1 = value;
    }

    /**
     * 获取israin属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getIsrain() {
        return israin;
    }

    /**
     * 设置israin属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setIsrain(JAXBElement<Double> value) {
        this.israin = value;
    }

    /**
     * 获取time属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getTime() {
        return time;
    }

    /**
     * 设置time属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setTime(JAXBElement<Double> value) {
        this.time = value;
    }

    /**
     * 获取positiontype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getPositiontype() {
        return positiontype;
    }

    /**
     * 设置positiontype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setPositiontype(JAXBElement<Double> value) {
        this.positiontype = value;
    }

}
