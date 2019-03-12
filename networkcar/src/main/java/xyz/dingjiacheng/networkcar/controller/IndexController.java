package xyz.dingjiacheng.networkcar.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.dingjiacheng.networkcar.model.User;
import xyz.dingjiacheng.networkcar.service.UserService;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/map")
	public String map() {
		return "map";
	}
	
	@GetMapping("/orderhis")
	public String orderhis() {
		return "orderhis";
	}
	
	@GetMapping("/thermodynamic")
	public String thermodynamic() {
		return "thermodynamic";
	}
	
	@GetMapping("/count")
	public String count() {
		return "count";
	}
	
	@GetMapping("/predict")
	public String predict() {
		return "predict";
	}
	@GetMapping("/dispatch")
	public String dispatch() {
		return "dispatch";
	}
	
	@GetMapping("/setting")
	public String setting(Model map) {
		List<User> allUser = (new UserService()).loadAllUser();
		map.addAttribute("userlist",allUser);
		return "setting";
	}
}
