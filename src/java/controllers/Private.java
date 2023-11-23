package controllers;

import business.*;
import data.WrapDB;
import data.security.SecurityUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
                        errors.add("You must verify your password.");
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
            case "requestEstimate": {
                int userID = (int) loggedInUser.getUserID();
                String make = (String) request.getParameter("make");
                String model = (String) request.getParameter("model");
                String yearString = (String) request.getParameter("year");
                String wrapDescription = (String) request.getParameter("wrapdescription");
                int year = 0;
                List<String> errors = new ArrayList();

                if (make.equals("")) {
                    errors.add("You must enter the vehicles make.");
                }

                if (model.equals("")) {
                    errors.add("You must enter the vehicles model.");
                }

                if (yearString.equals("")) {
                    errors.add("You must enter the vehicles year.");
                }

                if (wrapDescription.equals("")) {
                    errors.add("You must enter the wrap description.");
                }

                if (errors.isEmpty()) {
                    try {
                        year = Integer.parseInt(yearString);
                    } catch (Exception e) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                    }

                    Estimate estimate = new Estimate(userID, make, model, year, wrapDescription);

                    try {
                        WrapDB.insertEstimate(estimate);
                    } catch (SQLException ex) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    url = "/userPage.jsp";
                } else {
                    request.setAttribute("errorList", errors);
                    response.sendRedirect(url);
                }

                break;
            }

            case "submitReview": {
                int userID = (int) loggedInUser.getUserID();
                int rating = 0;
                String reviewInput = (String) request.getParameter("review");

                try {
                    rating = Integer.parseInt(request.getParameter("rating"));
                } catch (NumberFormatException en) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, en);
                }

                Review review = new Review(userID, rating, reviewInput);

                try {
                    WrapDB.insertReview(review);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = "/userPage.jsp";

                break;
            }

            case "adminUserAction": {
                url = "/adminUsers.jsp";
                List<UserData> allUsers = new ArrayList<>();

                try {
                    allUsers = WrapDB.selectUsersData();
                } catch (SQLException e) {
                    Logger.getLogger(WrapDB.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("allUsers", allUsers);
                break;
            }

            case "adminDeleteUser": {
                int userID = 0;
                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                try {
                    WrapDB.deleteUser(userID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                url = "/Private?action=adminUserAction";
                break;
            }

            case "gotoAdminUserNE": {
                url = "/adminUserNE.jsp";
                List<UserData> noEstUsers = new ArrayList<>();

                try {
                    noEstUsers = WrapDB.selectUsersNoEstimate();
                } catch (SQLException e) {
                    Logger.getLogger(WrapDB.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("noEstUsers", noEstUsers);

                break;
            }

            case "gotoAdminReviews": {
                url = "/adminReviews.jsp";
                List<UserDataReview> userReview = new ArrayList<>();
                
                try {
                    userReview = WrapDB.selectUsersDataReview();
                } catch (SQLException e) {
                    Logger.getLogger(WrapDB.class.getName()).log(Level.SEVERE, null, e);
                }
                
                request.setAttribute("userReview", userReview);
                
                break;
            }
            
            case "adminDeleteReview": {
                int userID = 0;
                int reviewID = 0;
                
                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    reviewID = Integer.parseInt(request.getParameter("reviewID"));
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                try {
                    WrapDB.deleteReview(userID, reviewID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                url = "/Private?action=gotoAdminReviews";
                
                break;
            }
            
            case "gotoAdminQuotes": {
                url = "/adminQuotes.jsp";
                List<UserDataEstimate> userEstimate = new ArrayList<>();
                
                try {
                    userEstimate = WrapDB.selectUsersDataEstimate();
                } catch (SQLException e) {
                    Logger.getLogger(WrapDB.class.getName()).log(Level.SEVERE, null, e);
                }
                
                request.setAttribute("userEstimate", userEstimate);
                
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
