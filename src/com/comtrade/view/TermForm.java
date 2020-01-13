package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TermForm extends JFrame{
    private JPanel jPanel;
    private JComboBox cbShow;

    private JTextField tfNumberofTickets;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JButton SaveTermButton;
    private JButton RemoveTermButton;
    private JButton PROMENITERMINButton;

    private JComboBox cbMinutes;
    private JComboBox cbHoures;
    private JComboBox cbYear;
    private JComboBox cbMonth;
    private JComboBox cbDay;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idTerm;
    private int idShow;


    public TermForm(){
        add(jPanel);
        setBounds(200,200,800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TERM FORM");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        insertShowsCB();
        setColumns();
        setDateTimeCB();



        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idTerm = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
            }
        });
        cbShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                dtm.setRowCount(0);
                String showName = cbShow.getSelectedItem().toString();
                Show show = new Show();
                show.setshowName(showName);
                idShow = (int) TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_ID).getResponse();

                Term term = new Term();
                List<Term> listaTermina = (List<Term>) TransferClass.transferKlasa(term, idShow, ConstantFC.TERM, ConstantBLC.TERM_RETURN_TERM).getResponse();
                for (Term term1 : listaTermina) {
                    Object [] red = {term1.getidTerm(), term1.gettime(), term1.getdate(), term1.getticketsNumber(), term1.getidShow()};
                    dtm.addRow(red);
                }
            }
        });
        SaveTermButton.addActionListener(new ActionListener() {

            String time =null;
            String dateString=null;
            public void actionPerformed(ActionEvent actionEvent) {
                if (cbHoures.getSelectedItem().toString()!=null && cbMinutes.getSelectedItem().toString()!=null && cbYear.getSelectedItem().toString() !=null
                        && cbMonth.getSelectedItem().toString()!=null && cbDay.getSelectedItem().toString()!=null){

                     time = cbHoures.getSelectedItem().toString() + ":"+ cbMinutes.getSelectedItem().toString() + ":00";
                     dateString = cbYear.getSelectedItem().toString() + "-"+ cbMonth.getSelectedItem().toString() + "-" + cbDay.getSelectedItem().toString();
                }

                java.sql.Date date = java.sql.Date.valueOf( dateString );

                int numberOfTickets = Integer.parseInt(tfNumberofTickets.getText());
                Term term = new Term(time, date, numberOfTickets, idShow);
                TransferClass.transferKlasa(term, 0, ConstantFC.TERM, ConstantBLC.TERM_DATA_ENTRY);
                clearFields();
            }
        });
        RemoveTermButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Term term = new Term();
                term.setidTerm(idTerm);
                TransferClass.transferKlasa(term, 0, ConstantFC.TERM, ConstantBLC.TERM_REMOVE);
                clearFields();

            }
        });
    }

    private void setDateTimeCB() {

        String[] list = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        for (String str: list             ) {
            cbHoures.addItem(str);
        }
        String[] listMinutes = new String[] {"00", "30"};
        for (String str: listMinutes             ) {
            cbMinutes.addItem(str);
        }
        String[] listYears = new String[] {"2020", "2021", "2022"};
        for (String str: listYears             ) {
            cbYear.addItem(str);
        }
        String[] listaMonths = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        for (String str: listaMonths             ) {
            cbMonth.addItem(str);
        }
        String[] listDays = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        for (String str: listDays             ) {
            cbDay.addItem(str);
        }

    }


    private void setDataInTable() {
        dtm.setRowCount(0);
        Term term = new Term();
        List<Term> listaTermina = (List<Term>) TransferClass.transferKlasa(term, idShow, ConstantFC.TERM, ConstantBLC.TERM_DATA_RETURN).getResponse();
        for (Term term1 : listaTermina) {
            Object [] red = {term1.getidTerm(), term1.gettime(), term1.getdate(), term1.getticketsNumber(), term1.getidShow()};
            dtm.addRow(red);
        }
    }

    private void setColumns() {

        Object[]kolone = {"idTerm", "time", "date", "numberOfTickets", "idShow"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
        dtm.addColumn(kolone[4]);

    }

    private void insertShowsCB() {
        Show show = new Show();
        List<String> showNames = (List<String>) TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_NAMES).getResponse();
        for (String str: showNames             ) {
            cbShow.addItem(str);
        }
    }
    private void clearFields() {
        tfNumberofTickets.setText("");

    }
}
