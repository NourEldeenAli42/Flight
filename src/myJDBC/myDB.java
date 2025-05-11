package myJDBC;
import components.CommonConstants;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class myDB {

    public static boolean checkUser(String username){
        try{
            Connection connection = DriverManager.getConnection (CommonConstants.DB_URL);

            PreparedStatement getUser = connection.prepareStatement (
                "SELECT * FROM users WHERE username=?");
                getUser.setString (1,username);

            ResultSet rs = getUser.executeQuery ();
            if (!rs.isBeforeFirst ()) return false;
        }catch (SQLException e){e.printStackTrace();}
        return true;
    }


    public static String getEmail(int id){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getEmail = conn.prepareStatement ("SELECT email FROM users WHERE usersid=?");
            getEmail.setInt (1,id);
            ResultSet rs = getEmail.executeQuery ();
            if (!rs.isBeforeFirst ()) return null;
            rs.next ();
            return rs.getString ("email");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String getName(int id){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getName = conn.prepareStatement ("SELECT name FROM users WHERE usersid=?");
            getName.setInt (1,id);
            ResultSet rs = getName.executeQuery ();
            if (!rs.isBeforeFirst ()) return null;
            rs.next ();
            return rs.getString ("name");
        }catch (SQLException e){
            e.printStackTrace();
        }
    return null;
    }


    public static boolean hasPassengerData(int userID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getPassengerData = conn.prepareStatement ("SELECT * FROM users WHERE usersid=?");
            getPassengerData.setInt (1, userID);
            ResultSet rs = getPassengerData.executeQuery ();
            if (!rs.isBeforeFirst ()) return false;
            rs.next ();
            return rs.getString ("passportnumber") != null && rs.getDate ("dateofbirth") != null;
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return false;
    }

    public static boolean checkPassword(String username,String password){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
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
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            if (checkUser (username)) return false;
            PreparedStatement insertUser = conn.prepareStatement ("INSERT INTO users (username, password, name, email) VALUES (?,?,?,?)");
            insertUser.setString (1,username);
            insertUser.setString (2,password);
            insertUser.setString (3,name);
            insertUser.setString (4,email);
            insertUser.executeUpdate ();
            PreparedStatement logRegister = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logRegister.setInt (1,getUserID (username,password));
            logRegister.setString (2,"User " + username + " registered");
            logRegister.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logRegister.executeUpdate ();
            return true;
        }catch (Exception e){e.printStackTrace();}
        return false;
    }


    public static boolean loginUser(String username, String password){
        try{Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getUser = conn.prepareStatement ("SELECT * FROM users WHERE username=? AND password=?");
            getUser.setString (1,username);
            getUser.setString (2,password);
            ResultSet rs = getUser.executeQuery ();
            if (!rs.isBeforeFirst ()) return false;
            rs.next ();
            CommonConstants.CURRENT_USER_ID=rs.getInt ("usersid");
            PreparedStatement logLogin = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logLogin.setInt (1,CommonConstants.CURRENT_USER_ID);
            logLogin.setString (2,"User " + username + " logged in");
            logLogin.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logLogin.executeUpdate ();
            return true;

        }catch (Exception e){e.printStackTrace();}
        return false;
    }


    public static void updateUser(int userID, String name, String email, String password){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement updateUser = conn.prepareStatement ("UPDATE users SET name=?, email=?, password=? WHERE usersid=?");
            updateUser.setString (1,name);
            updateUser.setString (2,email);
            updateUser.setString (3,password);
            updateUser.setInt (4,userID);
            updateUser.executeUpdate ();
            PreparedStatement logUpdate = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logUpdate.setInt (1,userID);
            logUpdate.setString (2,"User " + getUsername (userID) + " updated their profile");
            logUpdate.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logUpdate.executeUpdate ();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static int getUserType(int userID){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement gettype = conn.prepareStatement ("SELECT * FROM users WHERE usersid = ?");
            gettype.setInt (1,userID);
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
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
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


    public static boolean createBooking(String username,String password, int flightNumber,int tickettype){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            if (!checkUser (username)) return false;


            if (hasBooked (username,password,flightNumber)) {
                JOptionPane.showMessageDialog (null, "You have already booked a flight", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            PreparedStatement addBooking = conn.prepareStatement ("INSERT INTO bookings (bookerid, flightid,tickettype,bookingtime) VALUES (?,?,?,?)");
            addBooking.setInt (1,CommonConstants.CURRENT_USER_ID);
            addBooking.setInt (2,flightNumber);
            addBooking.setInt (3,tickettype);
            addBooking.setTimestamp (4,new Timestamp (System.currentTimeMillis ()));

            addBooking.executeUpdate ();
            PreparedStatement addAFlight = conn.prepareStatement ("UPDATE flights SET Areserved=Areserved+1 WHERE flightid=?");
            PreparedStatement addBFlight = conn.prepareStatement ("UPDATE flights SET Breserved=Breserved+1 WHERE flightid=?");
            PreparedStatement addCFlight = conn.prepareStatement ("UPDATE flights SET Creserved=Creserved+1 WHERE flightid=?");
            switch (tickettype){
                case 1:
                    if (getAreserved (flightNumber)+1> getFlightASeats (flightNumber)){
                        JOptionPane.showMessageDialog (null, "No seats available in class A", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    addAFlight.setInt (1,flightNumber);
                    addAFlight.executeUpdate ();
                    PreparedStatement logclassA = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
                    logclassA.setInt (1,CommonConstants.CURRENT_USER_ID);
                    logclassA.setString (2,"User " + username + " booked a flight " + flightNumber + " in class A");
                    logclassA.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
                    logclassA.executeUpdate ();
                    break;
                case 2:
                    if (getBreserved (flightNumber)+1> getFlightBSeats (flightNumber)){
                        JOptionPane.showMessageDialog (null, "No seats available in class B", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    addBFlight.setInt (1,flightNumber);
                    addBFlight.executeUpdate ();
                    PreparedStatement logclassB = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
                    logclassB.setInt (1,CommonConstants.CURRENT_USER_ID);
                    logclassB.setString (2,"User " + username + " booked a flight " + flightNumber + " in class B");
                    logclassB.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
                    logclassB.executeUpdate ();
                    break;
                case 3:
                    if (getCreserved (flightNumber)+1> getFlightCSeats (flightNumber)){
                        JOptionPane.showMessageDialog (null, "No seats available in class C", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    addCFlight.setInt (1,flightNumber);
                    addCFlight.executeUpdate ();
                    PreparedStatement logclassC = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
                    logclassC.setInt (1,CommonConstants.CURRENT_USER_ID);
                    logclassC.setString (2,"User " + username + " booked a flight " + flightNumber + " in class C");
                    logclassC.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
                    logclassC.executeUpdate ();
                    break;
            }
            return true;

        }catch (SQLException e){e.printStackTrace();}
        return false;
    }


    public static boolean hasBooked(String username,String password,int flightid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getBooking = conn.prepareStatement ("SELECT * FROM bookings WHERE bookerid=? AND flightid=?");
            getBooking.setInt (1,getUserID(username,password));
            getBooking.setInt (2,flightid);
            ResultSet rs = getBooking.executeQuery ();
            return rs.isBeforeFirst ();
        }catch (SQLException e){e.printStackTrace();}
        return false;
    }


    public static boolean modifyBooking(String username, String password,int paidamount,int flightid,int newflightid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            if (!checkUser (username)){
                JOptionPane.showMessageDialog (null, "Invalid username",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!hasBooked (username,password,flightid)) {
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

            PreparedStatement logModify = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logModify.setInt (1,getUserID(username,password));
            logModify.setString (2,"User " + username + " modified booking from flight " + flightid + " to flight " + newflightid);
            logModify.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logModify.executeUpdate ();


            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public static int getTotalPaid(String username, String password,int flightid){
        try{
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
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
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
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
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            if (!checkUser (username)){
                JOptionPane.showMessageDialog (null, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                return false;}
            if (!checkPassword (username,password)) {
                JOptionPane.showMessageDialog (null, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                return false;}
            PreparedStatement changeType = conn.prepareStatement ("UPDATE users SET usertype=? WHERE username=?");
            changeType.setInt (1,type);
            changeType.setString (2,username);
            deleteFromTable (getUserID (username,password));
            changeType.executeUpdate ();
            if (type==1){
                PreparedStatement addAdmin = conn.prepareStatement ("INSERT INTO admins (user) VALUES (?)");
                addAdmin.setInt (1,getUserID(username,password));
                addAdmin.executeUpdate ();
                PreparedStatement logAdmin = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
                logAdmin.setInt (1,getUserID(username,password));
                logAdmin.setString (2,"User " + username + " has been promoted to admin");
                logAdmin.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
                logAdmin.executeUpdate ();
            } else if (type==2){
                PreparedStatement addAgent = conn.prepareStatement ("INSERT INTO agents (user) VALUES (?)");
                addAgent.setInt (1,getUserID(username,password));
                addAgent.executeUpdate ();
                PreparedStatement logAgent = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
                logAgent.setInt (1,getUserID(username,password));
                logAgent.setString (2,"User " + username + " has been assigned as agent");
                logAgent.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
                logAgent.executeUpdate ();
            }else if (type==3){
                PreparedStatement addCustomer = conn.prepareStatement ("INSERT INTO customers (user) VALUES (?)");
                addCustomer.setInt (1,getUserID(username,password));
                addCustomer.executeUpdate ();
                PreparedStatement logCustomer = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
                logCustomer.setInt (1,getUserID(username,password));
                logCustomer.setString (2,"User " + username + " has been assigned as customer");
                logCustomer.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
                logCustomer.executeUpdate ();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static void deleteFromTable(int userID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            int type = getUserType (userID);
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
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);

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
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);

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


    public static void createNewFlight(String origin, String destination, java.util.Date takeoffDate, int ticketPrice, int Aseats, int Bseats, int Cseats){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);

            PreparedStatement createFlight = conn.prepareStatement ("INSERT INTO flights (`from`,`to`,takeoffdate,ticketprice,Aseats,Bseats,Cseats,seats) VALUES (?,?,?,?,?,?,?,?)");
            createFlight.setString (1,origin);
            createFlight.setString (2,destination);
            createFlight.setDate (3,getDate(takeoffDate));
            createFlight.setInt (4,ticketPrice);
            createFlight.setInt (5,Aseats);
            createFlight.setInt (6,Bseats);
            createFlight.setInt (7,Cseats);
            createFlight.setInt (8,Aseats+Bseats+Cseats);
            createFlight.executeUpdate ();
            JOptionPane .showMessageDialog (null, "Flight created successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            PreparedStatement logFlight = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logFlight.setInt (1,CommonConstants.CURRENT_USER_ID);
            logFlight.setString (2,"User " + getUsername (CommonConstants.CURRENT_USER_ID) + " created a flight from " + origin + " to " + destination);
            logFlight.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logFlight.executeUpdate ();
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane .showMessageDialog (null, "Failed to create flight",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static java.sql.Date getDate(java.util.Date date){
        return new Date (date.getTime ());
    }


    public static void addPayment(int userid,int flightid,int amount,String paymentMethod){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement addPayment = conn.prepareStatement ("INSERT INTO payments (user,flight,amount,paymentmethod,paymenttime) VALUES (?,?,?,?,?)");
            addPayment.setInt (1,userid);
            addPayment.setInt (2,flightid);
            addPayment.setInt (3,amount);
            addPayment.setString (4,paymentMethod);
            addPayment.setTimestamp (5,new Timestamp (System.currentTimeMillis ()));
            addPayment.executeUpdate ();

            PreparedStatement updateFlight = conn.prepareStatement ("UPDATE flights SET totalgained=totalgained+? WHERE flightid=?");
            updateFlight.setInt (1,amount);
            updateFlight.setInt (2,flightid);
            updateFlight.executeUpdate ();

            PreparedStatement getPaymentID = conn.prepareStatement ("SELECT paymentid FROM payments WHERE user=? AND flight=? AND amount=? AND paymentmethod=?");
            getPaymentID.setInt (1,userid);
            getPaymentID.setInt (2,flightid);
            getPaymentID.setInt (3,amount);
            getPaymentID.setString (4,paymentMethod);
            ResultSet rs = getPaymentID.executeQuery ();
            if (!rs.isBeforeFirst ()) return;
            rs.next ();

            int paymentID = rs.getInt ("paymentid");
            PreparedStatement updateBooking = conn.prepareStatement ("UPDATE bookings SET paymentid=? WHERE bookerid=? AND flightid=?");
            updateBooking.setInt (1,paymentID);
            updateBooking.setInt (2,userid);
            updateBooking.setInt (3,flightid);
            updateBooking.executeUpdate ();
            JOptionPane.showMessageDialog (null, "Payment successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            PreparedStatement logPayment = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logPayment.setInt (1,userid);
            logPayment.setString (2,"User " + getUsername (userid) + " made a payment of " + amount + "$ for flight " + flightid);
            logPayment.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logPayment.executeUpdate ();
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog (null, "Payment failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static DefaultTableModel filterflights(String origin, String destination, java.util.Date takeoffDate) {
    try {
        Connection conn = DriverManager.getConnection(CommonConstants.DB_URL);

        PreparedStatement filterFlights;
        ResultSet rs;
        String[] columnNames = {"Flight Number", "From", "To", "Price", "TakeOff Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        String[] row = {"Flight No.", "From", "To", "Price", "Take Off"};
        model.addRow(row);
        if (origin.equals("Select Origin") && destination.equals("Select Destination") && takeoffDate == null) {
            // If no filters are applied, retrieve all flights
            filterFlights = conn.prepareStatement("SELECT * FROM flights");
        } else if (origin.equals ("Select Origin") && destination.equals ("Select Destination")) {
            // If only takeoff date is provided
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE takeoffdate = ?");
            filterFlights.setDate(1, new java.sql.Date(takeoffDate.getTime()));
        } else if (origin.equals ("Select Origin") && takeoffDate == null) {
            // If only destination is provided
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE `to` = ?");
            filterFlights.setString(1, destination);
        } else if (origin.equals ("Select Origin")) {
            // If only origin is provided
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE `from` = ?");
            filterFlights.setString(1, origin);
        } else if (destination.equals ("Select Destination") && takeoffDate == null){
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE `from` = ?");
            filterFlights.setString(1, origin);
        } else if (destination.equals ("Select Destination")) {
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE `from` = ? AND takeoffdate = ?");
            filterFlights.setString(1, origin);
            filterFlights.setDate(2, new java.sql.Date(takeoffDate.getTime()));
        } else if (takeoffDate == null) {
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE `from` = ? AND `to` = ?");
            filterFlights.setString(1, origin);
            filterFlights.setString(2, destination);
        } else {
            filterFlights = conn.prepareStatement("SELECT * FROM flights WHERE `from` = ? AND `to` = ? AND takeoffdate = ?");
            filterFlights.setString(1, origin);
            filterFlights.setString (2,destination);
            filterFlights.setDate (3,getDate(takeoffDate));
        }
        // Execute the query
        rs = filterFlights.executeQuery();

        // Check if results exist

            // Add results to the model
            while (rs.next()) {
                String flightNumber = rs.getString("flightid");
                String from = rs.getString("from");
                String to = rs.getString("to");
                int ticketprice = rs.getInt("ticketprice");
                String tfDate = rs.getString("takeoffdate");

                String[] text = {flightNumber, from, to, ticketprice + "$", tfDate};
                model.addRow(text);
            }
        return model;

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error retrieving flight information",
                "Database Error", JOptionPane.ERROR_MESSAGE);

        // Return an empty model in case of error
        String[] columnNames = {"Flight Number", "From", "To", "Price", "TakeOff Date"};
        return new DefaultTableModel(columnNames, 0);
    }
}


    public static ResultSet getOrigins(){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getOrigins = conn.prepareStatement ("SELECT DISTINCT `from` FROM flights");
            return getOrigins.executeQuery ();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static ResultSet getDestinations(){
       try {Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
           PreparedStatement getOrigins = conn.prepareStatement ("SELECT DISTINCT `to` FROM flights");
           return getOrigins.executeQuery ();
       }catch (SQLException e){
           e.printStackTrace ();
       }
       return null;
    }


    public static int getTicketType(int userID, int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getTicketType = conn.prepareStatement ("SELECT tickettype FROM bookings WHERE bookerid=? AND flightid=?");
            getTicketType.setInt (1,userID);
            getTicketType.setInt (2,flightID);
            ResultSet rs = getTicketType.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("tickettype");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;

    }


    public static double getTicketPrice(int userid,int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getTicketPrice = conn.prepareStatement ("SELECT ticketprice FROM flights WHERE flightid=?");
            getTicketPrice.setInt (1,flightID);
            ResultSet rs = getTicketPrice.executeQuery ();
            if (!rs.isBeforeFirst ()) {return -1;}
            rs.next ();
            int type = getTicketType (userid,flightID);
            double price = 0;
            if (type==1) price += rs.getInt ("ticketprice")*1.5;
            else if (type==2) price += rs.getInt ("ticketprice");
            else if (type==3) price += rs.getInt ("ticketprice")*0.5;
            PreparedStatement checkmeals = conn.prepareStatement ("SELECT meals FROM passengers WHERE flight=? AND user=?");
            checkmeals.setInt (1,flightID);
            checkmeals.setInt (2,userid);
            ResultSet rs1 = checkmeals.executeQuery ();
            if (!rs1.isBeforeFirst ()) return price;
            rs1.next ();
            int meals = rs1.getInt ("meals");
            if (meals==1) price += 100;
            else if (meals==2) price += 200;
            return price;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }


    public static String showLogs(){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getLogs = conn.prepareStatement ("SELECT * FROM logs");
            ResultSet rs = getLogs.executeQuery ();
            String[] columnNames = {"Action", "Timestamp"};
            DefaultTableModel model = new DefaultTableModel (columnNames,0);
            if (!rs.isBeforeFirst ()) {
                JOptionPane.showMessageDialog (null, "No logs available", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            while (rs.next ()) {
                String action = rs.getString ("action");
                String timestamp = rs.getString ("timestamp");

                String[] text = {action,timestamp};
                model.addRow (text);
            }
            StringBuilder logs = new StringBuilder();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    logs.append(model.getValueAt(i, j)).append(" ");
                }
                logs.append("\n");
            }
            return logs.toString();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void createProgram(int UserID,int flightID,String hotelName,int meals,String type){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement createProgram = conn.prepareStatement ("INSERT INTO passengers (user,flight,hotel,meals,type) VALUES (?,?,?,?,?)");
            createProgram.setInt (1,UserID);
            createProgram.setInt (2,flightID);
            createProgram.setString (3,hotelName);
            createProgram.setInt (4,meals);
            createProgram.setString (5,type);
            createProgram.executeUpdate ();
            PreparedStatement logProgram = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logProgram.setInt (1,UserID);
            logProgram.setString (2,"User " + getUsername (UserID) + " created a program with hotel " + hotelName + " and with "+ meals+ "meals");
            logProgram.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logProgram.executeUpdate ();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void modifyFlight(int flightID,String origin,String destination,java.util.Date takeoffDate,int ticketPrice,int Aseats,int Bseats,int Cseats){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement modifyFlight = conn.prepareStatement ("UPDATE flights SET `from`=?, `to`=?, takeoffdate=?, ticketprice=?, Aseats=?, Bseats=?, Cseats=?,seats=? WHERE flightid=?");
            modifyFlight.setString (1,origin);
            modifyFlight.setString (2,destination);
            modifyFlight.setDate (3,getDate(takeoffDate));
            modifyFlight.setInt (4,ticketPrice);
            modifyFlight.setInt (5,Aseats);
            modifyFlight.setInt (6,Bseats);
            modifyFlight.setInt (7,Cseats);
            modifyFlight.setInt (8,Aseats+Bseats+Cseats);
            modifyFlight.setInt (9,flightID);
            modifyFlight.executeUpdate ();
            PreparedStatement logModifyFlight = conn.prepareStatement ("INSERT INTO logs (userid,action,timestamp) VALUES (?,?,?)");
            logModifyFlight.setInt (1,CommonConstants.CURRENT_USER_ID);
            logModifyFlight.setString (2,"User " + getUsername (CommonConstants.CURRENT_USER_ID) + " modified flight " + flightID);
            logModifyFlight.setTimestamp (3,new Timestamp (System.currentTimeMillis ()));
            logModifyFlight.executeUpdate ();
            JOptionPane .showMessageDialog (null, "Flight modified successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane .showMessageDialog (null, "Failed to modify flight",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static boolean checkFlight(int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement checkFlight = conn.prepareStatement ("SELECT * FROM flights WHERE flightid=?");
            checkFlight.setInt (1,flightID);
            ResultSet rs = checkFlight.executeQuery ();
            return !rs.isBeforeFirst ();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public static String getFlightOrigin(int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightOrigin = conn.prepareStatement ("SELECT `from` FROM flights WHERE flightid=?");
            getFlightOrigin.setInt (1,flightID);
            ResultSet rs = getFlightOrigin.executeQuery ();
            if (!rs.isBeforeFirst ()) return "Null";
            rs.next ();
            return rs.getString ("from");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "Null";
    }


    public static String getFlightDestination(int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightDestination = conn.prepareStatement ("SELECT `to` FROM flights WHERE flightid=?");
            getFlightDestination.setInt (1,flightID);
            ResultSet rs = getFlightDestination.executeQuery ();
            if (!rs.isBeforeFirst ()) return "Null";
            rs.next ();
            return rs.getString ("to");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "Null";
    }


    public static java.util.Date getFlightDate(int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightTakeOffDate = conn.prepareStatement ("SELECT takeoffdate FROM flights WHERE flightid=?");
            getFlightTakeOffDate.setInt (1,flightID);
            ResultSet rs = getFlightTakeOffDate.executeQuery ();
            if (!rs.isBeforeFirst ()) return null;
            rs.next ();
            return rs.getDate ("takeoffdate");
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public static int getFlightPrice(int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightTicketPrice = conn.prepareStatement ("SELECT ticketprice FROM flights WHERE flightid=?");
            getFlightTicketPrice.setInt (1,flightID);
            ResultSet rs = getFlightTicketPrice.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("ticketprice");
        }catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static int getFlightASeats(int flightID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightASeats = conn.prepareStatement ("SELECT Aseats FROM flights WHERE flightid=?");
            getFlightASeats.setInt (1, flightID);
            ResultSet rs = getFlightASeats.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("Aseats");
        } catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static int getFlightBSeats(int flightID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightBSeats = conn.prepareStatement ("SELECT Bseats FROM flights WHERE flightid=?");
            getFlightBSeats.setInt (1, flightID);
            ResultSet rs = getFlightBSeats.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("Bseats");
        } catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static int getFlightCSeats(int flightID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightCSeats = conn.prepareStatement ("SELECT Cseats FROM flights WHERE flightid=?");
            getFlightCSeats.setInt (1, flightID);
            ResultSet rs = getFlightCSeats.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("Cseats");
        } catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static int getAreserved(int flightID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightSeats = conn.prepareStatement ("SELECT Areserved FROM flights WHERE flightid=?");
            getFlightSeats.setInt (1, flightID);
            ResultSet rs = getFlightSeats.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("Areserved");
        } catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static int getBreserved(int flightID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightSeats = conn.prepareStatement ("SELECT Breserved FROM flights WHERE flightid=?");
            getFlightSeats.setInt (1, flightID);
            ResultSet rs = getFlightSeats.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("Breserved");
        } catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static int getCreserved(int flightID) {
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightSeats = conn.prepareStatement ("SELECT Creserved FROM flights WHERE flightid=?");
            getFlightSeats.setInt (1, flightID);
            ResultSet rs = getFlightSeats.executeQuery ();
            if (!rs.isBeforeFirst ()) return -1;
            rs.next ();
            return rs.getInt ("Creserved");
        } catch (SQLException e) {
            e.printStackTrace ();
            return -1;
        }
    }


    public static ResultSet getFlightsReport(){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightsReport = conn.prepareStatement ("SELECT * FROM flights");
            return getFlightsReport.executeQuery ();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String[] getFlightInfo(int flightID){
        try {
            Connection conn = DriverManager.getConnection (CommonConstants.DB_URL);
            PreparedStatement getFlightInfo = conn.prepareStatement ("SELECT * FROM flights WHERE flightid=?");
            getFlightInfo.setInt (1,flightID);
            ResultSet rs = getFlightInfo.executeQuery ();
            if (!rs.isBeforeFirst ()) return null;
            rs.next ();
            return new String[]{rs.getString ("from"),rs.getString ("to"),rs.getString ("takeoffdate"),
                    rs.getString ("Areserved"),rs.getString ("Breserved"),rs.getString ("Creserved"),rs.getString ("airline")};
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}