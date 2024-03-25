package com.springmvcsec.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@GetMapping("/homepage")
	public String helloWorld(Principal principal, Authentication auth, Model model) {
		String userName=principal.getName();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		model.addAttribute("userName", userName);
		model.addAttribute("authorities",authorities);
		return "home-page";
		
	}
	
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "hello ji";
	}
	
	@ResponseBody
	@GetMapping("/bye")
	public String bye() {
		return "bye bye ji";
	}
	
	@GetMapping("/trainer")
	public String trainer() {
		return "trainer";
	}
	
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userName") String userName) {
		
		jdbcUserDetailsManager.deleteUser(userName);
		System.out.println("user deleted "+userName);
		return "redirect:/myCustomLogin";
	}
	
	@GetMapping("/coder")
	public String coder() {
		return "coder";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}

}
