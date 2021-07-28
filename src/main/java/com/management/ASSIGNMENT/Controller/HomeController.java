package com.management.ASSIGNMENT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
	public String home() {
		return "index";
	}
	
	//Registration
	@GetMapping("/register")
	public String register() {
		return "register/choose-role";
	}
	
	@GetMapping("/register/student")
	public String register_student() {
		return "register/student/student-register";
	}
	
	@GetMapping("/register/teacher")
	public String register_teacher() {
		return "register/teacher/teacher-register";
	}
	
	//Login
	
	@GetMapping("/login/student")
	public String login_student() {
		return "login/student/student-login";
	}
	
	@GetMapping("/login/teacher")
	public String login_teacher() {
		return "login/teacher/teacher-login";
	}
}
