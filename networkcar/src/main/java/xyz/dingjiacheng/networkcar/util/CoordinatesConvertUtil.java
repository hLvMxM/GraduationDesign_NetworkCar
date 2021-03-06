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
//        if(cordinatesVo.getLat() == null || cordinatesVo.getLon() == null) {
//            return cordinatesVo;
//        }
//        
//        double x = cordinatesVo.getLon().doubleValue(), y = cordinatesVo.getLat().doubleValue();
//
//        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * x_pi);
//
//        double theta = atan2(y, x) + 0.000003 * cos(x * x_pi);
//
//        double bd_lon = z * cos(theta) + 0.0065;
//
//        double bd_lat = z * sin(theta) + 0.006;
//
//        MapCordinatesVo mapCordinatesVo = new MapCordinatesVo();
//        BigDecimal lat = new BigDecimal(bd_lat);
//        BigDecimal lon = new BigDecimal(bd_lon);
//
//        mapCordinatesVo.setLat(lat.setScale(6,   BigDecimal.ROUND_HALF_DOWN));
//        mapCordinatesVo.setLon(lon.setScale(6,   BigDecimal.ROUND_HALF_DOWN));
    	double[] gaoDeToBaidu = gaoDeToBaidu(cordinatesVo.getLat().doubleValue(),cordinatesVo.getLon().doubleValue());
        MapCordinatesVo mapCordinatesVo = new MapCordinatesVo(gaoDeToBaidu[0], gaoDeToBaidu[1]);
    	return mapCordinatesVo;
    }
    
    public static MapCordinatesVo bd_encrypt(double lat,double lon) {
    	return bd_encrypt(new MapCordinatesVo(lat, lon));
    }
    
    public static void main(String[] args) {
		System.out.println(CoordinatesConvertUtil.bd_encrypt(
					new MapCordinatesVo(104.11456,30.66052)
				));
		double[] gaoDeToBaidu = gaoDeToBaidu(104.11456,30.66052);
		System.out.println("["+gaoDeToBaidu[0]+":"+gaoDeToBaidu[1]+"]");
	}
    //[104.113560:30.704262]
    
    private static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
	    double[] bd_lat_lon = new double[2];
	    double PI = StrictMath.PI * 3000.0 / 180.0;
	    double x = gd_lon, y = gd_lat;
	    double z = StrictMath.sqrt(x * x + y * y) + 0.00002 * StrictMath.sin(y * PI);
	    double theta = StrictMath.atan2(y, x) + 0.000003 * StrictMath.cos(x * PI);
	    bd_lat_lon[0] = z * StrictMath.cos(theta) + 0.0065;
	    bd_lat_lon[1] = z * StrictMath.sin(theta) + 0.006;
	    return bd_lat_lon;
	}
    
}

