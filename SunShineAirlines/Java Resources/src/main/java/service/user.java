package service;

import java.util.HashMap;
import java.util.List;

import pojo.Page;
import pojo.Result;

public class user {
	public static Result login(String email, String password) {
		Result result = new Result();
		List<HashMap<String, Object>> list = dao.user.findByEmailAndPassword(email, password);
		if(list!=null && list.size()>0) {
			result.setFlag("success");
			result.setData(list.get(0));
		}else {
			result.setFlag("fail");
			list = dao.user.findByEmail(email);
			if(list!=null && list.size()>0) {
				result.setData("密码错误");
			}else {
				result.setData("邮箱不存在");
			}
		}
		return result;
	}
	
	public static Result userList(int roleId, String name, int startPage, int pageSize) {
		Result result = new Result();
		List<HashMap<String, Object>> list = dao.user.findUserList(roleId, name, startPage, pageSize);
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
			int total = dao.user.findUserListCount(roleId, name);
			Page page = new Page(startPage,pageSize,total);
			result.setPage(page);
		} else {
			result.setFlag("fail");
		}
		return result;
	}
	
	public static Result addUser(String email, String firstName, String lastName, String password, String gender,
			String dateOfBirth, String phone, String photo, String address, int roleId) {
		Result result = new Result();
		List<HashMap<String, Object>> list =dao.user.findByEmail(email);
		if(list!=null && list.size()>0) {
			result.setFlag("fail");
			result.setData("邮箱重复");
		}else {
			int i = dao.user.addUser(email, firstName, lastName, password, gender, dateOfBirth,
					phone, photo, address, roleId);
			if (i > 0) {
				result.setFlag("success");
			} else {
				result.setFlag("fail");
			}
		}
		return result;
	}
	
	public static Result updatePassword(int userId, String password) {
		Result result = new Result();
		int i =dao.user.updatePassword(userId,password);
		if(i>0) {
			result.setFlag("success");
		}else {
			result.setFlag("fail");
			result.setData("用户信息不存在");
		}
		return result;
	}
}
