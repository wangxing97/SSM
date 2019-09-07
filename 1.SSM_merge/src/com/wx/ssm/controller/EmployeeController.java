package com.wx.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
