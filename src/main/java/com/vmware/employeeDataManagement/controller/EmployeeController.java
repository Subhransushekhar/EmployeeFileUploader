package com.vmware.employeeDataManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.employeeDataManagement.model.Employee;
import com.vmware.employeeDataManagement.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employeeInfo/{id}")
	public Optional<Employee> emloyeeInfo(@PathVariable("id") int id) {
		return employeeService.getEmplyeeInfo(id);
	}
	
	@GetMapping("/employeeInfo")
	public List<Employee> allEmloyeeInfo() {
		List<Employee> empList =  employeeService.getAllEmplyeeInfo();
		return empList;
	}
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmpInfo(@PathVariable("id") int id) {
		return employeeService.delete(id);
		
	}
	
	@PostMapping("/update")
	public Employee updateEmpData(@RequestParam String name, @RequestParam int age) {
		Employee emp = new Employee(name,age);
		return employeeService.updateEmployeeInfo(emp);
		
	}
}
