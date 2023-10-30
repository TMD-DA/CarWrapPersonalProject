package business;

import data.WrapDB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author tmdel
 */
public class Validation {

    private static final Logger LOG = Logger.getLogger(WrapDB.class.getName());

    // Simply used for determining if the user entered a username or email in the log in form so the login process can proceed in accordance with which was entered.
    // NOT used for Validating data, with errors and all that comes with it.
    public static boolean isEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        // must be unique for all users

        boolean result = false;
        boolean rs = false;
        try {
            rs = WrapDB.validateUsername(username);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** SQL error in checking username.");
        }

        if (!rs) { 
            result = true;
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        // must have more than 5 characters and contain a @ and a . after the @ and must be unique for all users
        boolean result = false;
        try {
            result = WrapDB.validateEmail(email);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** SQL error in checking email.");
        }

        return result;
    }

    public static boolean isValidPassword(String password) {
        // password must be more than 10 characters long

        boolean result = false;

        if (password.length() > 10) {
            result = true;
        }

        return result;
    }
}
