package com.example.loginregister.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.loginregister.model.Doc;
import com.example.loginregister.repository.DocRepository;


@Service
public class DocStorageService {
@Autowired
private DocRepository docRepository;

public Doc saveFile(MultipartFile file)
{
	String docname = file.getOriginalFilename();
	try
	{
		Doc doc = new Doc(docname , file.getContentType() , file.getBytes());
		return docRepository.save(doc);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

public Optional<Doc> getFile(Integer fileId)
{
	return docRepository.findById(fileId);
}
	
public List<Doc> getFiles()
{
	return docRepository.findAll();
}
}
