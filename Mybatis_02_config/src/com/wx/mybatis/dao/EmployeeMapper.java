package com.wx.mybatis.dao;

import com.wx.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);
	
	public boolean delteEmpById(Integer id);
}
