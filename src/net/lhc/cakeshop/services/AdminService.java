package net.lhc.cakeshop.services;

import java.sql.SQLException;

import net.lhc.cakeshop.entitys.Admin;
import net.lhc.cakeshop.entitys.User;
import net.lhc.cakeshop.utils.DBUtil;

public class AdminService {

	private DBUtil dbUtil;
	
	public AdminService() {
		dbUtil = new DBUtil();
	}
	
	
	
	/**
	 * 判断管理员是否存在
	 * @param admin 待判断的管理员
	 * @return 存在则返回true，否则返回false
	 */
	public boolean isExistAdmin(Admin admin) {
		//获取待判断的管理员信息
		String aName = admin.getaName();
		String aPwd = admin.getaPwd();
		//根据管理员信息拼接sql语句
		String sql = "select * from admin where aName = '" + aName +"' and aPwd = '" + aPwd +"'";
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
