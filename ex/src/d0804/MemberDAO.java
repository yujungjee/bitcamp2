package d0804;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(String _id) {
		List<MemberVO> memberList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "";
			if(_id.length() == 0) {
				query = " SELECT * FROM t_member ";
				pstmt = conn.prepareStatement(query);
			} else {
				query = " SELECT * FROM t_member WHERE id LIKE ? ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, _id);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String joinDate = rs.getString("joindate");
				MemberVO vo = new MemberVO(id, pwd, name, email, joinDate);
				memberList.add(vo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}
}