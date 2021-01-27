package com.vmware.employeeDataManagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	public void uploadtoLocal(MultipartFile multipartFile);
	public String uploadtoDB(byte[] uploadData);
	public boolean uploadFileInfotoDB(String fileName , String fileType);
	public String getUploadStatus(int id);
}
