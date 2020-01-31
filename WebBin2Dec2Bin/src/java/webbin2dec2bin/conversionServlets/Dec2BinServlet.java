package webbin2dec2bin.conversionServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webbin2dec2bin.model.Dec2BinConverterModel;
import webbin2dec2bin.model.History;

/**
 * Class responsible for dec2bin conversion.
 * 
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet (urlPatterns = {"/DEC2BIN"})
public class Dec2BinServlet extends HttpServlet {

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

        Object mObj = session.getAttribute("d2bmodel");

        if (mObj == null) {
            model = new Dec2BinConverterModel();
            session.setAttribute("d2bmodel", mObj);
        } else {
            model = (Dec2BinConverterModel) mObj;
        }
        if(dec != ""){
        model.convertDec2Bin(Integer.parseInt(dec));

        result = "Bin: " + model.getBin() + " Dec: " + dec;
        }
        result = "Bin: 0 Dec: 0";
        Object object = session.getAttribute("history");
        History history;

        if (object == null) {
            history = new History();
            session.setAttribute("history", history);
            Cookie cookie = new Cookie("firstConversion", result);
            response.addCookie(cookie);
        } else {
            history = (History) object;
        }
        history.addToHistory(result);

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
