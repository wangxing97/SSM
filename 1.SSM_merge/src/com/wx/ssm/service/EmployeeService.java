package com.wx.ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.ssm.bean.Employee;
import com.wx.ssm.dao.EmployeeDao;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	public Employee getEmpById(Integer id) {
		Employee emp = employeeDao.getEmpById(id);
		return emp;
	}
	
}
