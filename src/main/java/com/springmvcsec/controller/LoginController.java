package com.springmvcsec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springmvcsec.DAO.SignupDAO;
import com.springmvcsec.DTO.SignupDTO;

@Controller
public class LoginController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	SignupDAO singupDAO;

	@GetMapping("/myCustomLogin")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupdto") SignupDTO signupDTO) {
		return "signup";
	}

	@PostMapping("/singup-processing")
	public String signupProcessing(SignupDTO singupDTO) {
		System.out.println("before" + singupDTO.toString());

		String encodedPassword = passwordEncoder.encode(singupDTO.getPassword());
		singupDTO.setPassword(encodedPassword);

		System.out.println("after" + singupDTO.toString());
		
		UserDetails user = User.withUsername(singupDTO.getUsername()).password(encodedPassword).authorities("Coder").build();

		jdbcUserDetailsManager.createUser(user);
		//singupDAO.saveUser(singupDTO);
		return "redirect:/myCustomLogin";
	}
}
