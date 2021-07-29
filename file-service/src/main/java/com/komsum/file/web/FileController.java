package com.komsum.file.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.komsum.file.entity.FileEntity;
import com.komsum.file.service.FileService;
import com.komsum.file.util.constant.ApiPaths;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.FileCtrl.CTRL)
@RequiredArgsConstructor
public class FileController implements SecuredRestController{

	private final FileService fileService;
	
	@RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<String> createFile(@RequestPart("file") MultipartFile file) throws IOException {
		FileEntity fileEntity = fileService.create(file);
		return ResponseEntity.ok(fileEntity.getId());
    }
	
	@RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<FileEntity>> getAllFile(){
        return ResponseEntity.ok(fileService.findAll());
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getFileById(@PathVariable(value="id", required=true) String id){
    	FileEntity fileEntity = fileService.findById(id);
    	HttpHeaders headers = new HttpHeaders();
    	if (fileEntity.getType().equals(MediaType.IMAGE_PNG_VALUE)) {
    		headers.setContentType(MediaType.IMAGE_PNG); 
		}else if(fileEntity.getType().equals(MediaType.IMAGE_JPEG_VALUE)) {
    		headers.setContentType(MediaType.IMAGE_JPEG); 
		}else if(fileEntity.getType().equals(MediaType.IMAGE_GIF_VALUE)) {
    		headers.setContentType(MediaType.IMAGE_GIF); 
		}
        headers.setContentLength(fileEntity.getData().length);
        return ResponseEntity.ok().headers(headers)
                .body(fileEntity.getData());
    }
	
	@RequestMapping(value="/in", method= RequestMethod.POST)
    public ResponseEntity<List<FileEntity>> getFilesByIdIn(@RequestBody List<String> ids){
        return ResponseEntity.ok(fileService.findByIdIn(ids));
    }
}
