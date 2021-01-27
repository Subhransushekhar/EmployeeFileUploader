package com.vmware.employeeDataManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmware.employeeDataManagement.model.FileInfo;

public interface FileInfoRepo extends JpaRepository<FileInfo,Integer> {
	
}