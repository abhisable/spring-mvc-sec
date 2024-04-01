package com.springmvcsec.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvcsec.DTO.PasswordChangeDTO;

@Controller
public class HelloWorldController {

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/homepage")
	public String helloWorld(Principal principal, Authentication auth, Model model) {
		String userName = principal.getName();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		model.addAttribute("userName", userName);
		model.addAttribute("authorities", authorities);
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
		System.out.println("user deleted " + userName);
		return "redirect:/myCustomLogin";
	}

	@GetMapping("/changePassword")
	public String changePassword(@ModelAttribute("password-change") PasswordChangeDTO passwordChnageDTO) {
		// model.addAttribute("password-change", new PasswordChangeDTO());
		return "changepassword";
	}

	
	@PostMapping("/savePassword")
	public String savePassword(PasswordChangeDTO passwordChangeDTO, Principal principal) {

		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(principal.getName());
		boolean matches = passwordEncoder.matches(passwordChangeDTO.getOldPassword(), user.getPassword());
		
		if(!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmPassword())) {
			return "redirect:/changePassword?notMatched";
		}
		if(matches) {
		String encodedNewPassword = passwordEncoder.encode(passwordChangeDTO.getConfirmPassword());
		jdbcUserDetailsManager.changePassword(passwordChangeDTO.getOldPassword(), encodedNewPassword);
		return "redirect:/homepage";
		}
		
		return "redirect:/changePassword?invalidPassword";
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
