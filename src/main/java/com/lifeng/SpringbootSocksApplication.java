package com.lifeng;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class SpringbootSocksApplication {

	/**
	 * 登陆界面
	 */
	@GetMapping("/")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}

	/**
	 * 聊天界面
	 */
	@GetMapping("/index")
	public ModelAndView index(String username, String password, HttpServletRequest request) throws UnknownHostException {
		if (StringUtils.isEmpty(username)) {
			username = "匿名用户";
		}
		ModelAndView mav = new ModelAndView("/chat");
		mav.addObject("username", username);
		mav.addObject("webSocketUrl", "ws://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
		return mav;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootSocksApplication.class, args);
	}

}
