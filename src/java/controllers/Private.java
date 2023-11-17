package controllers;

import business.User;
import business.Validation;
import data.WrapDB;
import data.security.SecurityUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                try {
                    String newEmail = request.getParameter("email");
                    String newUsername = request.getParameter("username");
                    String newPassword = request.getParameter("password");
                    String verifyPassword = request.getParameter("verifypassword");
                    String newPhone = request.getParameter("phone");
                    List<String> errors = new ArrayList();

                    if (!newEmail.equals("")) {
                        if (!Validation.isEmail(newEmail)) {
                            errors.add("The email you entered is not a valid format. A valid format looks like this: example@somesite.com");
                        }

                        if (!Validation.isValidEmail(newEmail)) {
                            loggedInUser.setEmail(newEmail);
                            try {
                                WrapDB.updateUserEmail(loggedInUser);
                            } catch (SQLException e) {
                                Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } else {
                            errors.add("Email is already tied to an account please use a different email address.");
                        }
                    }

                    if (!newUsername.equals("")) {
                        if (Validation.isValidUsername(newUsername)) {
                            loggedInUser.setUsername(newUsername);
                            try {
                                WrapDB.updateUserUsername(loggedInUser);
                            } catch (SQLException e) {
                                Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } else {
                            errors.add("The username is already taken.");
                        }
                    }

                    if (!newPhone.equals("")) {
                        newPhone = Validation.formatPhoneNumber(newPhone);

                        if (Validation.isValidPhoneNumber(newPhone)) {
                            loggedInUser.setPhone(newPhone);
                            try {
                                WrapDB.updateUserPhone(loggedInUser);
                            } catch (SQLException e) {
                                Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    } else {
                        errors.add("The phone number you entered is invalid please re-enter phone number.");
                    }

                    if (!newPassword.equals("") && !verifyPassword.equals("")) {
                        if (!newPassword.equals(verifyPassword)) {
                            errors.add("The passwords don't match please re-enter new password.");
                        } else if (!Validation.isValidPassword(newPassword)) {
                            errors.add("Passwords must be longer than 10 characters");
                        } else {
                            newPassword = SecurityUtil.hashPassword(newPassword);

                            loggedInUser.setPassword(newPassword);
                            try {
                                WrapDB.updateUserPassword(loggedInUser);
                            } catch (SQLException e) {
                                Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    } else if (!newPassword.equals("") && verifyPassword.equals("")) {
                        errors.add("You must enter re-enter your password.");
                    } else if (newPassword.equals("") && !verifyPassword.equals("")) {
                        errors.add("You must enter your new password if you want to change it.");
                    }

                    if (errors.isEmpty()) {
                        url = "/userPage.jsp";
                    } else {
                        request.setAttribute("errorList", errors);
                        response.sendRedirect(url);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

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
