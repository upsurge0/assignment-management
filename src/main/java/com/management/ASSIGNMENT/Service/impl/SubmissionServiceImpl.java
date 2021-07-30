package com.management.ASSIGNMENT.Service.impl;

import java.util.ArrayList;
import java.util.List;

import com.management.ASSIGNMENT.Entity.Doc;
import com.management.ASSIGNMENT.Entity.Submission;
import com.management.ASSIGNMENT.Repository.SubmissionRepository;
import com.management.ASSIGNMENT.Service.SubmissionService;

import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl implements SubmissionService{
    
    private SubmissionRepository submissionRepository;

	public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
		super();
		this.submissionRepository = submissionRepository;
	}

    @Override
    public Submission saveSubmission(Submission submission){
        return submissionRepository.save(submission);
    }
    @Override
    public Submission getSubmissionById(long id){
        return submissionRepository.findById(id).get();
    }
    // @Override
    // public Doc findDocById(long id){
    //     return submissionRepository.findDocById(id);
    // }

    @Override
    public List<Submission> findSubmissionByAssignmentId(long id){
        List<Submission> submissions = new ArrayList<>();
        submissionRepository.findByAssignmentId(id)
        .forEach(submissions :: add);
        return submissions;
    }
}
