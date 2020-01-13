package com.comtrade.view;

import com.comtrade.domen.Cinema;
import com.comtrade.domen.ConstantFC;
import com.comtrade.domen.ConstantBLC;
import com.comtrade.domen.TransferClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CinemaForm extends JFrame{
    private JPanel jPanel;
    private JTextField tfName;
    private JTextField tfStreet;
    private JTextField tfNumber;
    private JTextField tfCity;
    private JButton SAVEButton;
    private JButton UPDATEButton;
    private JButton REMOVEButton;
    private JTable table1;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idTheater;

    public CinemaForm(){

        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("THEATER FORM");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        setColumns();
        setDataInTable();

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String nazivPozorista = tfName.getText();
                String ulica = tfStreet.getText();
                int broj = Integer.parseInt(tfNumber.getText());
                String city = tfCity.getText();
                Cinema cinema = new Cinema(nazivPozorista, ulica, broj, city);
                TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_DATA_ENTRY);
                setDataInTable();
                clearFields();
            }
        });




        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Cinema cinema = new Cinema();
                cinema.setidTheater(idTheater);
                TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_REMOVE);
                setDataInTable();
                clearFields();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idTheater = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
                tfName.setText(table1.getModel().getValueAt(red, 1).toString());
                tfStreet.setText(table1.getModel().getValueAt(red, 2).toString());
                tfNumber.setText(String.valueOf(Integer.parseInt(table1.getModel().getValueAt(red, 3).toString())));
                tfCity.setText(table1.getModel().getValueAt(red, 4).toString());
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String theaterName = tfName.getText();
                String street = tfStreet.getText();
                int number = Integer.parseInt(tfNumber.getText());
                String city = tfCity.getText();
                Cinema cinema = new Cinema(idTheater, theaterName, street, number, city);
                TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_DATA_UPDATE);
                setDataInTable();
                clearFields();
            }
        });

    }

    private void setDataInTable() {
        dtm.setRowCount(0);
        Cinema cinema = new Cinema();
        List<Cinema> list = (List<Cinema>) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_DATA).getResponse();
        for (Cinema pozor: list) {
            Object [] red = {pozor.getidTheater(), pozor.gettheaterName(), pozor.getstreet(), (pozor.getnumber()), pozor.getcity()};
            dtm.addRow(red);
        }
    }

    private void clearFields() {
        tfName.setText("");
        tfStreet.setText("");
        tfNumber.setText("");
        tfCity.setText("");

    }

    private void setColumns() {
        Object[]kolone = {"idTheater", "nameTheater", "street", "number", "city"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
        dtm.addColumn(kolone[4]);
    }
}
