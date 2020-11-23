package net.lhc.cakeshop.forClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.lhc.cakeshop.entitys.Cake;
import net.lhc.cakeshop.entitys.Search;
import net.lhc.cakeshop.services.CakeService;


/**
 * Servlet implementation class FilterCakeBySizeAndPriceServlet
 */
@WebServlet("/filterCake")
public class FilterCakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Cake> cakes = new ArrayList<Cake>();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterCakeServlet() {
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
        Search search = new Search();
        search =gson.fromJson(stringBuffer.toString(), Search.class);
        System.out.println( search.toString());
        
        String sql = null;
        String size1 = search.getSize1();
        String size2 = search.getSize2();
        String price1 = search.getPrice1();
        String price2 = search.getPrice2();
        if (price1.equals("") && price2.equals("") && !size1.equals("") && !size2.equals("")) {
			sql = "select * from cake where size between "+size1+" and "+size2+" order by size";
		}else if(!price1.equals("") && !price2.equals("") && size1.equals("") && size2.equals("")){
			sql = "select * from cake where price between "+price1+" and "+price2+" order by price";
		}else if(!price1.equals("") && !price2.equals("") && !size1.equals("") && !size2.equals("")){
			sql = "select * from cake where price between "+price1+" and "+price2+" and size between "+size1+" and "+size2+" order by price";
		}
        
        
        
        
        
        //调用CakeService类中getUsers方法访问数据库，并返回查询结果
        CakeService cakeService = new CakeService();
        
        cakes = cakeService.getCakes(sql);
        
        
        
        
        responseMessage = gson.toJson(cakes);
        
        System.out.println("对象转为json " + responseMessage);
        //输出流将信息返回
        out.print(responseMessage);
        
        
	}

}
