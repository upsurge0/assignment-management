package com.management.ASSIGNMENT.Service.impl;

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
}
