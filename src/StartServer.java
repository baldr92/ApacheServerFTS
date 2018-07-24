

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.stream.Collectors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;

import oracle.sql.CLOB;

/**
 * Servlet implementation class StartServer
 */
@WebServlet("/StartServer")
public class StartServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StartServer() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			InitialContext initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/DBTEST");
			Connection conn =ds.getConnection();
			
			//CLOB clob = CLOB.createTemporary(conn, true, CLOB.DURATION_SESSION);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		//request.getRequestDispatcher("").forward(request, response);
		//String path1 = "http://localhost:8080/ApacheServerFTS/StartServer/Cnfrm";
		//URL	 url1 = new URL(path1);
		
		//String link = request.getRequestURI();		
		//System.out.println(link);		
		//XMLCreator xml = new XMLCreator(); 
		//xml.chooseXML(link);		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//int link1 = request.getContentLength();
		BufferedReader bf = new BufferedReader(request.getReader());		
		String line = bf.readLine();
		bf.close();		
		//System.out.println(line);
		XMLCreator creator = new XMLCreator();
		try {
		response.getWriter().append(creator.chooseXML(line));
		} catch (Exception pe) {
			pe.printStackTrace();
		}
	}
	
	

}
