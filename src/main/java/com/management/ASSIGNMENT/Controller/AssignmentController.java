package com.management.ASSIGNMENT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Course;
import com.management.ASSIGNMENT.Entity.Doc;
import com.management.ASSIGNMENT.Entity.Submission;
import com.management.ASSIGNMENT.Service.AssignmentService;
import com.management.ASSIGNMENT.Service.CourseService;
import com.management.ASSIGNMENT.Service.DocStorageService;
import com.management.ASSIGNMENT.Service.StudentService;
import com.management.ASSIGNMENT.Service.SubmissionService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class AssignmentController {

    private AssignmentService assignmentService;
	private CourseService courseService;
	private SubmissionService submissionService;
	private DocStorageService docStorageService;
	private StudentService studentService;

	public AssignmentController(AssignmentService assignmentService, CourseService courseService,
	 SubmissionService submissionService, DocStorageService docStorageService, StudentService studentService) {
		super();
		this.assignmentService = assignmentService;
		this.courseService = courseService;
		this.submissionService = submissionService;
		this.docStorageService = docStorageService;
		this.studentService = studentService;
	}

	public List<String> getSessionAttributes(HttpSession session){
		@SuppressWarnings("unchecked")
		List<String> user = (List<String>) session.getAttribute("USER_SESSION");
		return user!=null? user:new ArrayList<>();
	}

	@GetMapping("/view-submissions/{assignmentId}")
	public String listSubmissions(Model model, @PathVariable long assignmentId, HttpSession session){
		List<Submission> submission = submissionService.findSubmissionByAssignmentId(assignmentId);
		
		List<Doc> docs = new ArrayList<>();
		for(Submission submissions:submission){
			docs.add(docStorageService.getBySubmissionId(submissions.getId()));
		}

		List<String> names = new ArrayList<>();
		for(Submission submissions:submission){
			names.add(studentService.findById(submissions.getStudentId()).getName());
		}

		model.addAttribute("submissions", submission);
		model.addAttribute("docs", docs);
		model.addAttribute("names", names);
        model.addAttribute("user", getSessionAttributes(session));
		return "view-submissions";
	}

    @GetMapping("/assignments-student")
    public String listStudentAssignments(Model model, HttpSession session){
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        model.addAttribute("user", getSessionAttributes(session));
        return "assignments-student";
    }

    @GetMapping("/submit-page/{id}")
    public String studentSubmitForm(@PathVariable long id, Model model, HttpSession session){
        Assignment assignment = assignmentService.getAssignmentById(id);
		Doc doc = docStorageService.getByAssignmentId(id);
		
		model.addAttribute("assignments", assignment);
        model.addAttribute("submission", new Submission());
        model.addAttribute("docs", doc);
        model.addAttribute("user", getSessionAttributes(session));
        return "submit-assignment";
    }

    @PostMapping("/submit-assignment/{assignmentId}")
    public String studentSubmit(@PathVariable long assignmentId,
	@ModelAttribute("assignment") Assignment assignment,
	@ModelAttribute("submission") Submission submission,
	@RequestParam("files") MultipartFile[] files,
	HttpSession session){
        Assignment existingAssignment = assignmentService.getAssignmentById(assignmentId);
        List<String> user = getSessionAttributes(session);
		submission.setMarks(10);
		submission.setStudentId(new Long(user.get(1)));
		submission.setSubTime(new Date());
		submission.submit(existingAssignment);
		Doc doc = new Doc();
		for(MultipartFile file : files)
		{
			doc = docStorageService.saveFile(file);
		}
		doc.setSubmission(submission);
		submission.setDoc(doc);
		submissionService.saveSubmission(submission);
        return "redirect:/assignments-student";
    }

	@GetMapping("/assignments/edit/{id}")
	public String editAssignmentForm(@PathVariable long id, Model model, HttpSession session) {
		model.addAttribute("assignment",assignmentService.getAssignmentById(id));
		model.addAttribute("course", courseService.getAllCourses());
        model.addAttribute("user", getSessionAttributes(session));
		return "edit_assignment";
			
	}
	
	@PostMapping("/assignments/{id}")
	public String updateAssignment(@PathVariable Long id, @ModelAttribute("assignment") Assignment assignment,
	@RequestParam("files") MultipartFile[] files, Model model) throws ParseException {
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

		//TODO: Add edit function for file.
		// Doc doc = new Doc();
		// for(MultipartFile file : files)
		// {
		// 	doc = docStorageService.saveFile(file);
		// }
		// doc.setAssignment(assignment);
		// existingAssignment.setDoc(doc);
		//existingAssignment.setTime(assignment.getTime());
		
		//save updated assignment object
		assignmentService.updateAssignment(existingAssignment);
		return "redirect:/assignments";
	}

    @GetMapping("/assignments")
	public String listAssignments(Model model, HttpSession session) {
		List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("user", getSessionAttributes(session));
		model.addAttribute("assignments", assignments);
		return "assignments";
	}
	
	@PostMapping("/assignments")
	public String saveAssignment(@ModelAttribute("assignment") Assignment assignment, 
	@RequestParam("files") MultipartFile[] files) throws ParseException {
		assignment.convertTime();
		assignmentService.saveAssignment(assignment);
		Doc doc = new Doc();
		for(MultipartFile file : files)
		{
			doc = docStorageService.saveFile(file);
		}
		doc.setAssignment(assignment);
		assignment.setDoc(doc);
		assignmentService.saveAssignment(assignment);
		return "redirect:/assignments";
	}

	@GetMapping("/assignments/new")
	public String createAssignmentForm(Model model,	HttpSession session) {
		
		// create assignment object to hold assignment form data
		Assignment assignment = new Assignment();
		List<Course> courses = courseService.getAllCourses();
		model.addAttribute("assignment",assignment);
		model.addAttribute("course",courses);
        model.addAttribute("user", getSessionAttributes(session));
		// assignment.convertTime();
		
		return "create_assignment";		
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
