package com.wx.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.wx.mybatis.bean.Employee;
import com.wx.mybatis.dao.EmployeeMapper;

class MybatisTest {

	SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	/*
	 * 两种缓存：
	 * 一级缓存：(本地缓存):sqlSession级别的缓存，一级缓存是一直开启的
	 * 			与数据库同一次会话期间查询到的数据会放在本地缓存中
	 *			一级缓存失效的情况
	 *			1、sqlSession不同
	 *			2、sqlSession相同，查询条件不同 	
	 *			3、sqlSession相同，但在多次查询中执行了增删改操作			
	 *			4、清除缓存  .clearCache()
	 * 			以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库
	 * 
	 * 二级缓存：(全局缓存)：基于namespace级别的缓存，一个namespace对应一个二级缓存
	 * 		工作机制：
	 * 			1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中
	 * 			2、如果会话关闭：一级缓存中的数据会被保存到二级缓存中，新的会话查询信息就可以参照二级缓存中的数据
	 * 			3、sqlSession===EmployeeMapper==>Employee
	 * 						    DepartmentMapper==>Department
	 * 				不同namespace查出的数据会放在自己对应的缓存(map)中
	 * 		使用：
	 * 			1)、开启全局二级配置缓存配置(<setting name="cacheEnabled" value="true"/>)
	 * 			2)、去mapper.xml中配置使用二级缓存
	 * 				<cache></cache>
	 * 			3)、我们的POJO需要实现序列化接口
	 * 
	 * 和缓存相关的设置/属性
	 * 			1)、cacheEnabled=true：false：关闭缓存（二级缓存）（一级缓存一直可用的）
	 * 			2)、每个select标签都有useCache="true"
	 * 					false：不使用缓存（一级缓存依然可以使用，二级关闭） 前提要将cacheEnable=true打开
	 * 			3)、每个增删改标签有个flushCache=true，查询也有，但是默认是false
	 * 					增删改执行完成后就会清除缓存（一级二级都会清除）******
	 * 			4)、sqlSession.clearCache()不会影响二级缓存，只会清除一级缓存
	 *			5)、localCacheScope：本地缓存作用域 (一级缓存SESSION)，当前会话的所有数据保存在会话缓存中
	 *													STATEMENT：禁用一级缓存
	 */
	@Test
	public void testFirstLevelCache() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee emp01 = mapper.getEmpById(1);
			System.out.println(emp01);
			//ctrl+shift+Enter在上一行插入空行
			//shift+Enter在下一行插入空行
			//ctrl+down快速复制一行在下一行
			//alt+down/up快速互换行
			//如果在这里我再次获取1号id的信息，mybatis不会在进行查询，而是去本地缓存取出数据
			Employee emp02 = mapper.getEmpById(2);
			System.out.println(emp02);
		}finally {
			openSession.close();
		}
	}
	
	@Test
	public void testSecondLevelCache() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		SqlSession openSession2 = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper1 = openSession.getMapper(EmployeeMapper.class);
			EmployeeMapper mapper2 = openSession2.getMapper(EmployeeMapper.class);
			Employee emp1 = mapper1.getEmpById(1);
			System.out.println(emp1);
			openSession.close();
			Employee emp2 = mapper2.getEmpById(1);
			System.out.println(emp2);
			openSession2.close();
		}finally {
		}
	}
}
