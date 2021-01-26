package com.vmware.employeeDataManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmware.employeeDataManagement.model.Employee;

@Repository
public interface EmployeeDataRepo extends JpaRepository<Employee,Integer> {
	
}
