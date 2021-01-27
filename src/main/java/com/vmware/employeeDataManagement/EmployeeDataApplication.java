package com.vmware.employeeDataManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EmployeeDataApplication  {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataApplication.class, args);
	}

}
