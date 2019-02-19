/**
 * WebServiceImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xyz.dingjiacheng.hbaseWebService.service.impl;

public interface WebServiceImpl extends java.rmi.Remote {
    public java.lang.String sayHello(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String scanOrderByDriver(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String scanPositionByOrder(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String updateData(java.lang.String arg0) throws java.rmi.RemoteException;
    public void writeDataToPosition(java.lang.String[] arg0) throws java.rmi.RemoteException, xyz.dingjiacheng.hbaseWebService.service.impl.Exception;
}
