package join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JoinDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public JoinDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<JoinVO> listMembers() {
		List<JoinVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from member";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String member_id = rs.getString("member_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				JoinVO memberVO = new JoinVO(member_id, name, password);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	public void addMember(JoinVO m) {
		try {
			conn = dataFactory.getConnection();
			String member_id = m.getMember_id();
			String name = m.getName();
			String password = m.getPwd();
			System.out.println(name);
			String query = "insert into member";
			query += "(member_id, name, password, regdate)";
			query += " values(?, ?, ?, sysdate)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkId(String id) {
		String sql = "SELECT COUNT(1) AS CNT FROM MEMBER WHERE MEMBER_ID = ?";
		Boolean b_chk = true;
		try {
			Connection conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (Integer.parseInt(rs.getString("CNT")) > 0) {
					b_chk = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b_chk;
	}

}