package dao;

import java.util.HashMap;
import java.util.List;

import helper.mysql;

public class user {
	public static List<HashMap<String, Object>> findByEmailAndPassword(String email, String password) {
		List<HashMap<String, Object>> list = null;
		String sql = "select UserId,Email,FirstName,LastName,RoleId from users where email = ? and password = ?";
		list = mysql.query(sql, new Object[] { email, password });
		return list;
	}

	public static List<HashMap<String, Object>> findByEmail(String email) {
		List<HashMap<String, Object>> list = null;
		String sql = "select * from users where email = ?";
		list = mysql.query(sql, new Object[] { email });
		return list;
	}

	public static List<HashMap<String, Object>> findUserList(int roleId, String name, int startPage, int pageSize) {
		List<HashMap<String, Object>> list = null;
		String sql1 = "select * from users where RoleId = ? and (FirstName like ? or LastName like ?) limit ?,?";
		String sql2 = "select * from users where FirstName like ? or LastName like ? limit ?,?";
		if (roleId != 0) {
			list = mysql.query(sql1,
					new Object[] { roleId, "%" + name + "%", "%" + name + "%", (startPage - 1) * pageSize, pageSize });
		} else {
			list = mysql.query(sql2,
					new Object[] { "%" + name + "%", "%" + name + "%", (startPage - 1) * pageSize, pageSize });
		}
		return list;
	}
	
	public static int findUserListCount(int roleId, String name) {
		List<HashMap<String, Object>> list = null;
		String sql1 = "select count(*) as Total from users where RoleId = ? and (FirstName like ? or LastName like ?)";
		String sql2 = "select count(*) as Total from users where FirstName like ? or LastName like ?";
		if (roleId != 0) {
			list = mysql.query(sql1, new Object[] { roleId, "%" + name + "%", "%" + name + "%" });
		} else {
			list = mysql.query(sql2, new Object[] { "%" + name + "%", "%" + name + "%" });
		}
		int count = Integer.parseInt(list.get(0).get("Total").toString());
		return count;
	}
	
	public static int addUser(String email, String firstName, String lastName, String password, String gender,
			String dateOfBirth, String phone, String photo, String address, int roleId) {
		String sql = "insert into users(Email,FirstName,LastName,Password,Gender,DateOfBirth,Phone,Photo,Address,RoleId) values(?,?,?,?,?,?,?,?,?,?)";
		int i = mysql.update(sql, new Object[] { email, firstName, lastName, password, gender, dateOfBirth, phone,
				photo, address, roleId });
		return i;
	}
	
	public static int updatePassword(int userId, String password) {
		String sql = "update users set Password = ? where UserId = ?";
		int i = mysql.update(sql, new Object[] { password, userId });
		return i;
	}
	
}
