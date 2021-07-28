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
import java.util.List;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Course;
import com.management.ASSIGNMENT.Entity.Student;
import com.management.ASSIGNMENT.Entity.Teacher;
import com.management.ASSIGNMENT.Repository.StudentRepository;
import com.management.ASSIGNMENT.Repository.TeacherRepository;
import com.management.ASSIGNMENT.Service.AssignmentService;
import com.management.ASSIGNMENT.Service.CourseService;

@Controller
public class StudentController {
	
	private AssignmentService assignmentService;
	private CourseService courseService;

	public StudentController(AssignmentService assignmentService, CourseService courseService) {
		super();
		this.assignmentService = assignmentService;
		this.courseService = courseService;
	}
	
	//Handle Login/Register
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	TeacherRepository perTeacher;
	
	@PostMapping("/processregister")
	public String registerSubmit(Student student) {
		studentRepo.save(student);
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
		Student tempPerson = studentRepo.findByEmail(email);
		
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
		return "assignments";
	}
	
	@PostMapping("/assignments")
	public String saveAssignment(@ModelAttribute("assignment") Assignment assignment) throws ParseException {
		assignment.convertTime();
		assignmentService.saveAssignment(assignment);
		return "redirect:/assignments";
		
	}

	@GetMapping("/assignments/new")
	public String createAssignmentForm(Model model) {
		
		// create assignment object to hold assignment form data
		Assignment assignment = new Assignment();
		List<Course> courses = courseService.getAllCourses();
		model.addAttribute("assignment",assignment);
		model.addAttribute("course",courses);
		// assignment.convertTime();
		
		return "create_assignment";		
	}
	
	@GetMapping("/assignments/edit/{id}")
	public String editAssignmentForm(@PathVariable long id, Model model) {
		model.addAttribute("assignment",assignmentService.getAssignmentById(id));
		model.addAttribute("course", courseService.getAllCourses());
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
		existingAssignment.setStime(assignment.getStime()); 
		existingAssignment.convertTime();
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

	@GetMapping("/courses")
	public String getCourses(){
		return "manage-course";
	}

}
