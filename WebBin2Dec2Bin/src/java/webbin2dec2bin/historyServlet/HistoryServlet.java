package webbin2dec2bin.historyServlet;

import webbin2dec2bin.model.History;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class responsible for showing history of conversions.
 * 
 * @author Antoni Krasinski
 * @version 1.0
 */
@WebServlet (urlPatterns = {"/HISTORY"})
public class HistoryServlet extends HttpServlet {
    
    
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
        HttpSession session = request.getSession(true);

        Object object = session.getAttribute("history");
        History history;

        if (object == null) {
            history = new History();
            session.setAttribute("history", history);
        } else {
            history = (History) object;
        }

        List<String> listOfConversions = history.getHistory();

        try (PrintWriter out = response.getWriter()) {
            if (listOfConversions.isEmpty()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");
                out.println("<h1>History of conversions is empty</h1>");
                out.println("</body>");
                out.println("</html>");
            } else {
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

                for (int i = 0; i < listOfConversions.size(); i++) {
                    out.println("<h1>" + listOfConversions.get(i) + "</h1>");
                }
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
}
