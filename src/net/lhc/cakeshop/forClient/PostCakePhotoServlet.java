package net.lhc.cakeshop.forClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation class PostCakePhotoServlet
 */
@WebServlet("/postCakePhoto")
public class PostCakePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int num = 1;
	private String address;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostCakePhotoServlet() {
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
        Gson gson = new Gson();
        String photoPath = gson.fromJson(stringBuffer.toString(), String.class);
        
        
      //获取服务器根目录
        address = getServletContext().getRealPath("/");
        System.out.println(address);
        
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyyMMddHHmmss");// a为am/pm的标记  
        Date date = new Date();// 获取当前时间 
        System.out.println(sdf.format(date));
        String path ="./images/"+sdf.format(date)+ ".jpg";
        //将用户头像存入到服务器中
    	File file = new File(address + path);
    	try {
    		OutputStream outputStream = new FileOutputStream(file);
    		byte[] data = Base64.getMimeDecoder().decode(photoPath);
    		outputStream.write(data);
    		outputStream.flush();
    		outputStream.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    		path = "";
    	}
    	num++;
		
        //将查询结果转为json，响应
        responseMessage = gson.toJson(path);
        System.out.println("对象转为json " + responseMessage);
        //输出流将信息返回
        out.print(responseMessage);
	}

}
