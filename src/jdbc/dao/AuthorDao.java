package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.vo.AuthorVo;

public class AuthorDao {
	public List<AuthorVo> fetch() throws ClassNotFoundException, SQLException{
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "icto55", "icto55");
			
			ps = conn.prepareStatement("select * from author");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new AuthorVo(rs.getLong(1), rs.getString(2), rs.getString(3)));
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		}

		return list;
	}
	
	public void insert(AuthorVo author) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "icto55", "icto55");

			st = conn.prepareStatement("insert into author values(seq_author.nextval, ?, ?)");
			st.setString(1, author.getName());
			st.setString(2, author.getBio());

			st.executeUpdate();
			
		} finally {
			if(st != null) {
				st.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
}
