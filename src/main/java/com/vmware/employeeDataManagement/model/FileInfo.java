package com.vmware.employeeDataManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "FileInfo")
public class FileInfo {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Id
	private String fileName;
	private String fileType;
	private String status;
	
	public FileInfo(){
		
	}
	public FileInfo(String fileName, String fileType) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.status = "IN PROGRESS";
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
