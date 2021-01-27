package com.vmware.employeeDataManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vmware.employeeDataManagement.service.FileUploadService;

@RestController
@RequestMapping("/api/v1")
public class FileUploadController {
	
@Autowired
FileUploadService fileUploadService;

	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/upload/local")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
		if(multipartFile.isEmpty()) {
			return "File is Empty!!!";
		}
		fileUploadService.uploadtoLocal(multipartFile);
		return "Successfull";
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/upload/db")
	public String uploadDB(@RequestParam("file") MultipartFile multipartFile) {
		
		if(multipartFile.isEmpty()) {
			return "File is Empty!!!";
		}
		
		try {
			fileUploadService.uploadtoDB(multipartFile.getBytes());				
			fileUploadService.uploadFileInfotoDB(multipartFile.getOriginalFilename(),multipartFile.getContentType());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Upload In progress. Please access getUploadStatus to tack the status";
	}
	
	@GetMapping("/getUploadStatus/{id}")
	public String getUploadStatus(@PathVariable int id) {
		return fileUploadService.getUploadStatus(id);
		
	}
	
	
}	
