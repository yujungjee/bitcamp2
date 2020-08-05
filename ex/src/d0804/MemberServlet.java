package d0804;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		MemberDAO dao = new MemberDAO();
		
		
		String _id = request.getParameter("id");
		List<MemberVO> list = dao.listMembers(_id);
		
		JSONObject memberInfo;
		JSONArray membersArray = new JSONArray();
		for(int i=0; i<list.size(); i++) {
			MemberVO vo = list.get(i);
			memberInfo = new JSONObject();
			memberInfo.put("id", vo.getId());
			memberInfo.put("pwd", vo.getPwd());
			memberInfo.put("name", vo.getName());
			memberInfo.put("email", vo.getEmail());
			memberInfo.put("joinDate", vo.getJoindate());
			membersArray.add(memberInfo);
		}
		String jsonInfo = membersArray.toJSONString();
		writer.print(jsonInfo);
	}
}