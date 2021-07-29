package com.example.loginregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginregister.model.Doc;

public interface DocRepository extends JpaRepository<Doc , Integer> {
	

}
