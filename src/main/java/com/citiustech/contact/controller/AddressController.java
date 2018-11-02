/*
package com.citiustech.contact.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.contact.model.Address;
import com.citiustech.contact.model.Employee;
import com.citiustech.contact.service.AddressService;
import com.citiustech.contact.service.EmployeeService;

import javassist.bytecode.Descriptor.Iterator;

@RestController
public class AddressController {
	

	@Autowired
	EmployeeService employeeService;
	@Autowired
	AddressService addressService;
	
	@GetMapping("/employees/{id}/address")
	public List<Address> getAllAddress(@PathVariable(value="id") Long empid){
		
		
		
		//System.out.println("employee id is "+empid+"data is "+adr);
		return addressService.findAll(empid);
	}
	
	@PostMapping("/employees/{empid}/address/")
	public Address createAddress(@RequestBody Address adr,@PathVariable(value="empid") Long empid) {
		System.out.println("emp id is"+empid);
		adr.setEmp(new Employee(empid,"","",""));
		return addressService.save(adr);
	}
	
	
	@PutMapping("/employees/{id}/address/{adrID}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address adrdetails,@PathVariable(value="id") Long empid,
			@PathVariable(value="adrID") Long adrId){
		
		Address adr=addressService.findOne(adrId);
		if(adr==null) {
			return ResponseEntity.notFound().build();
		}
		
		adr.setState(adrdetails.getState());
		adr.setPincode(adrdetails.getPincode());

		adr.setEmp(new Employee(empid,"","",""));	
		
		return ResponseEntity.ok().body(addressService.save(adr));

	}
	
	@DeleteMapping("/employees/{id}/address/{adrID}")
	public ResponseEntity<Employee> deleteAddress(@PathVariable(value="adrID") Long adrId){
		
		Address adr=addressService.findOne(adrId);
		if(adr==null) {
			return ResponseEntity.notFound().build();
		}
		addressService.delete(adr);
		
		return ResponseEntity.ok().build();	
	}
	
	
	

}

*/