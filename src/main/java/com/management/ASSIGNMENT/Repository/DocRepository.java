package com.management.ASSIGNMENT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ASSIGNMENT.Entity.Doc;

public interface DocRepository extends JpaRepository<Doc , Integer> {
    Doc findBySubmissionId(long id);
    Doc findByAssignmentId(long id);
}
