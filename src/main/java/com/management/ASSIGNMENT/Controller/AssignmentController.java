package com.management.ASSIGNMENT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Course;
import com.management.ASSIGNMENT.Entity.Submission;
import com.management.ASSIGNMENT.Service.AssignmentService;
import com.management.ASSIGNMENT.Service.CourseService;
import com.management.ASSIGNMENT.Service.DocStorageService;
import com.management.ASSIGNMENT.Service.SubmissionService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AssignmentController {

    private AssignmentService assignmentService;
	private CourseService courseService;
	private SubmissionService submissionService;
	private DocStorageService docStorageService;

	public AssignmentController(AssignmentService assignmentService, CourseService courseService,
	 SubmissionService submissionService, DocStorageService docStorageService) {
		super();
		this.assignmentService = assignmentService;
		this.courseService = courseService;
		this.submissionService = submissionService;
		this.docStorageService = docStorageService;
	}

	public List<String> getSessionAttributes(HttpSession session){
		@SuppressWarnings("unchecked")
		List<String> user = (List<String>) session.getAttribute("USER_SESSION");
		return user!=null? user:new ArrayList<>();
	}

    @GetMapping("/assignments-student")
    public String listStudentAssignments(Model model, HttpSession session){
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        model.addAttribute("user", getSessionAttributes(session));
        return "assignments-student";
    }

    @GetMapping("/submit-page/{id}")
    public String studentSubmitForm(@PathVariable long id, Model model, HttpSession session){
        model.addAttribute("assignments", assignmentService.getAssignmentById(id));
        model.addAttribute("submission", new Submission());
        model.addAttribute("user", getSessionAttributes(session));
        return "submit-assignment";
    }

    @PostMapping("/submit-assignment/{assignmentId}")
    public String studentSubmit(@PathVariable long assignmentId,
	@ModelAttribute("assignment") Assignment assignment,
	@ModelAttribute("submission") Submission submission, HttpSession session){
        Assignment existingAssignment = assignmentService.getAssignmentById(assignmentId);
        List<String> user = getSessionAttributes(session);
		submission.setMarks(10);
		submission.setStudentId(new Long(user.get(1)));
		submission.setSubTime(new Date());
		submission.submit(existingAssignment);
		submissionService.saveSubmission(submission);
        return "redirect:/assignments-student";
    }

    @GetMapping("/assignments")
	public String listAssignments(Model model, HttpSession session) {
		List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("user", getSessionAttributes(session));
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
	public String createAssignmentForm(Model model, HttpSession session) {
		
		// create assignment object to hold assignment form data
		Assignment assignment = new Assignment();
		List<Course> courses = courseService.getAllCourses();
		model.addAttribute("assignment",assignment);
		model.addAttribute("course",courses);
        model.addAttribute("user", getSessionAttributes(session));
		// assignment.convertTime();
		
		return "create_assignment";		
	}
	
	@GetMapping("/assignments/edit/{id}")
	public String editAssignmentForm(@PathVariable long id, Model model, HttpSession session) {
		model.addAttribute("assignment",assignmentService.getAssignmentById(id));
		model.addAttribute("course", courseService.getAllCourses());
        model.addAttribute("user", getSessionAttributes(session));
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
