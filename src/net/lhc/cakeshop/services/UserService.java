package net.lhc.cakeshop.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.lhc.cakeshop.entitys.User;
import net.lhc.cakeshop.utils.DBUtil;

public class UserService {

	private DBUtil dbUtil;
	
	public UserService() {
		dbUtil = new DBUtil();
	}
	
	
	
	
	/**
	 * 根据用户信息构造用户对象
	 * @return 构造的用户对象
	 */
	
	
//	private User createUser(String uName, String uPwd) {
//		User user = new User();
//		user.setuName(uName);
//		user.setuPwd(uPwd);
//		return user;
//	}
	
	
//	/**
//	 * 添加用户
//	 * @param book 待添加的用户对象
//	 * @return 添加用户是否成功，成功返回true，否则返回false
//	 */
//	public boolean addUser(User user) {
//		//获取用户信息
//		
//		String uName =user.getuName();
//        String uPwd =user.getuPwd();
//        String sex =user.getSex();
//		
//		//拼接插入用户的sql语句
//		String sql = "insert into userinfo(uName, uPwd ,sex) values('" + uName + "', '" + uPwd + "','"+ sex + "')";
//		//将图书的信息插入图书表中
//		int n = -1;//存储插入的记录数
//		try {
//			n = dbUtil.addDataToTable(sql);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0 ? true : false;
//	}
//	
//	/**
//	 * 修改用户信息
//	 * @param
//	 * @return 修改用户资料是否成功，成功返回true，否则返回false
//	 */
//	public boolean updateUserInfo(String sql) {
//		//将用户的信息插入图书表中
//		int n = -1;//存储插入的记录数
//		try {
//			n = dbUtil.addDataToTable(sql);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0 ? true : false;
//	}
//		
//				
//			
//	
//	
//	
//	
//	/**
//	 * 根据用户名删除指定用户
//	 * @param uName 用户名
//	 * @return 是否删除用户，成功删除返回true，否则返回false
//	 */
//	public boolean deleteUserByUname(String uName) {
//		//拼接删除用户的sql语句
//		String sql="delete from UserInfo where uName='"+uName+"'";
//		//更新用户
//		int n = -1;//存储删除的记录数
//		try {
//			n = dbUtil.updateData(sql);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return n > 0 ? true : false;
//	}
	
	
	
	/**
	 * 判断用户是否存在
	 * @param user 待判断的用户
	 * @return 存在则返回true，否则返回false
	 */
	public boolean isExistUser(User user) {
		//获取待判断的用户信息
		String uName = user.getuName();
		String uPwd = user.getuPwd();
		//根据用户信息拼接sql语句
		String sql = "select * from user where uName = '" + uName +"' and uPwd = '" + uPwd +"'";
		System.out.println(sql);
		boolean b = false;
		try {
			b = dbUtil.isExist(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	
	
	

}
