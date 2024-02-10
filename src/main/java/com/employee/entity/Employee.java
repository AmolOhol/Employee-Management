package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_info")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String employeeName;
	
	@Column
	private String gender;
	
	@Column
	private long mobile_no;
	
	@Column
	private String email;
	
	@Column
	private long salary;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(long id, String employeeName, String gender, long mobile_no, String email, long salary) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.gender = gender;
		this.mobile_no = mobile_no;
		this.email = email;
		this.salary = salary;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(long mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", gender=" + gender + ", mobile_no="
				+ mobile_no + ", email=" + email + ", salary=" + salary + "]";
	}
	
	
	
}
