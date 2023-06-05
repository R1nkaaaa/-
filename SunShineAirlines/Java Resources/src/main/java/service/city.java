package service;

import java.util.HashMap;
import java.util.List;

import pojo.Result;

public class city {
	public static Result getCityNames() {
		Result result = new Result();
		List<HashMap<String, Object>> list =dao.city.getCityNames();
		if(list!=null && list.size()>0) {
			result.setFlag("success");
			result.setData(list);
		}
		return result;
	}
}
