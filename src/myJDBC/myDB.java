package myJDBC;
import components.CommonConstants;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        }catch (SQLException e){e.printStackTrace();}
        return true;
    }


    public static boolean checkPassword(String username,String password){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getUser = conn.prepareStatement ("SELECT * FROM users WHERE username=?");
            getUser.setString (1,username);
            ResultSet rs = getUser.executeQuery ();
            if (!rs.isBeforeFirst ()) return false;
            rs.next ();
            return rs.getString ("password").equals (password);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
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


    public static int getUserType(String username){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement gettype = conn.prepareStatement ("SELECT * FROM users WHERE username = ?");
            gettype.setString (1,username);
            ResultSet rs = gettype.executeQuery ();
            if (!rs.isBeforeFirst ()){
                JOptionPane.showMessageDialog (null,"The user doesn't exist","Error",JOptionPane.ERROR_MESSAGE);
            }
            rs.next ();
            return rs.getInt("usertype");
        }catch (SQLException e){
            e.printStackTrace ();
        }
        return -1;
    }


    public static int getUserID(String username,String password){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getUser = conn.prepareStatement ("SELECT * FROM users WHERE username=?");
            getUser.setString (1,username);
            ResultSet rs = getUser.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            if (rs.getString ("password").equals (password) ){
                return rs.getInt ("usersid");
            } else {
                return -2;
            }
        }catch (SQLException e){e.printStackTrace();}
        return -1;
    }


    public static boolean createBooking(String username,String password, String flightNumber,int totalpaid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            if (!checkUser (username)) return false;
            int ticketprice = getFlightPrice (Integer.parseInt (flightNumber));

            if (ticketprice<totalpaid){
                JOptionPane.showMessageDialog (null, "Ticket price is" + ticketprice
                        , "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (hasBooked (username,password)) {
                JOptionPane.showMessageDialog (null, "You have already booked a flight", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            PreparedStatement addBooking = conn.prepareStatement ("INSERT INTO bookings (bookerid, flightid,totalpaid) VALUES (?,?,?)");
            addBooking.setInt (1,getUserID(username,password));
            addBooking.setString (2,flightNumber);
            addBooking.setInt (3,totalpaid);
            addBooking.executeUpdate ();
            PreparedStatement addFlight = conn.prepareStatement ("UPDATE flights SET seats=seats-1 WHERE flightid=?");
            addFlight.setString (1,flightNumber);
            addFlight.executeUpdate ();
            return true;

        }catch (Exception e){e.printStackTrace();}
        return false;
    }


    public static int getFlightPrice(int flightid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getPrice = conn.prepareStatement ("SELECT ticketprice FROM flights WHERE flightid=?");
            getPrice.setInt (1,flightid);
            ResultSet rs = getPrice.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("ticketprice");
        } catch (SQLException e) {
        e.printStackTrace();}
        return -1;
    }


    public static boolean hasBooked(String username,String password){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getBooking = conn.prepareStatement ("SELECT * FROM bookings WHERE bookerid=?");
            getBooking.setInt (1,getUserID(username,password));
            ResultSet rs = getBooking.executeQuery ();
            return rs.isBeforeFirst ();
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }


    public static boolean modifyBooking(String username, String password,int paidamount,int flightid,int newflightid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            if (!checkUser (username)){
                JOptionPane.showMessageDialog (null, "Invalid username",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!hasBooked (username,password)) {
                JOptionPane.showMessageDialog (null, "You have not booked a flight",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            PreparedStatement modifyBooking = conn.prepareStatement ("UPDATE bookings SET totalpaid=? , flightid = ?," +
                    "bookingtime = CURRENT_TIMESTAMP"+" WHERE bookerid=? AND flightid=?");

            modifyBooking.setInt (1,paidamount+getTotalPaid (username,password,flightid));
            modifyBooking.setInt (2,newflightid);
            modifyBooking.setInt (3,getUserID(username,password));
            modifyBooking.setInt (4,flightid);
            modifyBooking.executeUpdate ();

            PreparedStatement setSeats = conn.prepareStatement ("UPDATE flights SET seats=seats-1 WHERE flightid=?");
            setSeats.setInt (1,newflightid);
            setSeats.executeUpdate ();
            PreparedStatement minusSeats = conn.prepareStatement ("UPDATE flights SET seats=seats+1 WHERE flightid=?");
            minusSeats.setInt (1,flightid);
            minusSeats.executeUpdate ();

            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public static int getTotalPaid(String username, String password,int flightid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getBooking = conn.prepareStatement ("SELECT totalpaid FROM bookings WHERE bookerid=? AND flightid=?");
            getBooking.setInt (1,getUserID(username,password));
            getBooking.setInt (2,flightid);
            ResultSet rs = getBooking.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("totalpaid");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }


    public static DefaultTableModel getAvailableFlight(){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            PreparedStatement getFlights = conn.prepareStatement ("SELECT * FROM flights");
            ResultSet rs = getFlights.executeQuery ();
            String[] columnNames = {"Flight Number","From","To","Price","A Class","B Class","C Class"
                    ,"Take Off Date"};
            DefaultTableModel model = new DefaultTableModel (columnNames,0);
            String[] row = {"Flight No.","From","To","Price","A Class","B Class","C Class"
                    ,"Take Off"};
            model.addRow (row);
            if (!rs.isBeforeFirst ()) {
                JOptionPane.showMessageDialog (null, "No flights available", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            while (rs.next ()) {
                String flightNumber = rs.getString ("flightid");
                String from = rs.getString ("from");
                String to = rs.getString ("to");
                int ticketprice = rs.getInt ("ticketprice");
                String tfDate = rs.getString ("takeoffdate");

                int Aseats = rs.getInt ("Aseats");
                int Bseats = rs.getInt ("Bseats");
                int Cseats = rs.getInt ("Cseats");


                String[] text = {flightNumber,from,to, ticketprice +"$",Integer.toString (Aseats),
                        Integer.toString (Bseats),Integer.toString (Cseats), tfDate};
                model.addRow (text);
            }
            return model;
        }catch (SQLException e){
            e.printStackTrace();
        }
        String[] columnNames = {"Flight No.","From","To","Ticket Price","Available St."};
        JOptionPane.showMessageDialog (null, "No flights available", "Error", JOptionPane.ERROR_MESSAGE);
        return new DefaultTableModel (columnNames,0);
    }


    public static boolean changeUserType(String username, String password, int type){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            if (!checkUser (username)){
                JOptionPane.showMessageDialog (null, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                return false;}
            if (!checkPassword (username,password)) {
                JOptionPane.showMessageDialog (null, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                return false;}
            PreparedStatement changeType = conn.prepareStatement ("UPDATE users SET usertype=? WHERE username=?");
            changeType.setInt (1,type);
            changeType.setString (2,username);
            deleteFromTable (username,password);
            changeType.executeUpdate ();
            if (type==1){
                PreparedStatement addAdmin = conn.prepareStatement ("INSERT INTO admins (user) VALUES (?)");
                addAdmin.setInt (1,getUserID(username,password));
                addAdmin.executeUpdate ();
            } else if (type==2){
                PreparedStatement addAgent = conn.prepareStatement ("INSERT INTO agents (user) VALUES (?)");
                addAgent.setInt (1,getUserID(username,password));
                addAgent.executeUpdate ();
            }else if (type==3){
                PreparedStatement addCustomer = conn.prepareStatement ("INSERT INTO customers (user) VALUES (?)");
                addCustomer.setInt (1,getUserID(username,password));
                addCustomer.executeUpdate ();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static void deleteFromTable(String username,String password){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);
            int type = getUserType (username);
            int userID = getUserID (username,password);
            if (type==1){
                PreparedStatement deleteAdmin = conn.prepareStatement ("DELETE FROM admins WHERE user=?");
                deleteAdmin.setInt (1,userID);
                deleteAdmin.executeUpdate ();
            } else if (type==2){
                PreparedStatement deleteAgent = conn.prepareStatement ("DELETE FROM agents WHERE user=?");
                deleteAgent.setInt (1,userID);
                deleteAgent.executeUpdate ();
            }else if (type==3){
                PreparedStatement deleteCustomer = conn.prepareStatement ("DELETE FROM customers WHERE user=?");
                deleteCustomer.setInt (1,userID);
                deleteCustomer.executeUpdate ();
            }
        }catch (SQLException e){e.printStackTrace();}
    }


    public static String getUsername(int userID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement getUsername = conn.prepareStatement ("SELECT username FROM users WHERE usersid=?");
            getUsername.setInt (1,userID);
            ResultSet rs = getUsername.executeQuery ();
            if (!rs.isBeforeFirst ()) return null;
            rs.next ();
            return rs.getString ("username");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String getPassword(int userID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement getPassword = conn.prepareStatement ("SELECT password FROM users WHERE usersid=?");
            getPassword.setInt (1,userID);
            ResultSet rs = getPassword.executeQuery ();
            if (!rs.isBeforeFirst ()) return null;
            rs.next ();
            return rs.getString ("password");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void createNewFlight(String origin, String destination,
                                       java.util.Date takeoffDate, int ticketPrice,
                                       int Aseats, int Bseats, int Cseats){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement createFlight = conn.prepareStatement ("INSERT INTO flights (`from`,`to`,takeoffdate,ticketprice,Aseats,Bseats,Cseats) VALUES (?,?,?,?,?,?,?)");
            createFlight.setString (1,origin);
            createFlight.setString (2,destination);
            createFlight.setDate (3,getDate(takeoffDate));
            createFlight.setInt (4,ticketPrice);
            createFlight.setInt (5,Aseats);
            createFlight.setInt (6,Bseats);
            createFlight.setInt (7,Cseats);
            createFlight.executeUpdate ();
            JOptionPane .showMessageDialog (null, "Flight created successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane .showMessageDialog (null, "Failed to create flight",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private static java.sql.Date getDate(java.util.Date date){
        return new Date (date.getTime ());
    }
}