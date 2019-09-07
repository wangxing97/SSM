package com.wx.mybatis.dao;

import com.wx.mybatis.bean.Department;


public interface DeparmentMapper {

	public Department getDeptById(Integer id);
	
	public Department getDeptByIdPlus(Integer id);
	
	public Department getDeptByStep(String dept_name);
	
}
