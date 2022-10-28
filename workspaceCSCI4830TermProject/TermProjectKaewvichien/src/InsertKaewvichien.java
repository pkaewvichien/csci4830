
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertKaewvichien")
public class InsertKaewvichien extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertKaewvichien() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String hobbyName = request.getParameter("hobbyName");
      String genre = request.getParameter("genre");
      //String phone = request.getParameter("phone");

      Connection connection = null;
      String insertSql = " INSERT INTO hobbyPk (id, hobby, genre) values (default, ?, ?)";

      try {
         DBConnectionKaewvichien.getDBConnection();
         connection = DBConnectionKaewvichien.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, hobbyName);
         preparedStmt.setString(2, genre);
         //preparedStmt.setString(3, phone);
        
         preparedStmt.execute();
         preparedStmt.close();
         connection.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Hobby Name</b>: " + hobbyName + "\n" + //
            "  <li><b>Genre</b>: " + genre + "\n" + //
            

            "</ul>\n");

      out.println("<a href=/TermProjectKaewvichien/homeGen.html>Generate Hobby</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
