package webbin2dec2bin.conversionServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import servicesbin2dec2bin.conversionsservice.Conversions_Service;

/**
 * Class responsible for bin2dec conversion, containing making sure if values are correct.
 * 
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet (urlPatterns = {"/BIN2DEC"})
public class Bin2DecServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Conversions/Conversions.wsdl")
    private Conversions_Service service;

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

        String bin = request.getParameter("bin");
        String dec = "";
        boolean error = false;
        try {
            dec = bin2Dec(bin);
        } catch (Exception e) {
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

        if (!error) {
        String result = "Bin: " + bin + " Dec: " + dec;
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
        return "Servlet responsible for bin2dec conversion.";
    }

    /**
     * Method that sends binary value to be converted in Conversions Service 
     * and gets the result of conversion.
     * @param bin - binary value to be converted
     * @return - result of conversion
     */
    private String bin2Dec(java.lang.String bin) {
        servicesbin2dec2bin.conversionsservice.Conversions_Service service = new servicesbin2dec2bin.conversionsservice.Conversions_Service();
        servicesbin2dec2bin.conversionsservice.Conversions port = service.getConversionsPort();
        return port.bin2Dec(bin);
    }
}
