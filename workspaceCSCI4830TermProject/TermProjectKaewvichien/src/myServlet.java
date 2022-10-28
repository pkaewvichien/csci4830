import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class myServlet
 */
@WebServlet("/myServlet")
public class myServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 static String url = "jdbc:mysql://ec2-3-86-206-245.compute-1.amazonaws.com:3306/Termpk";
	 static String user = "pk_remote";
	 static String password = "password1234";
	 static Connection connection = null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		String title = "Hobby Result!";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType + //
				"<html>\n" +//
				"<head><title>" + title + "</title></head>\n" + //
				"<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	      //response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
	      Random numRan = new Random();
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         
	      } catch (ClassNotFoundException e) {
	         //System.out.println("Where is your MySQL JDBC Driver?");
	         e.printStackTrace();
	         return;
	      }
	      //response.getWriter().println("MySQL JDBC Driver Registered!<br>");
	      connection = null;
	      try {
	         connection = DriverManager.getConnection(url, user, password);
	      } catch (SQLException e) {
	         //System.out.println("Connection Failed! Check output console");
	         e.printStackTrace();
	         return;
	      }
	      if (connection != null) {
	         //response.getWriter().println("You made it, take control your database now!<br>");
	      } else {
	         //System.out.println("Failed to make connection!");
	      }
	      try {
	         String selectSQL = "SELECT * FROM hobbyPk";
	         //String theUserName = "us%";
	         //response.getWriter().println(selectSQL + "<br>");
	         //response.getWriter().println("------------------------------------------<br>");
	         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
	         //preparedStatement.setString(1, theUserName);
	         ResultSet rs = preparedStatement.executeQuery();
	         String[] arr = new String[100];
	         int i = 0;
	         Random rn = new Random();
	         while (rs.next()) {
	        	 String id = rs.getString("id");
                 String hobby = rs.getString("hobby");
                 String genre = rs.getString("genre");
                 //response.getWriter().append("USER ID: " + id + ", ");
                 //response.getWriter().append("Hobby: " + hobby + ", ");
                 //response.getWriter().append("Genre: " + genre + "<br>");
                 //response.getWriter().append("USER PHONE: " + phone + "<br>");
                 arr[i++] = id;
                 arr[i++] = hobby;
                 arr[i++] = genre;  
	         }
	         int numrand = rn.nextInt(i/3) * 3;
	         response.getWriter().append("Hobby: " + arr[numrand + 1]);
	         response.getWriter().append(", Indoor/Outdoor: " + arr[numrand + 2] + "<br>");
	         out.println("<a href=/TermProjectKaewvichien/homeGen.html><button type=\"button\">Back</button></a> <br>");
	         out.println("</body></html>");
	         preparedStatement.close();
	         connection.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      
	      
	      
	   }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
