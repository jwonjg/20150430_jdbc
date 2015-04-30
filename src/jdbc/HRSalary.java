package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HRSalary {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");

			stmt = conn.createStatement();
		
			rs = stmt.executeQuery("select first_name, last_name from employees where first_name like '%ab%' or last_name like '%ab%'");
			while (rs.next()) {
				String first_name = rs.getString(1);
				String last_name = rs.getString(2);
				System.out.println(first_name+" "+last_name);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
