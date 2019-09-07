package com.wx.ssm.test;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.wx.ssm.bean.Employee;
import com.wx.ssm.service.EmployeeService;

class MybatisTest {
	
	private ApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	@Test
	public void Test01() throws IOException {
		 EmployeeService teacherService = ioc.getBean(EmployeeService.class);
		 Employee emp = teacherService.getEmpById(1); 
		 System.out.println(emp);
	}
}
