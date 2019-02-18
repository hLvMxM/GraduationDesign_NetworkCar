package xyz.dingjiacheng.networkcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.dingjiacheng.networkcar.service.InfoService;
import xyz.dingjiacheng.networkcar.service.ScanService;
import xyz.dingjiacheng.networkcar.util.CoordinatesConvertUtil;
import xyz.dingjiacheng.networkcar.util.MapCordinatesVo;

@RestController
public class InfoController {
	
	@GetMapping("/api/position")
	public String getPosition() { 
		return InfoService.getPositionString();	
	}
	
	@GetMapping("/api/orderInfo")
	public String getOrderInfo(@RequestParam(name = "id", required = true) String driverID) {
		String order = ScanService.getOrder(driverID);
		if("".equals(order)||order==null||order.length()==0) {
			return "{\"order\":[]}";
		}
		String[] split = order.split("\n");
		StringBuilder stringBuilder = new StringBuilder("");
		for (String string : split) {
			String[] info = string.split(":");
			stringBuilder.append("[\""+info[0]+"\",\""+info[1]+"\",\""+info[2]+"\"],");
		}
		String json = stringBuilder.toString();
		json = "{\"order\":["+json.substring(0,json.length()-1)+"]}";
		return json;
	}
	
	@GetMapping("/api/positionInfo")
	public String getPositionInfo(@RequestParam(name = "id", required = true) String orderID) {
		String position = ScanService.getPosition(orderID);
		if("".equals(position)||position==null||position.length()==0) {
			return "{\"position\":[]}";
		}
		String[] split = position.split("\n");
		StringBuilder stringBuilder = new StringBuilder("");
		for (String string : split) {
			String[] info = string.split(":");
			MapCordinatesVo mcv = CoordinatesConvertUtil.bd_encrypt(Double.valueOf(info[0]), Double.valueOf(info[1]));
			stringBuilder.append("[\""+mcv.getLat()+"\",\""+mcv.getLon()+"\",\""+info[2]+"\"],");
			System.out.println(mcv.getLat()+"|"+mcv.getLon());
		}
		String json = stringBuilder.toString();
		json = "{\"position\":["+json.substring(0,json.length()-1)+"]}";
		return json;
	}
}
