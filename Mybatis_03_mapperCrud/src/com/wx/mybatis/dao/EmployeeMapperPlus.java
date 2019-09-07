package com.wx.mybatis.dao;

import java.util.List;

import com.wx.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
	
	public Employee getEmpById(Integer id);
	
	public Employee getEmpAndDeptByID(Integer id);
	
	public Employee getEmpByStep(Integer id);
	
	public List<Employee> getEmpBydept(Integer id);
	
}
