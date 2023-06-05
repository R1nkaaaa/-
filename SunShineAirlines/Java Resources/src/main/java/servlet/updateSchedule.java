package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import pojo.Result;
import service.schedule;

/**
 * Servlet implementation class updateSchedule
 */
@WebServlet("/updateSchedule")
public class updateSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateSchedule() {
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
		int scheduleId;
		try {
			scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
		} catch (Exception e) {
			scheduleId = 0;
		}
		String status = request.getParameter("status");
		Result result = schedule.updateSchedule(scheduleId, status);
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
