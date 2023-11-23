/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tmdel
 */
public class WrapDB {

    private static final Logger LOG = Logger.getLogger(WrapDB.class.getName());

    public static int insertUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO user (userType, username, password, email, firstName, lastName, phone) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserType());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getFirstName());
            ps.setString(6, user.getLastName());
            ps.setString(7, user.getPhone());
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }

    public static LinkedHashMap<String, User> selectAllUsers() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            User user = null;
            LinkedHashMap<String, User> users = new LinkedHashMap();

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String userType = rs.getString("userType");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                user = new User(userID, userType, username, password, email);
                users.put(user.getUsername(), user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    public static List<UserData> selectUsersData() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user ";
     
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            UserData user = null;
            List<UserData> users = new ArrayList<>();
            
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                user = new UserData(userID, firstName, lastName, phone, email);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    public static List<UserDataReview> selectUsersDataReview() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT r.reviewID, u.userID, u.username, u.phone, u.email, "
                + "r.review, r.rating FROM user u INNER JOIN review r ON u.userID = r.userID"; 
     
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            UserDataReview user = null;
            List<UserDataReview> users = new ArrayList<>();
            
            while (rs.next()) {
                int reviewID = rs.getInt("reviewID");
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String review = rs.getString("review");
                int rating = rs.getInt("rating");
                user = new UserDataReview(reviewID, userID, username, phone, email, review, rating);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    public static List<UserDataEstimate> selectUsersDataEstimate() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT u.userID, u.firstName, u.lastName, u.phone, u.email, "
                + "e.make, e.model, e.year, e.wrapDescription FROM user u "
                + "INNER JOIN estimate e on u.userID = e.userID";
     
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            UserDataEstimate user = null;
            List<UserDataEstimate> users = new ArrayList<>();
            
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                String wrapDescription = rs.getString("wrapDescription");
                user = new UserDataEstimate(userID, firstName, lastName, phone, 
                        email, make, model, year, wrapDescription);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    public static List<UserData> selectUsersNoEstimate() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user "
                + "WHERE userID NOT IN "
                + "(SELECT userID FROM estimate)";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            UserData user = null;
            List<UserData> users = new ArrayList<>();
            
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                user = new UserData(userID, firstName, lastName, phone, email);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }

    public static String getPasswordForUsername(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT password "
                + "FROM user "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            String password = "";
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
            return password;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static String getPasswordForEmail(String email) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT password "
                + "FROM user "
                + "WHERE email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            String password = "";
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
            return password;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static User getUserInfo(String usernameOrEmail) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "";

        if (Validation.isEmail(usernameOrEmail)) {
            query = "SELECT * FROM user WHERE email = ?";
        } else {
            query = "SELECT * FROM user WHERE username = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usernameOrEmail);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                int userid = rs.getInt("userID");
                String userType = rs.getString("userType");
                String userName = rs.getString("username");
                String Password = rs.getString("password");
                String email = rs.getString("email");
                
                user = new User(userid, userType, userName, Password, email);
            }
            return user;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }
    
    public static boolean validateEmail(String email) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT email FROM user "
                + "WHERE email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            //String emailCheck = rs.getString("email");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** validate email sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** validate email null pointer?", e);
                throw e;
            }
        }
    }

    public static boolean validateUsername(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT username FROM user "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            //String usernameCheck = rs.getString("username");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** validate username sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** validate username null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updateUserUsername(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE user "
                + "SET username = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updateUserPassword(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE user "
                + "SET password = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updateUserEmail(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE user "
                + "SET email = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updateUserFirstName(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE user "
                + "SET firstName = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updateUserLastName(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE user "
                + "SET lastName = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getLastName());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void updateUserPhone(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE user "
                + "SET phone = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getPhone());
            ps.setInt(2, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int deleteUser(int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "DELETE FROM user "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** delete user sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int deleteReview(int userID, int reviewID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "DELETE FROM review "
                + "WHERE userID = ? AND reviewID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** delete review sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** delete review null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int insertEstimate(Estimate estimate) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO estimate (userID, make, model, year, wrapDescription) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, estimate.getUserID());
            ps.setString(2, estimate.getMake());
            ps.setString(3, estimate.getModel());
            ps.setInt(4, estimate.getYear());
            ps.setString(5, estimate.getWrapDescription());
    
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int insertReview(Review review) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO review (userID, review, rating) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, review.getUserID());
            ps.setString(2, review.getReview());
            ps.setInt(3, review.getRating());
     
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
}
