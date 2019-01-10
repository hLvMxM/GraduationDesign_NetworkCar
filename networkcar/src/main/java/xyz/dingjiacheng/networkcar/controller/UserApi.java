package xyz.dingjiacheng.networkcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
	
	@PostMapping("/login")
	public String PostLogin() {
		
		return "True";
	}
}
