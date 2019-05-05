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
import org.springframework.web.bind.annotation.RequestParam;

import io.netty.util.internal.StringUtil;
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
	public String setting(Authentication authentication,ModelMap map,@RequestParam(name = "page", required = false) Integer page,@RequestParam(name = "username", required = false) String username) {
		map.put("name", authentication.getName());
		map.put("auth", getAuth(authentication));
		if ("admin".equals(getAuth(authentication))) {
			List<User> allUser = null;
			int length = 0;
			if(!StringUtil.isNullOrEmpty(username)) {
				allUser = (new UserService()).loadAllUserByUsername(username);
				length = allUser.size();
			}else if (page==null) {
				allUser = (new UserService()).loadAllUserByPage(1);
				length =  (new UserService()).countalluser();
			}else {
				if (page<=1) {
					page = 1;
				}
				allUser = (new UserService()).loadAllUserByPage(page);
				length =  (new UserService()).countalluser();
			}
			map.addAttribute("userlist",allUser);
			StringBuffer sb = new StringBuffer("");
			for (User user : allUser) {
				sb.append(user.toString()+"\n");
			}
			map.put("userinfo", sb.toString());
			map.put("length",length);
			if(page==null) {
				page = 1;
			}
			if(page>length) page = length;
			map.put("page",page);
			return "setting";
		}else {
			return "setting_user";
		}
	}
}
