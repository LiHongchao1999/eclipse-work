package net.lhc.cakeshop.forClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.lhc.cakeshop.entitys.Order;
import net.lhc.cakeshop.services.OrderService;

/**
 * Servlet implementation class GetUserOrderListServlet
 */
@WebServlet("/getUserOrderList")
public class GetUserOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Order> orders;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserOrderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 设置编码方式
				request.setCharacterEncoding("utf-8");
				//设置返回数据格式和编码
		        response.setContentType("application/json;charset=utf-8");
		        
		        //定义StringBuffer变量
		      	StringBuffer stringBuffer = new StringBuffer();
		        //line保存读取请求信息的当前一行，responseMessage为响应信息，返回信息
		        String line = null, responseMessage = null;
		        
		        //输出流
		        PrintWriter out = response.getWriter();
		        
		        //读取信息时会发生IO异常
		        try{
		            //BufferedReader为缓冲读取流
		            BufferedReader bufferedReader = request.getReader();
		            while((line = bufferedReader.readLine()) != null){
		                stringBuffer.append(line);
		            }
		            
		        }catch(IOException e){
		            e.printStackTrace();
		        }
		        
		        
		        //将json数据转为UserPhoto
		        Gson gsons = new Gson();
		        String userName = gsons.fromJson(stringBuffer.toString(), String.class);
        
        
        //调用MenuService类中getUsers方法访问数据库，并返回查询结果
        OrderService orderService = new OrderService();
        orders = orderService.getOrders("select * from `order` where userName = " +userName);
        System.out.println("666"+Arrays.asList(orders));
        Gson gson = new Gson();
        responseMessage = gson.toJson(orders);
        
        System.out.println("对象转为json " + responseMessage);
        //输出流将信息返回
        out.print(responseMessage);
        
	}

}
