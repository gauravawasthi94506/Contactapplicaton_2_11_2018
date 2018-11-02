package com.citiustech.contact.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.citiustech.contact.exceptions.EmployeeNotFound;
import com.citiustech.contact.model.Address;
import com.citiustech.contact.model.Employee;
import com.citiustech.contact.service.AddressService;
import com.citiustech.contact.service.EmployeeService;

//@RequestMapping("/ContactApplication")
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	
	@Autowired
	AddressService adrService;
	@CrossOrigin
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return employeeService.findAll();
	}
	@CrossOrigin
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp,@RequestHeader HttpHeaders headers ) {
		//System.out.println("headers values are"+headers.getContentLength());
		return employeeService.save(emp);
	}
	
	
	//@PutMapping("/employees/{id}")
	@CrossOrigin
	@RequestMapping(value="/employees/{id}" , method=RequestMethod.PUT,
					consumes ={MediaType.APPLICATION_JSON_VALUE ,MediaType.APPLICATION_XML_VALUE },
							produces = { "application/json", "application/xml" })
	public ResponseEntity<Employee> updateEmployee( @PathVariable(value="id") Long empid, @RequestBody Employee empDetails){
/*		Employee emp=employeeService.findOne(empid);
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExpertise(empDetails.getExpertise());
		emp.setAdr(empDetails.getAdr());*/
	
		Address adr=new Address();
		empDetails.setId(empid);
		Employee updateEmployee=employeeService.save(empDetails);
		//System.out.println("employee details are"+empDetails);
		return new ResponseEntity(updateEmployee,HttpStatus.CREATED);
		//return ResponseEntity.ok().body(updateEmployee);
		 
	}
	
	//@Consumes(value={MediaType.APPLICATION_JSON_VALUE})
	@CrossOrigin
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empid){
		
		Employee emp=employeeService.findOne(empid);
		if(emp==null) {
			//return ResponseEntity.notFound().build();
			throw new EmployeeNotFound("Employee with ID not found"+empid);
		}
		employeeService.delete(emp);
		
		return ResponseEntity.ok().build();	
	}
	@CrossOrigin
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empid){
		
		Employee emp=employeeService.findOne(empid);
		
		if(emp==null) {
		//	return new ResponseEntity(null,HttpStatus.NOT_FOUND);
			//return ResponseEntity.notFound().build();
			throw new EmployeeNotFound("Employee with ID not found"+empid);
		}
		return ResponseEntity.ok().body(emp);
		
	}
	

}


/*
 * public class EmployeeController {
 * 
 * @Autowired EmployeeDAO employeeDAO;
 * 
 * // to save an employee
 * 
 * @PostMapping("/employees") public Employee createEmployee(@Valid @RequestBody
 * Employee emp) { return employeeDAO.save(emp); }
 * 
 * //get all employees
 * 
 * @GetMapping("/employees") public List<Employee> getAllEmployees(){ return
 * employeeDAO.findAll(); }
 * 
 * //get employee by empid
 * 
 * @GetMapping("/employees/{id}") public ResponseEntity<Employee>
 * getEmployeeById(@PathVariable(value="id") Long empid){
 * 
 * Employee emp=employeeDAO.findOne(empid);
 * 
 * if(emp==null) { return ResponseEntity.notFound().build(); } return
 * ResponseEntity.ok().body(emp);
 * 
 * }
 * 
 * 
 * //update an employee by empid
 * 
 * @PutMapping("/employees/{id}") public ResponseEntity<Employee>
 * updateEmployee(@PathVariable(value="id") Long empid,@Valid @RequestBody
 * Employee empDetails){
 * 
 * Employee emp=employeeDAO.findOne(empid); if(emp==null) { return
 * ResponseEntity.notFound().build(); }
 * 
 * emp.setName(empDetails.getName());
 * emp.setDesignation(empDetails.getDesignation());
 * emp.setExpertise(empDetails.getExpertise());
 * 
 * Employee updateEmployee=employeeDAO.save(emp); return
 * ResponseEntity.ok().body(updateEmployee);
 * 
 * 
 * 
 * }
 * 
 * //Delete an employee
 * 
 * @DeleteMapping("/employees/{id}") public ResponseEntity<Employee>
 * deleteEmployee(@PathVariable(value="id") Long empid){
 * 
 * Employee emp=employeeDAO.findOne(empid); if(emp==null) { return
 * ResponseEntity.notFound().build(); } employeeDAO.delete(emp);
 * 
 * return ResponseEntity.ok().build();
 * 
 * 
 * }
 * 
 * 
 * 
 * }
 */