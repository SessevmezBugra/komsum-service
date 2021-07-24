package com.komsum.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.komsum.file.entity.FileEntity;

public interface FileService {

	FileEntity create(MultipartFile file) throws IOException;
	
	FileEntity findById(String id);
	
	List<FileEntity> findByIdIn(List<String> ids);
	
	List<FileEntity> findAll();
}
