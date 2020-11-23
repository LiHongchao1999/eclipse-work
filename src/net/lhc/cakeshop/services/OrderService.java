package net.lhc.cakeshop.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.lhc.cakeshop.entitys.Cake;
import net.lhc.cakeshop.entitys.Order;
import net.lhc.cakeshop.utils.DBUtil;

public class OrderService {
	private List<Order> orders;
	private DBUtil dbUtil;
	
	
	public OrderService() {
		//初始化订单集合
		orders = new ArrayList<Order>();
		dbUtil = new DBUtil();
	}
	
	/**
	 * 根据信息构造订单对象
	 * @return 构造的订单对象
	 */
	private Order createOrder(int id, String userName, String time, String list,float totalPrice, int tag) {
		Order order = new Order();
		order.setId(id);
		order.setUserName(userName);
		order.setTime(time);
		order.setList(list);
		order.setTotalPrice(totalPrice);
		order.setTag(tag);
		return order;
	}
	
	/**
	 * 获取订单信息
	 * @param sql 查询订单的sql语句
	 * @return 订单集合
	 */
	public List<Order> getOrders(String sql){
		try {
			//查询订单
			ResultSet rs = dbUtil.queryDate(sql);
			while(rs.next()) {
				//获取订单表字段id的值
				int id = rs.getInt("id");
				//获取订单表字段userName的值
				String userName = rs.getString("userName");
				//获取订单表字段time的值
				String time = rs.getString("time");
				//获取订单表字段list的值
				String list = rs.getString("list");
				//获取订单表字段totalPrice的值
				float totalPrice = rs.getFloat("totalPrice");
				//获取订单表字段tag的值
				int tag = rs.getInt("tag");
				
				//根据获取到的订单信息构造订单对象
				Order order = createOrder(id, userName, time, list,totalPrice, tag);
				orders.add(order);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	
	/**
	 * 新增订单
	 * @param order 待添加的订单对象
	 * @return 添加订单是否成功，成功返回true，否则返回false
	 */
	public boolean addOrder(Order order) {
		//获取订单信息
		 String userName =order.getUserName();//下单用户
		 String time =order.getTime();//下单时间								
		 String lists = order.getList();//订单列表	
		 float totalPrice = order.getTotalPrice();//价格
		 int tag = order.getTag();//订单状态
		 System.out.println("获取到的信息:"+order.toString());
		//拼接插入订单的sql语句
		String sql = "insert into `order`(userName, totalPrice ,time ,tag ,list) "
				+ "values('" + userName + "', " + totalPrice + ",'" + time + "'," + tag + ", '" + lists + "')";
		
		System.out.println(sql);
		//将订单的信息插入订单表中
		int n = -1;//存储插入的记录数
		try {
			n = dbUtil.addDataToTable(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return n > 0 ? true : false;
	}
	
	/**
	 * 修改订单信息
	 * @param order 待修改的订单对象
	 * @return 修改订单是否成功，成功返回true，否则返回false
	 */
	public boolean updateOrder(Order order) {
		//获取订单信息
		int id = order.getId();//获取订单id	
		int tag = order.getTag();//订单状态		
		
		//拼接更新蛋糕的sql语句
		String sql = "update `order` set tag =" + tag + " where id ='" + id + "'";
		//将订单的信息插入订单表中
		int n = -1;//存储插入的记录数
		try {
			n = dbUtil.updateData(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return n > 0 ? true : false;
	}
	
	
	/**
	 * 根据id订单蛋糕
	 * @param id 蛋糕编号
	 * @return 是否删除蛋糕，成功删除返回true，否则返回false
	 */
	public boolean deleteOrderById(int id) {
		//拼接删除订单的sql语句
		String sql="delete from `order` where id='"+id+"'";
		//更新订单表
		int n = -1;//存储删除的记录数
		try {
			n = dbUtil.updateData(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return n > 0 ? true : false;
	}
	
	
	

}
