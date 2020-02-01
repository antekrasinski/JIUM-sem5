package webbin2dec2bin.historyServlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class responsible for showing history of conversions using database.
 *
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/HISTORY"})
public class HistoryServlet extends HttpServlet {

    /**
     * Containing information about connection with database.
     */
    private Connection connection;

    /**
     * Handles the HTTP GET method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        
        if ((Connection) this.getServletContext().getAttribute("databaseConnection") == null) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.println("Can't connect to database");
            }
            
            /**
            * try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Conversions", "Krasinski", "Krasinski")) {
                Statement statement = con.createStatement();
                statement.executeUpdate("CREATE TABLE WebConversions "
                + "(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), binary_value VARCHAR(50), decimal_value VARCHAR(50), PRIMARY KEY (id)) ");
             
            System.out.println("Table created");
            } catch (SQLException sqle) {
                System.err.println(sqle.getMessage());
            }
            */
            
            try {
                String url = this.getServletContext().getInitParameter("url");
                String username = this.getServletContext().getInitParameter("username");
                String password = this.getServletContext().getInitParameter("password");

                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException sqle) {
                connection = null;
                System.err.println("Can't connect to database!");
            }

            this.getServletContext().setAttribute("databaseConnection", connection);
        } else {
            connection = (Connection) this.getServletContext().getAttribute("databaseConnection");
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");

            Cookie[] cookies = request.getCookies();
            String firstConversion = "";

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("firstConversion")) {
                    firstConversion = cookie.getValue();
                }
            }

            if (!firstConversion.equals("")) {
                out.println("<h1>First conversion: " + firstConversion + "<h1>");
            }

            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM WebConversions");
                if(!rs.next())
                    out.println("History of conversions is empty.");
                while (rs.next()) {
                    out.println(rs.getString(1) + ") BIN: " + rs.getString(2) + " DEC: " + rs.getString(3) + "<br>");
                }
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }

            out.println("</body>");
            out.println("</html>");

        }

    }

    /**
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet responsible for showing history.";
    }
}
