 package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.wx.mybatis.bean.Employee;
import com.wx.mybatis.dao.EmployeeMapper;
import com.wx.mybatis.dao.EmployeeMapperAnnotation;

/*
 * 1、接口编程
 * 原生：                Dao  ====>  DaoImpl
 * mybatis:     Mapper ======>  xxMapper.xml
 * 
 * 2、Sqlsession代表和数据库的一次会话，用完必须关掉
 * 3、SqlSession和Connection一样都是非线程安全，必须每次都获取新的对象，不能放在外部
 * 4、mapper接口没有实现类，但是mybatis为这个接口生成一个代理对象
 * （将接口和xml进行绑定）
 * 5、两个重要的配置文件
 *    mybatis的全局配置文件：包含数据库连接池信息，事务管理器等 ...... 可以没有
 *    sql映射文件：保存了每一个sql语句的映射信息
 *    	          将sql操作抽取出来
 * */
class MybatisTest {

	SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	/*
	 * 1、根据XML配置文件（全局配置文件）创建一个SqlSessionFactory对象
	 * 	  有数据源一些运行环境信息
	 * 2、sql映射文件：配置了每一个sql，以及sql的封装规则等。
	 * 3、将sql映射文件注册在全局配置文件中
	 * 4、写代码：
	 * 	 1）通过全局配置文件得到SqlSessionFactory
	 *   2）使用sqlSession工厂，获取到sqlSession对象使用它来进行CRUD
	 *   3）一个sqlSession就是代表和数据库的一次会话，用完关闭，sql都是保存在sql映射文件中。
	 *   
	 * */
	
//	void test() throws IOException {
//		
//		//1、获取sqlSessionFactory对象
//		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//		
//		//2、获取sqlSession实例，能直接执行已经映射的sql语句
//		SqlSession openSession = sqlSessionFactory.openSession();
//		//第一个参数为唯一标识，第二个为sql需要的参数
//		try{
//			Employee employee = openSession.selectOne("selectEmp", 1);
//			System.out.println(employee);
//		}finally {
//			openSession.close();
//		}
//		
//	}
	
	@Test
	public void test01() throws IOException {
		//1.获取sqlSessionFactory对象
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
		//2、根据sqlSessionFactory对象获取sqlSession对象
		SqlSession sqlSession = sessionFactory.openSession();
		try {
			//3、获取接口的实现类对象
			//会为接口自动创建一个代理对象，代理对象去执行增删改查
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee empById = mapper.getEmpById(1);
			System.out.println("mapper:"+mapper.getClass());
			System.out.println("66:"+empById);
		}finally {
			sqlSession.close();
		}
	}
	
	public void testO2() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		SqlSession session = factory.openSession();
		try {
			EmployeeMapperAnnotation annotation = session.getMapper(EmployeeMapperAnnotation.class);
			Employee employee =  annotation.getEmpById(1);
			System.out.println(employee);
		}finally {
			session.close();
		}
		
	}
	
	public void test12() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapperAnnotation annotation = session.getMapper(EmployeeMapperAnnotation.class);
			Employee empByIdOrName = annotation.getEmpByIdOrName(1, "xiaoxing");
			System.out.println(empByIdOrName);
		}finally {
			session.close();
		}
	}
	
	public void test03() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//sqlsession不自动提交
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(null, "jerry", "jerry@qq.com", "1");
			mapper.addEmp(employee);
			session.commit();
			
		}finally {
			session.close();
		}
	}
	
	public void test04() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		SqlSession openSession = factory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(1, "xiao xing", "xiaoxing@qq.com", "2");
			mapper.updateEmp(employee);
			openSession.commit();
		}finally {
			openSession.close();
		}
	}
	public void test05() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			mapper.delteEmpById(2);
		}finally {
			openSession.close();
		}
	}
	
	public void test06() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> list = new ArrayList();
			for(int i = 0; i < 2; i++ ) {
				Employee employee = new Employee(null, "lastname"+i, i+"@qq.com", i+"");
				list.add(employee);
			}
			mapper.addManyEmp(list);
		}finally {
			openSession.close();
		}
	}
	
	public void test07() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Integer obj[] = new Integer[22];
			int j = 0;
			for(int i = 42; i < 63; i++ ) {
				obj[j++] = i;
			}
			mapper.delManyEmp(obj);
		}finally {
			openSession.close();
		}
	}
	public void test08() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> empLike = mapper.getEmpLike("%name%");
			for (Employee employee : empLike) {
				System.out.println(employee);
			}
		}finally {
			openSession.close();
		}
	}
	@Test
	public void test09() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Map<Integer,Employee> map = mapper.getEmpLikeReturnMap("%小%");
			System.out.println(map);
		}finally {
			openSession.close();
		}
	}
//	@Test
//	public void test01() throws IOException {
//		//1.获取sqlSessionFactory对象
//		SqlSessionFactory sessionFactory = getSqlSessionFactory();
//		//2、根据sqlSessionFactory对象获取sqlSession对象
//		SqlSession sqlSession = sessionFactory.openSession();
//		try {
//			//3、获取接口的实现类对象
//			//会为接口自动创建一个代理对象，代理对象去执行增删改查
//			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//			boolean empById = mapper.delteEmpById(1);
////			System.out.println("mapper:"+mapper.getClass());
//			System.out.println("88:"+empById);
//		}finally {
//			sqlSession.close();
//		}
//	}
//	public void test02() throws IOException {
//		//1.获取sqlSessionFactory对象
//		SqlSessionFactory sessionFactory = getSqlSessionFactory();
//		//2、根据sqlSessionFactory对象获取sqlSession对象
//		SqlSession sqlSession = sessionFactory.openSession();
//		try {
//			//3、获取接口的实现类对象
//			//会为接口自动创建一个代理对象，代理对象去执行增删改查
//			//EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//			int flag = sqlSession.delete("deleteuser", 1);
////			System.out.println("mapper:"+mapper.getClass());
//			System.out.println("777:"+flag);
//		}finally {
//			sqlSession.close();
//		}
//	}
}
