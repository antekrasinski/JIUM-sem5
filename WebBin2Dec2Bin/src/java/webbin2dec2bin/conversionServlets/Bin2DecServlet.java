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
import webbin2dec2bin.model.Bin2DecConverterModel;
import webbin2dec2bin.model.CheckBin;
import webbin2dec2bin.model.History;
import webbin2dec2bin.model.NotBinaryNumberException;

/**
 * Class responsible for bin2dec conversion, containing making sure if values are correct.
 * 
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet (urlPatterns = {"/BIN2DEC"})
public class Bin2DecServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);

        boolean error = false;

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

        try {
            check.checkBinaryNumber(bin);
        } catch (NotBinaryNumberException e) {
            error = true;
            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ConversionServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>RESULT</h1>");
                out.println("<h1>" + e.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
       
        if (!error) {
            model.convertBin2Dec(bin);
            String result = "Bin: " + bin + " Dec: " + Integer.toString(model.getDec());
           
            Object hObj = session.getAttribute("history");
            History history;
           
            if (hObj == null) {
                
                history = new History();
                session.setAttribute("history", history);
                
                Cookie cookie = new Cookie("firstConversion", result);
                response.addCookie(cookie);
            } else {
                history = (History) hObj;
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
}
