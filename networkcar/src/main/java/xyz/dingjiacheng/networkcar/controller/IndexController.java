package xyz.dingjiacheng.networkcar.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import xyz.dingjiacheng.networkcar.model.User;
import xyz.dingjiacheng.networkcar.service.UserService;

@Controller
public class IndexController {
	
	private String getAuth(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			return grantedAuthority.getAuthority();
		}
		return "driver";
	}
	
	@GetMapping("/")
	public String indexnull() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String index(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "index";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest httpServletRequest,ModelMap map) {
		String queryString = httpServletRequest.getQueryString();
		if("error".equals(queryString)) {
			map.put("error", true);
		}
		else {
			map.put("error", false);
		}
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/map")
	public String map(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "map";
	}
	
	@GetMapping("/orderhis")
	public String orderhis(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "orderhis";
	}
	
	@GetMapping("/thermodynamic")
	public String thermodynamic(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "thermodynamic";
	}
	
	@GetMapping("/count")
	public String count(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "count";
	}
	
	@GetMapping("/predict")
	public String predict(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "predict";
	}
	@GetMapping("/dispatch")
	public String dispatch(Authentication authentication,ModelMap map) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "dispatch";
	}
	
	@GetMapping("/setting")
	public String setting(Authentication authentication,ModelMap map) {
		List<User> allUser = (new UserService()).loadAllUser();
		map.addAttribute("userlist",allUser);
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		return "setting";
	}
}
