package com.vmware.employeeDataManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vmware.employeeDataManagement.model.Employee;

@Service
public interface EmployeeService {
	public String delete(int id);
	public Optional<Employee> getEmplyeeInfo(int id);
	public Employee updateEmployeeInfo(Employee emp);
	public List<Employee> getAllEmplyeeInfo();
}
