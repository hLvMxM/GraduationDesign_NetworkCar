package xyz.dingjiacheng.hbaseWebService.service.impl;

public class WebServiceImplProxy implements xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImpl {
  private String _endpoint = null;
  private xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImpl webServiceImpl = null;
  
  public WebServiceImplProxy() {
    _initWebServiceImplProxy();
  }
  
  public WebServiceImplProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceImplProxy();
  }
  
  private void _initWebServiceImplProxy() {
    try {
      webServiceImpl = (new xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImplServiceLocator()).getWebServiceImplPort();
      if (webServiceImpl != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceImpl)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceImpl != null)
      ((javax.xml.rpc.Stub)webServiceImpl)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public xyz.dingjiacheng.hbaseWebService.service.impl.WebServiceImpl getWebServiceImpl() {
    if (webServiceImpl == null)
      _initWebServiceImplProxy();
    return webServiceImpl;
  }
  
  public java.lang.String sayHello(java.lang.String arg0) throws java.rmi.RemoteException{
    if (webServiceImpl == null)
      _initWebServiceImplProxy();
    return webServiceImpl.sayHello(arg0);
  }
  
  public java.lang.String scanOrderByDriver(java.lang.String arg0) throws java.rmi.RemoteException{
    if (webServiceImpl == null)
      _initWebServiceImplProxy();
    return webServiceImpl.scanOrderByDriver(arg0);
  }
  
  public java.lang.String scanPositionByOrder(java.lang.String arg0) throws java.rmi.RemoteException{
    if (webServiceImpl == null)
      _initWebServiceImplProxy();
    return webServiceImpl.scanPositionByOrder(arg0);
  }
  
  public java.lang.String updateData(java.lang.String arg0) throws java.rmi.RemoteException{
    if (webServiceImpl == null)
      _initWebServiceImplProxy();
    return webServiceImpl.updateData(arg0);
  }
  
  public void writeDataToPosition(java.lang.String[] arg0) throws java.rmi.RemoteException, xyz.dingjiacheng.hbaseWebService.service.impl.Exception{
    if (webServiceImpl == null)
      _initWebServiceImplProxy();
    webServiceImpl.writeDataToPosition(arg0);
  }
  
  
}