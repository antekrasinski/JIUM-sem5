package webbin2dec2bin.conversionServlets;

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
import javax.servlet.http.HttpSession;
import webbin2dec2bin.model.Dec2BinConverterModel;

/**
 * Class responsible for dec2bin conversion.
 * 
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet (urlPatterns = {"/DEC2BIN"})
public class Dec2BinServlet extends HttpServlet {

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

        String dec = request.getParameter("dec");
        HttpSession session = request.getSession(true);

        Dec2BinConverterModel model;

        String result = "";
        String bin = "0";

        Object mObj = session.getAttribute("d2bmodel");

        if (mObj == null) {
            model = new Dec2BinConverterModel();
            session.setAttribute("d2bmodel", mObj);
        } else {
            model = (Dec2BinConverterModel) mObj;
        }

        if (!dec.isEmpty()) {
            model.convertDec2Bin(Integer.parseInt(dec));
            bin = model.getBin();
            result = "Bin: " + model.getBin() + " Dec: " + dec;
        } else {
            dec = "0";
            result = "Bin: 0 Dec: 0";
        }

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

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM WebConversions");

            if (!rs.next()) {
                Cookie cookie = new Cookie("firstConversion", result);
                response.addCookie(cookie);
            }

            statement.executeUpdate("INSERT INTO WebConversions (binary_value, decimal_value) VALUES('"
                    + bin + "', '" + dec + "')");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

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
        return "Servlet responsible for dec2bin conversion.";
    }

}
