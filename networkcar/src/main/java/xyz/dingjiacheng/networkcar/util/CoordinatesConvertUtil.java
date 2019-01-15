package xyz.dingjiacheng.networkcar.util;

import java.math.BigDecimal;
import static java.lang.StrictMath.*;

public class CoordinatesConvertUtil {
	private  static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	
	/**
     * 高德地图坐标转化为百度坐标
     * @param cordinatesVo
     */
    public static MapCordinatesVo bd_encrypt(MapCordinatesVo cordinatesVo)

    {
        if(cordinatesVo.getLat() == null || cordinatesVo.getLon() == null) {
            return cordinatesVo;
        }
        
        double x = cordinatesVo.getLon().doubleValue(), y = cordinatesVo.getLat().doubleValue();

        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * x_pi);

        double theta = atan2(y, x) + 0.000003 * cos(x * x_pi);

        double bd_lon = z * cos(theta) + 0.0065;

        double bd_lat = z * sin(theta) + 0.006;

        MapCordinatesVo mapCordinatesVo = new MapCordinatesVo();
        BigDecimal lat = new BigDecimal(bd_lat);
        BigDecimal lon = new BigDecimal(bd_lon);

        mapCordinatesVo.setLat(lat.setScale(6,   BigDecimal.ROUND_HALF_DOWN));
        mapCordinatesVo.setLon(lon.setScale(6,   BigDecimal.ROUND_HALF_DOWN));
        return mapCordinatesVo;
    }
    
    public static MapCordinatesVo bd_encrypt(double lat,double lon) {
    	return bd_encrypt(new MapCordinatesVo(lat, lon));
    }
    
    public static void main(String[] args) {
		System.out.println(CoordinatesConvertUtil.bd_encrypt(
					new MapCordinatesVo(104.10753000000001,30.69789)
				));
	}
    
}

