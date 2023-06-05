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
 * Servlet implementation class getTicketStatistics
 */
@WebServlet("/getTicketStatistics")
public class getTicketStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTicketStatistics() {
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
		String startDate = request.getParameter("startDate") + "-01 00:00:00";
		String endDate = request.getParameter("endDate") + "-01 00:00:00";
		Result result = schedule.getTicketStatistics(startDate, endDate);
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
