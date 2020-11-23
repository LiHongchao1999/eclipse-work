package net.lhc.cakeshop.services;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.lhc.cakeshop.entitys.Cake;
import net.lhc.cakeshop.utils.DBUtil;


public class CakeService {
	private List<Cake> cakes;
	private DBUtil dbUtil;
	
	
	public CakeService() {
		//初始化蛋糕集合
		cakes = new ArrayList<Cake>();
		dbUtil = new DBUtil();
	}
	
	/**
	 * 根据信息构造蛋糕对象
	 * @return 构造的蛋糕对象
	 */
	private Cake createCake(int id, String name, Float price, int size, String image, String description, int stock) {
		Cake cake = new Cake();
		cake.setId(id);
		cake.setName(name);
		cake.setPrice(price);
		cake.setSize(size);
		cake.setImage(image);
		cake.setDescription(description);
		cake.setStock(stock);
		return cake;
	}
	
	/**
	 * 获取蛋糕信息
	 * @param sql 查询用户的sql语句
	 * @return 蛋糕集合
	 */
	public List<Cake> getCakes(String sql){
		try {
			//查询用户
			ResultSet rs = dbUtil.queryDate(sql);
			while(rs.next()) {
				//获取蛋糕表字段id的值
				int id = rs.getInt("id");
				//获取蛋糕表字段name的值
				String name = rs.getString("name");
				//获取蛋糕表字段price的值
				Float price = rs.getFloat("price");
				//获取蛋糕表字段size的值
				int size = rs.getInt("size");
				//获取蛋糕表字段image的值
				String image = rs.getString("image");
				//获取蛋糕表字段description的值
				String description = rs.getString("description");
				//获取蛋糕表字段stock的值
				int stock = rs.getInt("stock");
				
				//根据获取到的蛋糕信息构造蛋糕对象
				Cake cake = createCake(id, name, price, size, image, description, stock);
				cakes.add(cake);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cakes;
	}
	
	
	/**
	 * 新增蛋糕
	 * @param cake 待添加的蛋糕对象
	 * @return 添加蛋糕是否成功，成功返回true，否则返回false
	 */
	public boolean addCake(Cake cake) {
		//获取蛋糕信息
		 String name =cake.getName();//蛋糕名称
		 Float price =cake.getPrice();//蛋糕价格				
		 int size = cake.getSize();//蛋糕尺寸				
		 String image = cake.getImage();//蛋糕图片					
		 String description = cake.getDescription();//蛋糕描述	
		 int stock = cake.getStock();//蛋糕库存
		//拼接插入菜谱的sql语句
		String sql = "insert into `cake`(name, price ,size ,image,description,stock) "
				+ "values('" + name + "', '" + price + "',"
						+ " '" + size + "', '" + image + "','"+ description + "','"+ stock + "')";
		//将蛋糕的信息插入蛋糕表中
		int n = -1;//存储插入的记录数
		try {
			n = dbUtil.addDataToTable(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return n > 0 ? true : false;
	}
	
	/**
	 * 修改蛋糕信息
	 * @param cake 待修改的蛋糕对象
	 * @return 修改蛋糕是否成功，成功返回true，否则返回false
	 */
	public boolean updateCake(Cake cake) {
		//获取蛋糕信息
		int id = cake.getId();//获取蛋糕id	
		String name =cake.getName();//蛋糕名称
		Float price =cake.getPrice();//蛋糕价格				
		int size = cake.getSize();//蛋糕尺寸				
		//String image = cake.getImage();//蛋糕图片					
		String description = cake.getDescription();//蛋糕描述	
		int stock = cake.getStock();//蛋糕库存				
		
		//拼接更新蛋糕的sql语句
		String sql = "update cake set name ='" + name + "',"
				+ "price ='" + price + "',size ='" + size + "',"
						+ "description ='" + description + "',"
								+ "stock ='" + stock + "' where id ='" + id + "'";
		
		System.out.println("这是sql语句："+sql);
		//将蛋糕的信息插入蛋糕表中
		int n = -1;//存储修改的记录数
		try {
			n = dbUtil.updateData(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return n > 0 ? true : false;
	}
	
	
	/**
	 * 根据id删除蛋糕
	 * @param id 蛋糕编号
	 * @return 是否删除蛋糕，成功删除返回true，否则返回false
	 */
	public boolean deleteCakeById(int id) {
		//拼接删除蛋糕的sql语句
		String sql="delete from cake where id='"+id+"'";
		//更新蛋糕表
		int n = -1;//存储删除的记录数
		try {
			n = dbUtil.updateData(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return n > 0 ? true : false;
	}
	
	
	

}
