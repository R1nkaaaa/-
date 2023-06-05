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
 * Servlet implementation class addUser
 */
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html;charset=utf8");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password;
		if (email.indexOf("@") < 6) {
			password = email.substring(0, email.indexOf("@"));
		} else {
			password = email.substring(0, 6);
		}
		String gender = request.getParameter("gender");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String phone = request.getParameter("phone");
		String photo = request.getParameter("photo");
		String address = request.getParameter("address");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		Result result = user.addUser(email, firstName, lastName, password, gender, dateOfBirth, phone, photo, address,
				roleId);
		response.getWriter().append(JSON.toJSONString(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
