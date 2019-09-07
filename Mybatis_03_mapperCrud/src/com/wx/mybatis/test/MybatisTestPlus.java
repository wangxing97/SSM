package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.wx.mybatis.bean.Department;
import com.wx.mybatis.bean.Employee;
import com.wx.mybatis.dao.DeparmentMapper;
import com.wx.mybatis.dao.EmployeeMapperPlus;

class MybatisTestPlus {
	
	SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	
	@Test
	public void Test01() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee empById = mapper.getEmpById(2);
			System.out.println(empById);
			System.out.println(empById.getDepartment());
		}finally {
			openSession.close();
		}
	}
	public void Test02() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee empById = mapper.getEmpAndDeptByID(1);
			System.out.println(empById);
			System.out.println(empById.getDepartment());
		}finally {
			openSession.close();
		}
	}
	
	public void Test03() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee empById = mapper.getEmpByStep(1);
			System.out.println(empById.getLastName());
			//System.out.println(empById.getDepartment());
		}finally {
			openSession.close();
		}
	}
	
	
	public void Test04() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			DeparmentMapper mapper = openSession.getMapper(DeparmentMapper.class);
			Department deptById = mapper.getDeptById(1);
			System.out.println(deptById);
		}finally {
			openSession.close();
		}
	}
	
	
	public void Test06() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			DeparmentMapper mapper = openSession.getMapper(DeparmentMapper.class);
			Department deptByIdPlus = mapper.getDeptByIdPlus(1);
			System.out.println(deptByIdPlus);
			System.out.println(deptByIdPlus.getEmps());
		}finally {
			openSession.close();
		}
	}
	
	@Test
	public void Test07() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			DeparmentMapper mapper = openSession.getMapper(DeparmentMapper.class);
			Department deptByIdPlus = mapper.getDeptByStep("开发部");
			System.out.println(deptByIdPlus.getEmps());
			/*
			 * List<Employee> emps = deptByIdPlus.getEmps(); ListIterator<Employee>
			 * listIterator = emps.listIterator(); while(listIterator.hasNext()) { Employee
			 * next = listIterator.next(); System.out.println(next); }
			 */
		}finally {
			openSession.close();
		}
	}
}
