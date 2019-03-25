
package xyz.dingjiacheng.networkcar.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebServiceImpl", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebServiceImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "scanOrderByDriver", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanOrderByDriver")
    @ResponseWrapper(localName = "scanOrderByDriverResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanOrderByDriverResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanOrderByDriverRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanOrderByDriverResponse")
    public String scanOrderByDriver(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "scanIndexByOrder", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanIndexByOrder")
    @ResponseWrapper(localName = "scanIndexByOrderResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanIndexByOrderResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanIndexByOrderRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanIndexByOrderResponse")
    public String scanIndexByOrder(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "scanPositionByOrder", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanPositionByOrder")
    @ResponseWrapper(localName = "scanPositionByOrderResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanPositionByOrderResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanPositionByOrderRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanPositionByOrderResponse")
    public String scanPositionByOrder(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "scanthermodynamic", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.Scanthermodynamic")
    @ResponseWrapper(localName = "scanthermodynamicResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScanthermodynamicResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanthermodynamicRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scanthermodynamicResponse")
    public String scanthermodynamic(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Long arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "scancount", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.Scancount")
    @ResponseWrapper(localName = "scancountResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.ScancountResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scancountRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/scancountResponse")
    public String scancount(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Long arg1);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getdoingcount", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.Getdoingcount")
    @ResponseWrapper(localName = "getdoingcountResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.GetdoingcountResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/getdoingcountRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/getdoingcountResponse")
    public String getdoingcount();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPosition", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.GetPosition")
    @ResponseWrapper(localName = "getPositionResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.GetPositionResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/getPositionRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/getPositionResponse")
    public String getPosition();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getnowTime", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.GetnowTime")
    @ResponseWrapper(localName = "getnowTimeResponse", targetNamespace = "http://webService.networkcar.dingjiacheng.xyz/", className = "xyz.dingjiacheng.networkcar.webservice.GetnowTimeResponse")
    @Action(input = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/getnowTimeRequest", output = "http://webService.networkcar.dingjiacheng.xyz/WebServiceImpl/getnowTimeResponse")
    public String getnowTime();

}
