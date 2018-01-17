package com.sjm.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

	@Test
	public void testDataSource() throws SQLException {
		ApplicationContext acx = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource = acx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
}
