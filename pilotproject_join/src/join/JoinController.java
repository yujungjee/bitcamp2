package join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JoinDAO joinDAO;

	public void init() throws ServletException {
		joinDAO = new JoinDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo(); //URL에서 요청명을 가져옴
		System.out.println("action:" + action);
		
		if (action.equals("/addMember.do")) { 
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd1");
			JoinVO joinVO = new JoinVO(id, name, pwd);
			
			// 기존에 등록된 아이디가 있는지 찾는다.
			Boolean b_chk = joinDAO.checkId(id);
			if(b_chk) { //같은 아이디가 없으면
				System.out.println("아이디 등록 시작");
				joinDAO.addMember(joinVO);
				System.out.println("아이디 등록 완료");
				nextPage = "/pilotproject_0724/join_jsp/joinComplete.jsp?id="+id; // 원래 화면으로
			}else { //같은 아이디가 있으면
				System.out.println("중복 아이디 있음");
				nextPage = "/pilotproject_0724/join_jsp/joinForm.jsp?chk=N"; // 원래 화면으로
			}
			response.sendRedirect(nextPage);
		} 
		
	}
}