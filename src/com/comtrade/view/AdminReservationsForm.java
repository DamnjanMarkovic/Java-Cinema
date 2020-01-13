package com.comtrade.view;

import com.comtrade.domen.*;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AdminReservationsForm extends JFrame implements Proxy {
    private JPanel jPanel;
    private JComboBox cbTheater;
    private JComboBox cbShow;
    private JComboBox cbDate;
    private JTable table1;
    private JComboBox cbTime;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idTheater;
    private int idShow;


    public AdminReservationsForm(){


        add(jPanel);
        setBounds(200, 200, 700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN RESERVATIONS FORM");
        theatersIntoCB();
        columnsIntoTable();
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);


        cbTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbShow.removeAllItems();
                showsIntoCB();

            }
        });
        cbShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbDate.removeAllItems();
                datesIntoCB();

            }
        });
        cbDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbTime.removeAllItems();
                    if (cbDate.getSelectedItem() !=null){
                        timeIntoCB ((Date) cbDate.getSelectedItem());
                    }
            }
        });
        cbTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postaviPodatkeUTabelu();
            }
        });
    }
    private void theatersIntoCB() {
        Cinema cinema = new Cinema();
        List<String> nameTheatres = (List<String>) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_NAMES).getResponse();
        for (String str: nameTheatres             ) {
            cbTheater.addItem(str);
        }
    }
    private void showsIntoCB() {
        String theaterName = cbTheater.getSelectedItem().toString();
        Cinema cinema = new Cinema();
        cinema.settheaterName(theaterName);
        idTheater = (int) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_ID).getResponse();
        Show show = new Show();
        List<Show> listShow = (List<Show>) TransferClass.transferKlasa(show, idTheater, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_DATA).getResponse();
        for (Show show1 : listShow) {
            cbShow.addItem(show1.getshowName());
        }
    }
    private void datesIntoCB() {
        if (cbShow.getSelectedItem() != null) {
            String showName = cbShow.getSelectedItem().toString();
            Show show = new Show();
            show.setshowName(showName);
            idShow = (int) TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_ID).getResponse();
            Term term = new Term();
            List<Date>listDates = (List<Date>) TransferClass.transferKlasa(term, idShow, ConstantFC.TERM, ConstantBLC.TERM_DATA_RETURN).getResponse();
            for (Date datess:listDates ) {
                cbDate.addItem(datess);
            }
        }
    }
    private void timeIntoCB(Date cbTerminDatum) {
        Term term = new Term();
        term.setdate((java.sql.Date) cbTerminDatum);
        List<String> listTime = (List<String>) TransferClass.transferKlasa(term, idShow, ConstantFC.TERM, ConstantBLC.TERM_RETURN_TIME).getResponse();
        for (String time:listTime             ) {
            cbTime.addItem(time);
        }
    }

    private void postaviPodatkeUTabelu() {
        dtm.setRowCount(0);
        Reservation reservation = new Reservation();
        List<Reservation> listaReservation = (List<Reservation>) TransferClass.transferKlasa(reservation, vratiidTermina(), ConstantFC.RESERVATION, ConstantBLC.RESERVATION_RETURN).getResponse();

        for (Reservation rez: listaReservation) {
            User user = new User();
            String imeKorisnika = (String) TransferClass.transferKlasa(user, rez.getidUser(), ConstantFC.USER, ConstantBLC.USER_RETURN_NAME).getResponse();
            Object [] red = {imeKorisnika, rez.getnumberOfTickets()};
            dtm.addRow(red);
        }
    }

    private void columnsIntoTable() {

        Object[] kolone ={"user name", "number of reserved tickets"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);

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

    @Override
    public void login(User user) {

    }
}
