package xyz.dingjiacheng.networkcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.dingjiacheng.networkcar.service.InfoService;

@RestController
public class InfoController {
	
	@GetMapping("/api/position")
	public String getPosition() { 
		return InfoService.getPositionString();	
	}
}
