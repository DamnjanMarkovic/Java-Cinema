package com.comtrade.view;

import com.comtrade.domen.*;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ReservationForm extends JFrame implements Proxy {
    private JPanel jPanel;
    private JComboBox cbTheater;
    private JComboBox cbDate;
    private JComboBox cbShow;
    private JTextField tfNumberofTickets;
    private JLabel lblAvailableticketsNumber;
    private JComboBox cbTime;
    private JButton REZERVEButton;
    private JLabel lblUser;
    private JButton INFONSHOWButton;

    private int idTheater;
    private int idShow;
    private String date;
    private String time;



    public ReservationForm(User user) {

        add(jPanel);
        setBounds(200, 200, 700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("RESERVATION FORM");
        theatresIntoCB();
        lblUser.setText(user.getUsername());



        cbTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbShow.removeAllItems();
                String nameTheater = cbTheater.getSelectedItem().toString();
                Cinema cinema = new Cinema();
                cinema.settheaterName(nameTheater);
                idTheater = (int) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_ID).getResponse();
                setShowInCB(idTheater);
            }
        });

        cbShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                cbDate.removeAllItems();
                if (cbShow.getSelectedItem() != null) {
                    String showName = cbShow.getSelectedItem().toString();
                    Show show = new Show();
                    show.setshowName(showName);
                    idShow = (int) TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_ID).getResponse();
                    setTermDateCB(idShow);
                }
            }
        });


        cbDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbTime.removeAllItems();
                if (cbDate.getSelectedItem() !=null){
                    setTimeInCB();
                }
            }
        });
        cbTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postaviBrojKarata();
            }
        });
        REZERVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Integer.parseInt(lblAvailableticketsNumber.getText()) - Integer.parseInt(tfNumberofTickets.getText())>0 &&
                Integer.parseInt(tfNumberofTickets.getText())>0) {
                    try {
                        int idUser = returnUserID (user.getUsername(), user.getPassword());
                        int idTermina = vratiidTermina();
                        Reservation reservation = new Reservation(Integer.parseInt(tfNumberofTickets.getText()), idUser, idTermina);
                        TransferClass.transferKlasa(reservation, 0, ConstantFC.RESERVATION, ConstantBLC.RESERVATION_ENTRY);
                        clearFields();
                    }catch(Exception e){
                    }JOptionPane.showMessageDialog(null, "Reserved tickets!");
                }else
                    JOptionPane.showMessageDialog(null, "You can not reserve.");
            }
        });

        INFONSHOWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InfoShowForm infoShowForm = new InfoShowForm(idTheater, idShow);
                infoShowForm.setVisible(true);
            }
        });
    }

    private void clearFields() {
        tfNumberofTickets.setText("");
        cbShow.removeAllItems();
        cbDate.removeAllItems();
        cbTime.removeAllItems();
    }

    private int returnUserID(String username,String password) {

        User user = new User(username, password);
        int idUser = (int) TransferClass.transferKlasa(user, 0, ConstantFC.USER, ConstantBLC.USER_RETURN_ID).getResponse();
        return idUser;
    }

    private int vratiidTermina() {
        int idTerm=0;
        if (cbTime.getSelectedItem()!=null && cbDate.getSelectedItem() !=null &&
                cbShow.getSelectedItem()!=null){
            Date date = (Date) cbDate.getSelectedItem();
            String time = cbTime.getSelectedItem().toString();
            Term term = new Term();
            term.setdate((java.sql.Date) date);
            term.settime(time);
            term.setidShow(idShow);
            idTerm = (int) TransferClass.transferKlasa(term, 0, ConstantFC.TERM, ConstantBLC.TERM_RETURN_ID).getResponse();
        }return idTerm;
    }


    private void postaviBrojKarata() {

        if (cbTime.getSelectedItem()!=null && cbDate.getSelectedItem() !=null &&
                cbShow.getSelectedItem()!=null){

            int idTermina = vratiidTermina();
            Term term = new Term();
            term.setidTerm(idTermina);
            int numberOfTickets = (int) TransferClass.transferKlasa(term, 0, ConstantFC.RESERVATION, ConstantBLC.RESERVATION_NO_TICKETS).getResponse();
            lblAvailableticketsNumber.setText(String.valueOf(numberOfTickets));
        }
    }

    private void setTermDateCB(int idPredstave) {

        Term term = new Term();
        List<Date> listDates = (List<Date>) TransferClass.transferKlasa(term, idPredstave, ConstantFC.TERM, ConstantBLC.TERM_DATA_RETURN).getResponse();
        for (Date datumi:listDates             ) {
            cbDate.addItem(datumi);
        }
    }

    private void setTimeInCB() {
        Term term = new Term();
        term.setdate(java.sql.Date.valueOf(cbDate.getSelectedItem().toString()));
        List<String> listTime = (List<String>) TransferClass.transferKlasa(term, idShow, ConstantFC.TERM, ConstantBLC.TERM_RETURN_TIME).getResponse();
        for (String timings:listTime             ) {
            cbTime.addItem(timings);
        }
    }

    private void setShowInCB(int idTheater) {
        Show show = new Show();
        List<Show> listShow = (List<Show>) TransferClass.transferKlasa(show, idTheater, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_DATA).getResponse();
        for (Show show1 : listShow) {
            cbShow.addItem(show1.getshowName());
        }
    }
    private void theatresIntoCB() {
            Cinema cinema = new Cinema();
            List<String> namesTheater = (List<String>) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_NAMES).getResponse();
            for (String str: namesTheater             ) {
                cbTheater.addItem(str);
            }
    }
    @Override
    public void login(User user) {
    }
}
