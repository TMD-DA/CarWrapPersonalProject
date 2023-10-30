package data.security;

import business.User;
import data.WrapDB;
import data.security.SecurityUtil;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedHashMap;

public class RegistrationService {
    public static RegistrationService shared = new RegistrationService();
    
    private RegistrationService() {}
    
    public void register(String email, String username, String password, String phone, String firstName, String lastName) throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPhone(phone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            user.setPassword(SecurityUtil.hashPassword(password));
            
            LinkedHashMap<String, User> users = WrapDB.selectAllUsers();
            
            if (users.size() == 0) {
                user.setUserType("admin");
            } else {
                user.setUserType("user");
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            WrapDB.insertUser(user);
        } catch (SQLException e) {
            throw e;
        }
    }
}
