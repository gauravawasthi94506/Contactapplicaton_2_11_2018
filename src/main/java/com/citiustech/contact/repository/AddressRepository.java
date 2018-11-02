package com.citiustech.contact.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository.*;
import com.citiustech.contact.model.Address;
import com.citiustech.contact.model.Employee;



@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	public Address findByEmployeeId(Long employeeID);
	
	/*PagingAndSortingRepository<Employee, Long> repository  // … get access to a bean
			Page<Employee> users= repository.findAll(new PageRequest(1, 20));*/
}