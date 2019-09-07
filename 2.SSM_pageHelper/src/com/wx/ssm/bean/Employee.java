package com.wx.ssm.bean;

import java.io.Serializable;

//@Alias("emp")
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private Department department;
	public Employee() {
		
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Employee(Integer id, String lastName, String email, String gender) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender
				+ ", department=" + department + "]";
	}
	
}

/*
 * Error creating bean with name 'sqlSessionFactoryBean' defined in class path resource 
 * [spring/applicationContext.xml]: Invocation of init method failed; 
 * nested exception is org.apache.ibatis.builder.BuilderException: 
 * Error creating document instance.  Cause: org.xml.sax.SAXParseException;
 *  lineNumber: 6; columnNumber: 16; 必须声明元素类型 "configuration"。
*/