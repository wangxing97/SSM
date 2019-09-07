package com.wx.mybatis.dao;

import java.util.List;

import com.wx.mybatis.bean.Employee;

public interface DynamicSQLMapper {
	
	public List<Employee> getEmpsByDynamicSQLIf(Employee emp);
	
	public List<Employee> getEmpsByDynamicSQLTrim(Employee emp);
	
	public List<Employee> getEmpsByDynamicSQLChoose(Employee emp);
	
	public void updateEmp(Employee emp);
	
	public List<Employee> getEmpsByForeach(List<Integer> ids);
	
	public List<Employee> getEmpsTestInnerParameter(Employee emp);
}
