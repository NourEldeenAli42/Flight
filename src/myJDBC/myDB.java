package myJDBC;
import components.CommonConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

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
        }catch (Exception e){e.printStackTrace();}
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
                JOptionPane.showMessageDialog (null, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!hasBooked (username,password)) {
                JOptionPane.showMessageDialog (null, "You have not booked a flight", "Error", JOptionPane.ERROR_MESSAGE);
            }
            PreparedStatement modifyBooking = conn.prepareStatement ("UPDATE bookings SET totalpaid=? , flightid = ?," +
                    "bookingtime = CURRENT_TIMESTAMP"+" WHERE bookerid=? AND flightid=?");
            modifyBooking.setInt (1,paidamount+getTotalPaid (username,password,flightid));
            modifyBooking.setInt (2,newflightid);
            modifyBooking.setInt (3,getUserID(username,password));
            modifyBooking.setInt (4,flightid);
            modifyBooking.executeUpdate ();
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
            String[] columnNames = {"Flight Number","From","To","Ticket Price","Seats Available"};
            DefaultTableModel model = new DefaultTableModel (columnNames,0);
            String[] row = {"Flight No.","From","To","Ticket Price","Available St."};
            model.addRow (row);
            if (!rs.isBeforeFirst ()) {
                JOptionPane.showMessageDialog (null, "No flights available", "Error", JOptionPane.ERROR_MESSAGE);
            }
            while (rs.next ()) {
                String flightNumber = rs.getString ("flightid");
                String from = rs.getString ("from");
                String to = rs.getString ("to");
                int ticketprice = rs.getInt ("ticketprice");
                int seatsavailable = rs.getInt ("seats");
                String[] text = {flightNumber,from,to,Integer.toString (ticketprice),Integer.toString (seatsavailable)};
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
}
