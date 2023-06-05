package service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import pojo.Result;

public class schedule {
	public static Result getSchedule(String fromCity, String toCity, String startDate, String endDate) {
		Result result = new Result();
		List<HashMap<String, Object>> list = dao.schedule.getSchedule(fromCity, toCity, startDate, endDate);
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
		}
		return result;
	}
	
	public static Result updateSchedule(int scheduleId, String status) {
		Result result = new Result();
		int i = dao.schedule.updateSchedule(scheduleId, status);
		if (i > 0) {
			result.setFlag("success");
		}else {
			result.setFlag("fail");
			result.setData("航班计划不存在");
		}
		return result;
	}
	
	public static Result getTicketStatistics(String startDate, String endDate) {
		Result result = new Result();
		List<HashMap<String, Object>> list = dao.schedule.getTicketStatistics(startDate, endDate);
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			list.stream().map(item -> {
				String year = item.get("Year").toString();
				String month = item.get("Month").toString();
				month = Integer.parseInt(month)<10?"0"+month:month;
				item.remove("Year");
				item.remove("Month");
				item.put("Month", year + "-" + month);
				return item;
			}).collect(Collectors.toList());
			result.setData(list);
		}
		return result;
	}
}
