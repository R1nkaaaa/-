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
 * Servlet implementation class getSchedule
 */
@WebServlet("/getSchedule")
public class getSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html;charset=utf8");
		String fromCity = request.getParameter("fromCity");
		String toCity = request.getParameter("toCity");
		String startDate = request.getParameter("startDate")+" 00:00:00";
		String endDate = request.getParameter("endDate")+" 23:59:59";
		Result result = schedule.getSchedule(fromCity, toCity, startDate, endDate);
		response.getWriter().append(JSON.toJSONStringWithDateFormat(result,"yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
