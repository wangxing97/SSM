package com.wx.ssm.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wx.ssm.bean.Employee;
import com.wx.ssm.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired EmployeeService employeeService;
	
	@RequestMapping(value = "/getEmpById")
	public String getEmpById(@RequestParam(value = "id",defaultValue = "1")Integer id,Model model) {
		Employee emp = employeeService.getEmpById(id); 
		model.addAttribute("emp",emp);
	  	return "success";
	}
	
	@RequestMapping(value = "/getAllEmps")
	public String getAllEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model) {
		//紧跟着后面的查询就是分页查询
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getAllEmps();
		PageInfo<Employee> info = new PageInfo<>(emps, 5);
		model.addAttribute("emps", info);
		return "success";
	}
}
