package com.management.ASSIGNMENT.Service;

import java.util.List;

import com.management.ASSIGNMENT.Entity.Doc;
import com.management.ASSIGNMENT.Entity.Submission;

public interface SubmissionService {
    Submission saveSubmission(Submission submission);
    Submission getSubmissionById(long id);
    List<Submission> findSubmissionByAssignmentId(long id);
}
