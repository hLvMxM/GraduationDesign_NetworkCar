package test;

import HbaseUtil.HbaseUtil;
import Properties.PM;
import thermodynamiccount.Count;

public class Thecounttest {
	public static void main(String[] args) {
		PM p = new PM(args[0]);
		HbaseUtil.initHbaseUtil();
		Count.countThe(1475349582L);
	}
}
