 import java.io.PrintWriter;
import java.io.Writer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.sf.json.JSONArray;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class topic{
	public topic() {
		super();
	}
	public topic(String title, String content) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.content = content;
	}
	String title;
	String content;
}

/**
 * Servlet implementation class ServletDemo1
 */
@WebServlet("/ServletDemo1")
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static  final String dbdriver="com.mysql.cj.jdbc.Driver";
	List l = new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("utf-8");
		    response.setContentType("text/html;charset=utf-8");          	        
	        response.setHeader("Access-Control-Allow-Origin", "*");   
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	       
	        String title = request.getParameter("title");	      
	        String content = request.getParameter("content");
	        String accesstoken = request.getParameter("accesstoken");
	        title=new String(title.getBytes("iso-8859-1"),"utf-8");
	        content=new String(content.getBytes("iso-8859-1"),"utf-8");
	        System.out.println(title+" "+content+" "+accesstoken);
	        	              
	        Connection conn=null;
			Statement stmt=null;
			ResultSet  rt=null;
			
			try{
				Class.forName(dbdriver);
				
			}catch(ClassNotFoundException  e){
				e.printStackTrace();
			}
			
			try{
				conn=DriverManager.getConnection( "jdbc:mysql://localhost:3306/datatest?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&user=root&password=123456&useSSL=true&serverTimezone=UTC&&useSSL=false&allowPublicKeyRetrieval=true\r\n");
				stmt=conn.createStatement();
				stmt.executeUpdate("INSERT INTO datatest(title,content) VALUES('"+ title +"','"+content+"')");
				System.out.println("数据插入成功");			 
			}catch(SQLException e){
				e.printStackTrace();
			}
		
			try{
				stmt.close();
				conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
			}

			l.add(0,new topic(title,content));
			System.out.println(l);
			Gson gson = new Gson();
			String json = gson.toJson(l);
			System.out.println(json);	
			 //返回值给微信小程序			
	        Writer out = response.getWriter(); 
	        out.write(json);
	        out.flush();  

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
