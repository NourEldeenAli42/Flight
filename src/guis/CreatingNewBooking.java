package guis;

import com.toedter.calendar.JDateChooser;
import components.CommonConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.beans.*;
import java.util.Objects;

import myJDBC.myDB;

public class CreatingNewBooking extends Form {

    public CreatingNewBooking() {
        super("Create New Booking");
        addGuiComponents();
    }

    private void addGuiComponents() {
        getContentPane().setBackground(CommonConstants.SECONDARY_COLOR);

        JLabel CNBLapel = new JLabel("Create New Booking");
        CNBLapel.setForeground(CommonConstants.TEXT_COLOR);
        CNBLapel.setFont(new Font("Dialog", Font.BOLD, 25));
        CNBLapel.setHorizontalAlignment(SwingConstants.CENTER);
        CNBLapel.setBounds(130,30,240,50);
        add(CNBLapel);

        JTable filteredflights = new JTable();
        filteredflights.setBackground ( CommonConstants.PRIMARY_COLOR );
        filteredflights.setForeground ( CommonConstants.TEXT_COLOR );
        filteredflights.setFont ( new Font ( "Dialog", Font.PLAIN, 15 ) );
        filteredflights.setBounds(43, 190, 420, 150);
        filteredflights.setRowHeight ( 30 );
        filteredflights.setSelectionBackground ( CommonConstants.TEXT_COLOR );
        filteredflights.setSelectionForeground ( CommonConstants.PRIMARY_COLOR );
        filteredflights.setGridColor ( CommonConstants.TEXT_COLOR );
        filteredflights.setShowGrid ( true );
        filteredflights.setAutoResizeMode ( JTable.AUTO_RESIZE_ALL_COLUMNS );
        filteredflights.setFillsViewportHeight ( true );
        JScrollPane scrollPane = new JScrollPane ( filteredflights );
        scrollPane.setBounds ( 43, 210, 420, 200 );
        scrollPane.setBackground ( CommonConstants.PRIMARY_COLOR );
        scrollPane.setForeground ( CommonConstants.TEXT_COLOR );
        scrollPane.setBorder ( BorderFactory.createLineBorder ( CommonConstants.TEXT_COLOR ) );
        add ( scrollPane );
        JButton filterFlightsButton = new JButton("Filter Flights");
        filterFlightsButton.setBounds(43, 460, 420, 30);
        filterFlightsButton.setForeground(CommonConstants.PRIMARY_COLOR);
        filterFlightsButton.setBackground(CommonConstants.TEXT_COLOR);
        filterFlightsButton.setFont(new Font("Dialog", Font.BOLD, 15));
        filterFlightsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        JComboBox<String> destination = new JComboBox<>();
        ResultSet destinations = myDB.getDestinations ();
        destination.addItem ( "Select Destination");
        while (true) {
            try {
                if (destinations==null)break;
                if (!destinations.next()) break;
                String destinationCity = destinations.getString("to");
                destination.addItem(destinationCity);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        destination.setBackground ( CommonConstants.PRIMARY_COLOR );
        destination.setForeground ( CommonConstants.TEXT_COLOR );
        destination.setFont ( new Font ( "Dialog", Font.PLAIN, 15 ) );
        destination.setBounds(260, 100, 210, 30);
        destination.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        destination.setSelectedIndex (0);

        add (destination);

        JComboBox<String> origin = new JComboBox<>();
        origin.setBackground ( CommonConstants.PRIMARY_COLOR );
        origin.setForeground ( CommonConstants.TEXT_COLOR );
        origin.setFont ( new Font ( "Dialog", Font.PLAIN, 15 ) );
        origin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        origin.setBounds(43, 100, 210, 30);
        origin.addItem ( "Select Origin");
        origin.setSelectedIndex(0);
        ResultSet origins = myDB.getOrigins();
        while (true) {
            try {
                if (origins==null)break;
                if (!origins.next()) break;
                String originCity = origins.getString("from");
                origin.addItem(originCity);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        add (origin);

        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(43, 140, 430, 30);
        dateChooser.setForeground(CommonConstants.TEXT_COLOR);
        dateChooser.setBackground(CommonConstants.PRIMARY_COLOR);
        dateChooser.setFont(new Font("Dialog", Font.PLAIN, 15));
        dateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.getDateEditor ().getUiComponent ().setBackground ( CommonConstants.PRIMARY_COLOR );
        dateChooser.getDateEditor ().getUiComponent ().setForeground ( CommonConstants.TEXT_COLOR );
        dateChooser.getDateEditor ().getUiComponent ().setFont ( new Font ( "Dialog", Font.PLAIN, 15 ) );
        add(dateChooser);

        destination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    filteredflights.setModel(myDB.filterflights(Objects.requireNonNull (origin.getSelectedItem ()).toString(), Objects.requireNonNull (destination.getSelectedItem ()).toString(), dateChooser.getDate()));
            }
        });

        origin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    filteredflights.setModel(myDB.filterflights(Objects.requireNonNull (origin.getSelectedItem ()).toString(), Objects.requireNonNull (destination.getSelectedItem ()).toString(), dateChooser.getDate()));
            }
        });

        dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                    filteredflights.setModel(myDB.filterflights(Objects.requireNonNull (origin.getSelectedItem ()).toString(), Objects.requireNonNull (destination.getSelectedItem ()).toString(), dateChooser.getDate()));
            }
        });


        final Object[] flight = {null};
        filteredflights.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = filteredflights.getSelectedRow();
                if (selectedRow != -1) {
                    int columnIndex = 0;
                    flight[0] = filteredflights.getValueAt(selectedRow, columnIndex);
                }
            }
        });



        JRadioButton classAButton = new JRadioButton("Class A");
        classAButton.setBounds(60, 510, 100, 30);
        classAButton.setForeground(CommonConstants.TEXT_COLOR);
        classAButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classAButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classAButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(classAButton);

        JRadioButton classBButton = new JRadioButton("Class B");
        classBButton.setBounds(200, 510, 100, 30);
        classBButton.setForeground(CommonConstants.TEXT_COLOR);
        classBButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classBButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classBButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        classBButton.setSelected(true);
        add(classBButton);

        JRadioButton classCButton = new JRadioButton("Class C");
        classCButton.setBounds(340, 510, 100, 30);
        classCButton.setForeground(CommonConstants.TEXT_COLOR);
        classCButton.setBackground(CommonConstants.SECONDARY_COLOR);
        classCButton.setFont(new Font("Dialog", Font.PLAIN, 17));
        classCButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(classCButton);

        ButtonGroup classButtonGroup = new ButtonGroup();
        classButtonGroup.add(classAButton);
        classButtonGroup.add(classBButton);
        classButtonGroup.add(classCButton);

        JButton createBookingButton = new JButton("Create Booking");
        createBookingButton.setBounds(250, 550, 200, 60);
        createBookingButton.setForeground(CommonConstants.PRIMARY_COLOR);
        createBookingButton.setBackground(CommonConstants.TEXT_COLOR);
        createBookingButton.setFont(new Font("Dialog", Font.BOLD, 22));
        createBookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        createBookingButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = myDB.getUsername(CommonConstants.CURRENT_USER_ID);
                String password = String.valueOf(myDB.getPassword(CommonConstants.CURRENT_USER_ID));
                int flightno = Integer.parseInt(flight[0].toString());
                if (myDB.hasBooked (username, password, flightno)) {
                    JOptionPane.showMessageDialog (CreatingNewBooking.this, "You have already booked this flight");
                } else {
                    int classType = classAButton.isSelected() ? 1 : classBButton.isSelected() ? 2 : 3;

                    if (myDB.createBooking(username, password,flightno, classType)) {
                        JOptionPane.showMessageDialog(CreatingNewBooking.this, "Booking created successfully");
                        DesignFlightProgram temp = new DesignFlightProgram();
                        DesignFlightProgram.setFlightNumber (flightno);
                        dispose();
                        temp.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(CreatingNewBooking.this, "Failed to create booking");
                    }
                }
            }
        });
        

        add(createBookingButton);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(43, 550, 200, 60);
        BackButton.setForeground(CommonConstants.PRIMARY_COLOR);
        BackButton.setBackground(CommonConstants.TEXT_COLOR);
        BackButton.setFont(new Font("Dialog", Font.BOLD, 25));
        BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                navigateBack();
            }
        });
        add(BackButton);
    }

}
