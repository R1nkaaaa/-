package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mysql {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/session1?serverTimezone=GMT%2B8&useOldAliasMetadataBehavior=true";
	private static final String user = "root";
	private static final String password = "123456";

	private static ResultSet rs = null;
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<HashMap<String, Object>> query(String sql, Object[] para) {
		List<HashMap<String, Object>> list = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			pst = conn.prepareStatement(sql);
			if (para != null) {
				for (int i = 0; i < para.length; i++) {
					String className = para[i].getClass().getName();
					if (className.contains("String")) {
						pst.setString(i + 1, para[i].toString());
					} else if (className.contains("Integer")) {
						pst.setInt(i + 1, Integer.parseInt(para[i].toString()));
					}
				}
			}
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			list = new ArrayList<HashMap<String, Object>>();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					String key = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(i + 1);
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public static int update(String sql, Object[] para) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			pst = conn.prepareStatement(sql);
			if (para != null) {
				for (int i = 0; i < para.length; i++) {
					String className = para[i].getClass().getName();
					if (className.contains("String")) {
						pst.setString(i + 1, para[i].toString());
					} else if (className.contains("Integer")) {
						pst.setInt(i + 1, Integer.parseInt(para[i].toString()));
					}
				}
			}
			result = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
