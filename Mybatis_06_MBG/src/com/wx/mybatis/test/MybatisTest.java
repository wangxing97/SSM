package com.wx.mybatis.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.wx.mybatis.bean.Employee;
import com.wx.mybatis.bean.EmployeeExample;
import com.wx.mybatis.bean.EmployeeExample.Criteria;
import com.wx.mybatis.dao.EmployeeMapper;

class MybatisTest {

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
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			EmployeeExample example = new EmployeeExample();
			example.setDistinct(true);
			example.setOrderByClause("d_id");
			List<Employee> emps = mapper.selectByExample(example);
			for (Employee emp : emps) {
				System.out.println(emp);
			}
		}finally {
			openSession.close();
		}
	}

	@Test
	public void Test02() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			EmployeeExample example = new EmployeeExample();
			example.setOrderByClause("id");
			Criteria criteria = example.createCriteria();
			criteria.andLastNameLike("%1%");
			System.out.println("-----------------");
			List<Employee> emps = mapper.selectByExample(example);
			for (Employee emp : emps) {
				System.out.println(emp);
			}
		}finally {
			openSession.close();
		}
		
	}
	
	@Test
	public void Test03() throws IOException {
		
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		List<Employee> lists = new ArrayList<Employee>();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			for(int i = 0; i < 1000; i++) {
				Employee employee = new Employee();
				String str = UUID.randomUUID().toString().substring(0, 6);
				employee.setLastName(str);
				employee.setGender(i%2==1?"男":"女");
				employee.setEmail(str+"@qq.com");
				lists.add(employee);
			}
			mapper.insertBatch(lists);
		}finally {
			openSession.close();
		}
	}
}




