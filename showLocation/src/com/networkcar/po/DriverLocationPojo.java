package com.networkcar.po;

public class DriverLocationPojo {
	private String id;
	private String orderid;
	private double longitude;
	private double latitude;
	private Long time;
	public DriverLocationPojo() {
		super();
	}
	public DriverLocationPojo(String id, String orderid, Long time, double longitude, double latitude) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String toString() {
		return "[Userid:"+id+",Orderid"+orderid+",time"+time+",longitude"+longitude+",latitude"+latitude+"]";
	}
	
}
