package com.management.ASSIGNMENT.Service;

import com.management.ASSIGNMENT.Entity.Submission;

public interface SubmissionService {
    Submission saveSubmission(Submission submission);
    Submission getSubmissionById(long id);
}
