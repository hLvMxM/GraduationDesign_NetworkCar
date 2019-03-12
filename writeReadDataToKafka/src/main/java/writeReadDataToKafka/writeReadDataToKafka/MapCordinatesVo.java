package writeReadDataToKafka.writeReadDataToKafka;

import java.math.BigDecimal;

public class MapCordinatesVo  {

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 经度
     */
    private BigDecimal lon;

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public MapCordinatesVo() {
		
	}
	
	public MapCordinatesVo(BigDecimal lat, BigDecimal lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}
	
	public MapCordinatesVo(double lat,double lon) {
		this(new BigDecimal(lat),new BigDecimal(lon));
	}
	
	@Override
	public String toString() {
		return "[" + lat + ":" + lon+ "]";
	}
    
}
