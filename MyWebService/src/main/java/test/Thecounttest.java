package test;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import ch.hsr.geohash.GeoHash;
import thermodynamiccount.Count;

public class Thecounttest {
	public static void main(String[] args) {
		new PM(args[0]);
		HbaseUtil.initHbaseUtil();
		Count.countThe(Long.valueOf(args[1]));
	}
}
