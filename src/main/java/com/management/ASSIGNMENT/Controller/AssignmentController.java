package com.management.ASSIGNMENT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Course;
import com.management.ASSIGNMENT.Service.AssignmentService;
import com.management.ASSIGNMENT.Service.CourseService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AssignmentController {

    private AssignmentService assignmentService;
	private CourseService courseService;

	public AssignmentController(AssignmentService assignmentService, CourseService courseService) {
		super();
		this.assignmentService = assignmentService;
		this.courseService = courseService;
	}

    @GetMapping("/assignments-student")
    public String listStudentAssignments(Model model, HttpSession session){
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        @SuppressWarnings("unchecked")
		List<String> notes = (List<String>) session.getAttribute("USER_SESSION");
        String user = new String();
        if (notes!=null) {        	
        	user = notes.get(0);
        }
        model.addAttribute("username", user!=null? user:new ArrayList<>());
        return "assignments-student";
    }

    @GetMapping("/submit-page/{id}")
    public String studentSubmitForm(@PathVariable long id, Model model){
        model.addAttribute("assignments", assignmentService.getAssignmentById(id));
        return "submit-assignment";
    }

    @PostMapping("/submit-assignment/{id}")
    public String studentSubmit(@PathVariable long id, @ModelAttribute Assignment assignment){
        Assignment existingAssignment = assignmentService.getAssignmentById(id);
        // existingAssignment.submit();
        return "redirect:/assignments-student";
    }

    @GetMapping("/assignments")
	public String listAssignments(Model model, HttpSession session) {
		List<Assignment> assignments = assignmentService.getAllAssignments();
		@SuppressWarnings("unchecked")
		List<String> notes = (List<String>) session.getAttribute("USER_SESSION");
        String user = new String();
        if (notes!=null) {        	
        	user = notes.get(0);
        }
        model.addAttribute("username", user!=null? user:new ArrayList<>());
//        return "home";
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
		return "edit_assignment";
			
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
