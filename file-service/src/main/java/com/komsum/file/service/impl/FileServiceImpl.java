package com.komsum.file.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.komsum.file.entity.FileEntity;
import com.komsum.file.repository.FileRepository;
import com.komsum.file.service.FileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

	private final FileRepository fileRepository;
	
	@Override
	public FileEntity create(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());
		return fileRepository.save(fileEntity);
	}

	@Override
	public FileEntity findById(String id) {
		return fileRepository.findById(id).get();
	}

	@Override
	public List<FileEntity> findByIdIn(List<String> ids) {
		return fileRepository.findAllById(ids);
	}

	@Override
	public List<FileEntity> findAll() {
		return fileRepository.findAll();
	}

}
