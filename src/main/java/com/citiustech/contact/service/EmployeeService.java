package com.citiustech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.citiustech.contact.model.Address;
import com.citiustech.contact.model.Employee;
import com.citiustech.contact.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AddressService adrService;
	
	/*to save an employee*/
	
	
	public Employee save(Employee emp) {
		
		Employee empDetails=employeeRepository.save(emp);
		return empDetails;
	}
	
	public Employee update(Long empid,Employee empDetails) {
		
	/*	Employee emp=this.findOne(empid);
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExpertise(empDetails.getExpertise());
		
		Address adr=adrService.findOne(empid);
		adr.setState(empDetails.getAdr().getState());
		adr.setPincode(empDetails.getAdr().getPincode());	
		emp.setAdr(adr);*/
		
		Employee updateEmployee=this.save(empDetails);
		
		return updateEmployee;
	}
	/* search all employees*/
	
	public List<Employee> findAll(){
		//employeeRepository.findAll(new PageRequest(0, 1, Direction.ASC, ""));
		return (List<Employee>) employeeRepository.findAll();
	}
	
	
	/*get an employee by id*/
	public Employee findOne(Long empid) {
		return employeeRepository.findOne(empid);
	}
	
	
	/*delete an employee*/
	
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}
	

}
