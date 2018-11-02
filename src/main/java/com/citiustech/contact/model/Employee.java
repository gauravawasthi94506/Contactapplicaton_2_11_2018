package com.citiustech.contact.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Employees")
@EntityListeners(AuditingEntityListener.class)
@XmlRootElement
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	

	public Employee(Long id,  String name, String designation, String expertise) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.expertise = expertise;
		this.adr=adr;
	}
	@OneToOne(cascade=CascadeType.ALL)	
	private Address adr;
	public Address getAdr() {
		return adr;
	}

	public void setAdr(Address adr) {
		this.adr = adr;
	}
	@NotBlank
	private String name;
	
	@NotBlank
	private String designation;
	
	public Employee() {
		
	}

	
	public Employee(Long id, Address adr, String name, String designation, String expertise) {
		super();
		this.id = id;
		this.adr = adr;
		this.name = name;
		this.designation = designation;
		this.expertise = expertise;
	}
	@NotBlank
	private String expertise;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	

}
