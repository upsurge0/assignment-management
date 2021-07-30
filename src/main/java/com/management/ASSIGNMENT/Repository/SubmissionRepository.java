package com.management.ASSIGNMENT.Repository;

import com.management.ASSIGNMENT.Entity.Doc;
import com.management.ASSIGNMENT.Entity.Submission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
    // Doc findDocBySubmissionId(long id);
}
