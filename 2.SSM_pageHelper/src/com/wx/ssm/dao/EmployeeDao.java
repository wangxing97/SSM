package com.wx.ssm.dao;
import java.util.List;

import com.wx.ssm.bean.Employee;

public interface EmployeeDao {
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getAllEmps();
}
