package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import pojo.Result;
import service.user;

/**
 * Servlet implementation class userList
 */
@WebServlet("/userList")
public class userList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf8");
		int roleId;
		try {
			roleId = Integer.parseInt(request.getParameter("roleId"));
		} catch (Exception e) {
			roleId = 0;
		}
		String name = request.getParameter("name");
		int startPage;
		try {
			startPage = Integer.parseInt(request.getParameter("startPage"));
		} catch (Exception e) {
			startPage = 1;
		}
		int pageSize;
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			pageSize = 10;
		}
		Result result = user.userList(roleId, name, startPage, pageSize);
		response.getWriter().append(JSON.toJSONString(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
