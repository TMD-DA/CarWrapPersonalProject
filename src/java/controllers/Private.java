package controllers;

import business.User;
import business.Validation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Private extends HttpServlet {

    String url = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger LOG = Logger.getLogger(Public.class.getName());
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("Public");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }
        // possibly change away from the switch case structure to an if structure.
        switch (action) {

            case "logout": {
                logout(request);

                break;
            }
            case "viewEstimates": {
                url = "/viewEstimates.jsp";
                
                break;
            }
            case "viewReivews": {
                url = "/viewReviews.jsp";
                
                break;
            }
            case "gotoReview": {
                url = "/submitReview.jsp";
                
                break;
            }
            case "gotoUpdatePage": {
                url = "/updateUser.jsp";
                
                break;
            }
            case "updateUser": {
                
                
                break;
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        url = "/Public?action=gotoIndex";
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
