package net.lhc.cakeshop.forClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.lhc.cakeshop.entitys.Order;
import net.lhc.cakeshop.services.OrderService;


/**
 * Servlet implementation class SubmitOrderServlet
 */
@WebServlet("/submitOrder")
public class SubmitOrderByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Order order;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderByNameServlet() {
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
            System.out.println(stringBuffer);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //将json数据转为Order
        Gson gson = new Gson();
        order =gson.fromJson(stringBuffer.toString(), Order.class);
        System.out.println(order.toString());
        
      //调用OrderService类中addOrder方法访问数据库，并返回查询结果
        OrderService orderService = new OrderService();
        boolean b = orderService.addOrder(order);
        System.out.println("是否添加成功："+b);
        //添加成功，修改评论记录
        
        responseMessage = gson.toJson(b);
        
        System.out.println("对象转为json " + responseMessage);
        //输出流将信息返回
        out.print(responseMessage);
        
		
	}

}
