package com.vmware.employeeDataManagement.serviceImpl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vmware.employeeDataManagement.model.Employee;
import com.vmware.employeeDataManagement.model.FileInfo;
import com.vmware.employeeDataManagement.repository.EmployeeDataRepo;
import com.vmware.employeeDataManagement.repository.FileInfoRepo;
import com.vmware.employeeDataManagement.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	EmployeeDataRepo dataRepo;
	
	@Autowired
	FileInfoRepo fileInfoRepo;

	String path = "";
	
	
	@Override
	public void uploadtoLocal(MultipartFile multipartFile) {
		try {
			byte[] fileData  = multipartFile.getBytes();
			Path filePath = Paths.get(path+multipartFile.getOriginalFilename());
			Files.write(filePath, fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	@Async
	public String uploadtoDB(byte[] fileData) {
		
		List<Employee>  empList= new ArrayList<>();
		try {
			InputStream is = null;
			BufferedReader bfReader = null;
			is = new ByteArrayInputStream(fileData);
            bfReader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = bfReader.readLine())!=null) {
				String[] empData  = line.split("\\s+");
				Employee emp = new Employee(empData[0],Integer.parseInt(empData[1]));
				empList.add(emp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error!!!";
		}
		
			
		if(!empList.isEmpty()) {
			dataRepo.saveAll(empList);
		}
        
        return "Success!!";
	}
	
	public boolean uploadFileInfotoDB(String fileName , String fileType) {
		FileInfo fileInfo = null;
		try {
			 fileInfo = new  FileInfo(fileName, fileType);
			fileInfo = fileInfoRepo.save(fileInfo);
			fileInfo.setStatus("COMPLETED");
			fileInfoRepo.save(fileInfo);
			return true;

		}catch(Exception e) {
			fileInfo.setStatus("ERROR");
			fileInfoRepo.save(fileInfo);
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public String getUploadStatus( String fileName) {
		Optional<FileInfo> fileInfo = fileInfoRepo.findById(fileName);
		if(fileInfo.isEmpty()) {
			return "File Not Found";
		}else
			return fileInfo.get().getStatus();
	}

	
}
