package jdbc.test;

import java.sql.SQLException;
import java.util.List;

import jdbc.dao.AuthorDao;
import jdbc.vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String[] args) {
		try {
			insertTest(new AuthorVo(-1L, "공자", "논어"));

			insertTest(new AuthorVo(-1L, "장자", "도덕경"));

			List<AuthorVo> list = selectTest();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getId()+"|"+list.get(i).getName()+"|"+list.get(i).getBio());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static List<AuthorVo> selectTest() throws ClassNotFoundException, SQLException {
		AuthorDao dao = new AuthorDao();
		return dao.fetch();
	}

	private static void insertTest(AuthorVo authorVo) throws ClassNotFoundException, SQLException {
		AuthorDao dao = new AuthorDao();
		dao.insert(authorVo);
	}
}
