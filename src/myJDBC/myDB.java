package myJDBC;
import components.CommonConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class myDB {

    public static boolean checkUser(String username){
        try{
            Connection connection = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement getUser = connection.prepareStatement (
                "SELECT * FROM users WHERE username=?");
                getUser.setString (1,username);
                
            ResultSet rs = getUser.executeQuery ();
            if (!rs.isBeforeFirst ()) return false;
        }catch (Exception e){e.printStackTrace();}
        return true;
    }


    public static boolean registerUser(String username, String password, String name, String email){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            if (checkUser (username)) return false;
            PreparedStatement insertUser = conn.prepareStatement ("INSERT INTO users (username, password, name, email) VALUES (?,?,?,?)");
            insertUser.setString (1,username);
            insertUser.setString (2,password);
            insertUser.setString (3,name);
            insertUser.setString (4,email);
            insertUser.executeUpdate ();
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }
   
   
    public static boolean loginUser(String username, String password){
        try{Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getUser = conn.prepareStatement ("SELECT * FROM users WHERE username=? AND password=?");
            getUser.setString (1,username);
            getUser.setString (2,password);
            ResultSet rs = getUser.executeQuery ();
            return rs.isBeforeFirst ();

        }catch (Exception e){e.printStackTrace();}
        return false;
    }
}
