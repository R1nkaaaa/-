package dao;

import java.util.HashMap;
import java.util.List;

import helper.mysql;

public class city {
	public static List<HashMap<String, Object>> getCityNames() {
		List<HashMap<String, Object>> list = null;
		String sql = "select * from city";
		list = mysql.query(sql, null);
		return list;
	}
}
