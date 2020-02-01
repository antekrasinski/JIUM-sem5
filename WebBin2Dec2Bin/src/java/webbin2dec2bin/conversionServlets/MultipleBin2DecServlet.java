package webbin2dec2bin.conversionServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webbin2dec2bin.model.Bin2DecConverterModel;
import webbin2dec2bin.model.CheckBin;
import webbin2dec2bin.model.NotBinaryNumberException;

/**
 * Class responsible for multiple bin2dec conversion, containing making sure if values are correct.
 * 
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet (urlPatterns = {"/MULTIPLEBIN2DEC"})
public class MultipleBin2DecServlet extends HttpServlet {

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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String first = request.getParameter("first");
        String second = request.getParameter("second");
        String third = request.getParameter("third");

        HttpSession session = request.getSession(true);

        Bin2DecConverterModel model;
        CheckBin check;

        Object mObj = session.getAttribute("b2dmodel");
        Object cObj = session.getAttribute("check");

        if (mObj == null) {
            model = new Bin2DecConverterModel();
            session.setAttribute("b2dmodel", mObj);
        } else {
            model = (Bin2DecConverterModel) mObj;
        }

        if (cObj == null) {
            check = new CheckBin();
            session.setAttribute("check", cObj);
        } else {
            check = (CheckBin) cObj;
        }

        List<String> binList = new ArrayList<>();
        boolean error = false;
        String result = "";

        binList.add(first);
        binList.add(second);
        binList.add(third);

        if ((Connection) this.getServletContext().getAttribute("databaseConnection") == null) {
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) {
                    System.err.println("Can't connect to database");
                }

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

        
        for (String element : binList) {
            try {
                check.checkBinaryNumber(element);
            } catch (NotBinaryNumberException exc) {
                error = true;
                try (PrintWriter out = response.getWriter()) {

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet ConversionServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>RESULT</h1>");
                    out.println("<h1>" + exc.getMessage() + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

            if (!error) {
                model.convertBin2Dec(element);
                result += "Bin: " + element;
                result += " Dec: " + (Integer.toString(model.getDec()) + "<br>");
                
                try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM WebConversions");

                if (!rs.next()) {
                    Cookie cookie = new Cookie("firstConversion", result);
                    response.addCookie(cookie);
                }

                statement.executeUpdate("INSERT INTO WebConversions (binary_value, decimal_value) VALUES('"
                        + element + "', '" + Integer.toString(model.getDec()) + "')");
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
                model.setDec(0);
            }
        }
        if (!error) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ConversionServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>RESULT</h1>");
                out.println("<h1>" + result + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet responsible for multiple bin2dec conversion.";
    }

}
