package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HRSearchEmployee2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String nextLine = sc.nextLine();
		String[] split = nextLine.split(":");
		int minSal = Integer.parseInt(split[0]);
		int maxSal = Integer.parseInt(split[1]);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");

			pstmt = conn.prepareStatement("select first_name, last_name, salary from employees where salary > ? and salary < ?");
			pstmt.setInt(1, minSal);
			pstmt.setInt(2, maxSal);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String first_name = rs.getString(1);
				String last_name = rs.getString(2);
				int salary = rs.getInt(3);
				System.out.println("name:"+first_name+" "+last_name+", salary:"+salary);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
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
