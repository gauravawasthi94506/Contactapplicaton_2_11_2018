package com.citiustech.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.contact.model.Address;
import com.citiustech.contact.model.Employee;
import com.citiustech.contact.repository.AddressRepository;
import com.citiustech.contact.repository.EmployeeRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	/*to save an employee*/
	
	
	public Address save(Address adr) {
	
		return addressRepository.save(adr);
	}
	
	
	
	
	/* search all employees*/
	
	/*public List<Address> findAll(Long empId){
		return addressRepository.findByEmployeeId(empId);
	}
	*/
	
	/*get an employee by id*/
	public Address findOne(Long empid) {
		return (Address) addressRepository.findByEmployeeId(empid);
	}
	
	

	
	public void delete(Address adr) {
		addressRepository.delete(adr);
	}
	
	

}
