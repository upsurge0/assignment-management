package com.management.ASSIGNMENT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Student;
import com.management.ASSIGNMENT.Entity.Teacher;
import com.management.ASSIGNMENT.Repository.StudentRepository;
import com.management.ASSIGNMENT.Repository.TeacherRepository;
import com.management.ASSIGNMENT.Service.AssignmentService;

@Controller
public class StudentController {
	
	private AssignmentService assignmentService;

	public StudentController(AssignmentService assignmentService) {
		super();
		this.assignmentService = assignmentService;
	}
	
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
	
	//Handle Login/Register
	@Autowired
	StudentRepository perRepo;
	
	@Autowired
	TeacherRepository perTeacher;
	
	@PostMapping("/processregister")
	public String registerSubmit(Student person) {
		perRepo.save(person);
		return "login/student/student-login";
	}
	
	@PostMapping("/processregisterTeacher")
	public String registerTeacher(Teacher teacher) {
		System.out.println(teacher.getPassword());
		perTeacher.save(teacher);
		return "login/teacher/teacher-login";
	}
	@PostMapping("/loginsubmit")
	public String loginSubmit(@RequestParam String email, @RequestParam String password) {
		Student tempPerson = perRepo.findByEmail(email);
		
		if(tempPerson!=null && tempPerson.getPassword().equals(password)) { 
			return "redirect:/assignments";
		}
		
		return "error";
	}
	
	@PostMapping("/loginsubmitTeacher")
	public String loginSubmitTeacher(@RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println(email);
		Teacher tempPerson = perTeacher.findByEmail(email);
		System.out.println(tempPerson);
		if(tempPerson!=null && tempPerson.getPassword().equals(password)) { 
			return "redirect:/assignments";
		}
		
		return "error";
	}
	//handler method to handle list students and mode and view
	@GetMapping("/assignments")
	public String listAssignments(Model model) {
		List<Assignment> assignments = assignmentService.getAllAssignments();
		model.addAttribute("assignments", assignments);
// 		List<Date> dates = new ArrayList<Date>();
// 		for(Assignment assignment: assignments)
// 		{
// 			dates.add(assignment.getDate());
// 		}
// //		System.out.println(assignment.getDate());
// 		System.out.println(dates);
// 		model.addAttribute("date", dates);
		return "assignments";
	}
	
	@GetMapping("/assignments/new")
	public String createAssignmentForm(Model model) {
		
		// create assignment object to hold assignment form data
		Assignment assignment = new Assignment();
		model.addAttribute("assignment",assignment);
		return "create_assignment";		
	}
	
	@PostMapping("/assignments")
	public String saveAssignment(@ModelAttribute("assignment") Assignment assignment) {
		assignmentService.saveAssignment(assignment);
		return "redirect:/assignments";
		
	}
	
	@GetMapping("/assignments/edit/{id}")
	public String editAssignmentForm(@PathVariable long id, Model model) {
		model.addAttribute("assignment",assignmentService.getAssignmentById(id));
//		model.addAttribute("date", assignmentService.get);
		return "editnewassignment";
			
	}
	
	@PostMapping("/assignments/{id}")
	public String updateAssignment(@PathVariable Long id, @ModelAttribute("assignment") Assignment assignment, Model model) throws ParseException {
		//get assignment from database by id
		Assignment existingAssignment = assignmentService.getAssignmentById(id);
		existingAssignment.setId(id);
		existingAssignment.setTitle(assignment.getTitle());
		existingAssignment.setInstructions(assignment.getInstructions());
		existingAssignment.setCourse(assignment.getCourse());
		existingAssignment.setMarks(assignment.getMarks());
		// System.out.println(assignment.getDate());
		existingAssignment.setSdate(assignment.getSdate()); 
		//existingAssignment.setTime(assignment.getTime());
		
		//save updated assignment object
		assignmentService.updateAssignment(existingAssignment);
		return "redirect:/assignments";
	}
	
	//handler method to handle delete assignment request
	
	@GetMapping("/assignments/{id}")
	public String deleteAssignment(@PathVariable Long id) {
		assignmentService.deleteAssignmentById(id);
		return "redirect:/assignments";
		
	}

}
