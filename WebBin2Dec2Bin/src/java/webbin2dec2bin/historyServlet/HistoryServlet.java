package webbin2dec2bin.historyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import servicesbin2dec2bin.dbconnection.HistorySer_Service;

/**
 * Class responsible for showing history of conversions using database.
 *
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/HISTORY"})
public class HistoryServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/HistorySer/HistorySer.wsdl")
    private HistorySer_Service service;

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
        boolean error = false;
        String history = "";
        try{
        history = getHistory();
        } catch (Exception e)
        {
            error = true;
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ConversionServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Exception: " + e);
                out.println("</body>");
                out.println("</html>");
            }
        }
        if(!error){
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

            out.println(history);
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

    /**
     * Method that gets history of conversions from History Service
     * @return history of conversions 
     */
    private String getHistory() {
        servicesbin2dec2bin.dbconnection.HistorySer_Service service = new servicesbin2dec2bin.dbconnection.HistorySer_Service();
        servicesbin2dec2bin.dbconnection.HistorySer port = service.getHistorySerPort();
        return port.getHistory();
    }
}
