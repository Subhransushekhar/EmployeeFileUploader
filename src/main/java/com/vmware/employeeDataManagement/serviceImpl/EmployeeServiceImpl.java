package com.vmware.employeeDataManagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmware.employeeDataManagement.model.Employee;
import com.vmware.employeeDataManagement.repository.EmployeeDataRepo;
import com.vmware.employeeDataManagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDataRepo dataRepo;
	
	@Override
	public String delete(int id) {
		try {
			dataRepo.deleteById(id);
		}
		catch(Exception e) {
			return "Delete Failed! Employee Not Found";
		}
		return "Delete Succesfull";
		
	}

	@Override
	public Optional<Employee> getEmplyeeInfo(int id) {
		return dataRepo.findById(id);
	}

	@Override
	public Employee updateEmployeeInfo(Employee emp) {
		Employee employee = dataRepo.save(emp);
		return employee;
	}

	@Override
	public List<Employee> getAllEmplyeeInfo() {
		return dataRepo.findAll();
	}

}
