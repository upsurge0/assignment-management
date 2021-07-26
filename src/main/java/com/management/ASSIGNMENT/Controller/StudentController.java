package com.management.ASSIGNMENT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Student;
import com.management.ASSIGNMENT.Repository.StudentRepository;
import com.management.ASSIGNMENT.Service.AssignmentService;

@Controller
public class StudentController {
	
	private AssignmentService studentService;

	public StudentController(AssignmentService studentService) {
		super();
		this.studentService = studentService;
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
	
	@PostMapping("/processregister")
	public String registerSubmit(Student person) {
		perRepo.save(person);
		return "login";
	}
	
	@PostMapping("/loginsubmit")
	public String loginSubmit(@RequestParam String email, @RequestParam String password) {
		Student tempPerson = perRepo.findByEmail(email);
		
		if(tempPerson!=null && tempPerson.getPassword().equals(password)) { 
			return "success";
		}
		
		return "error";
	}
	
	//handler method to handle list students and mode and view
	@GetMapping("/assignments")
	public String listAssignments(Model model) {
		model.addAttribute("assignments", studentService.getAllAssignments());
		return "assignments";
	}
	
	@GetMapping("/assignments/new")
	public String createAssignmentForm(Model model) {
		
		// create assignment object to hold assignment form data
		Assignment assignment = new Assignment();
		model.addAttribute("assignment",assignment);
		// return "create_assignment";
		return "createnewassignment";		
	}
	
	@PostMapping("/assignments")
	public String saveAssignment(@ModelAttribute("assignment") Assignment assignment) {
		studentService.saveAssignment(assignment);
		return "redirect:/assignments";
		
	}
	
	@GetMapping("/assignments/edit/{id}")
	public String editAssignmentForm(@PathVariable long id, Model model) {
		model.addAttribute("assignment",studentService.getAssignmentById(id));
		return "edit_assignment";
			
	}
	
	@PostMapping("/assignments/{id}")
	public String updateAssignment(@PathVariable Long id, @ModelAttribute("assignment") Assignment assignment, Model model) {
		//get assignment from database by id
		Assignment existingAssignment = studentService.getAssignmentById(id);
		existingAssignment.setId(id);
		existingAssignment.setTitle(assignment.getTitle());
		existingAssignment.setInstructions(assignment.getInstructions());
		existingAssignment.setCourse(assignment.getCourse());
		existingAssignment.setMarks(assignment.getMarks());
		existingAssignment.setDate(assignment.getDate()); 
		//existingAssignment.setTime(assignment.getTime());
		
		//save updated assignment object
		studentService.updateAssignment(existingAssignment);
		return "redirect:/assignments";
	}
	
	//handler method to handle delete assignment request
	
	@GetMapping("/assignments/{id}")
	public String deleteAssignment(@PathVariable Long id) {
		studentService.deleteAssignmentById(id);
		return "redirect:/assignments";
		
	}

}
