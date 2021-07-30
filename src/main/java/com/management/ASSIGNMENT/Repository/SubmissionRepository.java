package com.management.ASSIGNMENT.Repository;

import java.util.List;

import com.management.ASSIGNMENT.Entity.Doc;
import com.management.ASSIGNMENT.Entity.Submission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
    // Doc findDocById(long id);
    List<Submission> findByAssignmentId(long AssignmentId);
}
