package net.lhc.cakeshop.forClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.lhc.cakeshop.entitys.Cake;
import net.lhc.cakeshop.services.CakeService;


/**
 * Servlet implementation class SearchCakeServlet
 */
@WebServlet("/searchCake")
public class SearchCakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cakeName;
	private List<Cake> cakes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCakeServlet() {
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
        
        //将json数据转为String
        Gson gson = new Gson();
        cakeName =gson.fromJson(stringBuffer.toString(), String.class);
        cakeName = "%"+cakeName+"%";
        System.out.println( cakeName);
        
        //调用MenuService类中getUsers方法访问数据库，并返回查询结果
        CakeService cakeService = new CakeService();
        String sql = "select * from cake where name like '"+cakeName+"'";
        cakes = cakeService.getCakes(sql);
        System.out.println("sql:"+sql);
        int add = cakes.size();
        
        responseMessage = gson.toJson(cakes);
        
        System.out.println("对象转为json " + responseMessage);
        //输出流将信息返回
        out.print(responseMessage);
        
	}

}
