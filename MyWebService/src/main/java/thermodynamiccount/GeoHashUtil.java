package thermodynamiccount;


public class GeoHashUtil {
	private static final double minlat = 100.0;
	private static final double maxlat = 120.0;
	private static final double minlon = 20.0;
	private static final double maxlon = 40.0;
	public static String getHash(Double lat,Double lon,int length) {
		double tminlat = minlat;
		double tmaxlat = maxlat;
		double tminlon = minlon;
		double tmaxlon = maxlon;
		
		int[] array = new int[length];
		double tmp = (tmaxlat+tminlat)/2;
		for (int i = 0; i < length/2; i++) {
			if(lat>=tmp) {
				array[2*i] = 1;
				tminlat = tmp;
				tmp = (tmaxlat+tminlat)/2;
			}else {
				array[2*i] = 0;
				tmaxlat = tmp;
				tmp = (tmaxlat+tminlat)/2;
			}
		}
		tmp = (tmaxlon+tminlon)/2;
		for (int i = 0; i < length/2; i++) {
			if(lon>=tmp) {
				array[2*i+1] = 1;
				tminlon = tmp;
				tmp = (tmaxlon+tminlon)/2;
			}else {
				array[2*i+1] = 0;
				tmaxlon = tmp;
				tmp = (tmaxlon+tminlon)/2;
			}
		}
		StringBuffer sb = new StringBuffer();
		for (int i : array) {
			sb.append(i);
		}
		Long valueOf = Long.valueOf(sb.toString(),2);
		return Long.toString(valueOf, 16);
	}
	
	public static double[] mytohash(String myhash) {
		Long value = Long.valueOf(myhash, 16);
		String binaryString = Long.toBinaryString(value);
		char[] charArray = binaryString.toCharArray();
		double tmaxlat = maxlat;
		double tminlat = minlat;
		double tmaxlon = maxlon;
		double tminlon = minlon;
		double tlat = (tmaxlat+tminlat)/2;
		double tlon = (tmaxlon+tminlon)/2;
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if(i%2==0) {
				if(c=='1') {
					tminlat = tlat;
					tlat = (tmaxlat+tminlat)/2;
				}else {
					tmaxlat = tlat;
					tlat = (tmaxlat+tminlat)/2;
				}
			}else {
				if(c=='1') {
					tminlon = tlon;
					tlon = (tmaxlon+tminlon)/2;
				}else {
					tmaxlon = tlon;
					tlon = (tmaxlon+tminlon)/2;
				}
			}
		}
		return new double[] {tlat,tlon};
	}
	
	public static void main(String[] args) {
		String myhash = getHash(159.01,34.2,24);
		System.out.println(myhash);
		double[] tohash = mytohash(myhash);
		System.out.println(tohash[0]+" "+tohash[1]);
	}
}
