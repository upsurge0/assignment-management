package com.management.ASSIGNMENT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.management.ASSIGNMENT.Entity.Student;
import com.management.ASSIGNMENT.Entity.Teacher;
import com.management.ASSIGNMENT.Repository.StudentRepository;
import com.management.ASSIGNMENT.Repository.TeacherRepository;


@Controller
public class UserController {
	
	
	
	//Handle Login/Register
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	TeacherRepository perTeacher;
	
	@PostMapping("/processregister")
	public String registerSubmit(Student student) {
		Student tempPerson = studentRepo.findByEmail(student.getEmail());
		if(tempPerson == null){
			studentRepo.save(student);
			return "redirect:/login/student?create=success";
		}
		return "redirect:/register/student?error=exists";
	}
	
	@PostMapping("/processregisterTeacher")
	public String registerTeacher(Teacher teacher) {
		Teacher tempPerson = perTeacher.findByEmail(teacher.getEmail());
		if(tempPerson == null){
			perTeacher.save(teacher);
			return "redirect:/login/teacher?create=success";
		}
		return "redirect:/register/teacher?error=exists";
	}
	@PostMapping("/loginsubmit")
	public String loginSubmit(@RequestParam String email, @RequestParam String password,  HttpServletRequest request) {
		Student tempPerson = studentRepo.findByEmail(email);
		
//        return "redirect:/login";
		if(tempPerson!=null && tempPerson.getPassword().equals(password)) { 
			@SuppressWarnings("unchecked")
			List<String> users = (List<String>) request.getSession().getAttribute("USER_SESSION");
	        //check if notes is present in session or not
	        if (users == null) {
	            users = new ArrayList<>();
	            // if notes object is not present in session, set notes in the request session
	            request.getSession().setAttribute("USER_SESSION", users);
	        }
	        if (users.size() > 0) {
	        	users.set(0, tempPerson.getName());
	        	users.set(1, String.valueOf(tempPerson.getId()));
	            // String user =users.get(0);
	            // System.out.println(user);
	        }
	        else {
	        	users.add(tempPerson.getName());
	        	users.add(String.valueOf(tempPerson.getId()));
	        }
	        
	        request.getSession().setAttribute("USER_SESSION", users);
			return "redirect:/assignments-student";
		}
		
		return "redirect:/login/student?error=bad";
		
	}
	
	@PostMapping("/loginsubmitTeacher")
	public String loginSubmitTeacher(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
//		System.out.println(email);
		Teacher tempPerson = perTeacher.findByEmail(email);
//		System.out.println(tempPerson);
		if(tempPerson!=null && tempPerson.getPassword().equals(password)) { 
			@SuppressWarnings("unchecked")
			List<String> users = (List<String>) request.getSession().getAttribute("USER_SESSION");
	        //check if notes is present in session or not
	        if (users == null) {
	            users = new ArrayList<>();
	            // if notes object is not present in session, set notes in the request session
	            request.getSession().setAttribute("USER_SESSION", users);
	        }
	        if (users.size() > 0) {
	        	users.set(0, tempPerson.getName());
	        	users.set(1, String.valueOf(tempPerson.getId()));
	            // String user =users.get(0);
	            // System.out.println(user);
	        }
	        else {
	        	users.add(tempPerson.getName());
	        	users.add(String.valueOf(tempPerson.getId()));
	        }
	        
	        request.getSession().setAttribute("USER_SESSION", users);
			return "redirect:/assignments";
		}
		
		return "redirect:/login/teacher?error=bad";
	}
	
	 @GetMapping("/logout")
	    public String destroySession(HttpServletRequest request) {
	        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
	        request.getSession().invalidate();
	        return "redirect:/";
	    }
	//handler method to handle list students and mode and view

}
