package com.citiustech.contact.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Address")
@EntityListeners(AuditingEntityListener.class)
@XmlRootElement
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	
	private String state;
	public Address()
	{
		
	}
	public Address(Long id, String state, int pincode) {
		this.id = id;
		this.state = state;
		this.pincode = pincode;
	}
	private int pincode;
	
	@OneToOne(mappedBy="adr")
	private Employee employee;
	@JsonIgnore
	public Employee getEmp() {
		return employee;
	}
	public void setEmp(Employee emp) {
		this.employee = emp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
	
}
