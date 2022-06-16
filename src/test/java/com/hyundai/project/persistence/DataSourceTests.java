package com.hyundai.project.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTests {	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//end static
	
	@Test
	public void TestConnection() {
		try {
			Connection conproduct = 
					DriverManager.getConnection(
							"jdbc:oracle:thin:@123.142.252.25:40026/xepdb1"
							,"productmanager","Handsome123#");
			System.out.println(conproduct);
			
			Connection conuser = 
					DriverManager.getConnection(
							"jdbc:oracle:thin:@123.142.252.25:40026/xepdb1"
							,"usermanager","Handsome123#");
			System.out.println(conuser);
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
		}//end try			
	}//end test
}//end class