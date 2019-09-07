package com.wx.mybatis.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.wx.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	@MapKey("id")
	public Map<Integer, Employee> getEmpLikeReturnMap(String lastName);
	
	public List<Employee> getEmpLike(String lastName);
	
	public Employee getEmpById(Integer id);
	
	public void addEmp(Employee employee);
	
	public void addManyEmp(List<Employee> list);
	
	public void delManyEmp(Integer[] ids);
	
	public void updateEmp(Employee employee);
	
	public boolean delteEmpById(Integer id);
}
