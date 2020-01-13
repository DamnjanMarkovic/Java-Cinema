package com.comtrade.view;

import com.comtrade.domen.*;
import com.comtrade.view.proxy.Proxy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AdminForm extends JFrame implements Proxy {
    private JPanel jPanel;
    private JComboBox cbTheater;
    private JComboBox cbShow;
    private JTable table1;
    private JButton USERSButton;
    private JButton THEATERButton;
    private JButton SHOWButton;
    private JButton TERMButton;
    private JButton ACTORButton;
    private JScrollPane jScrollPane;
    private JButton RESERVATIONSButton;
    private DefaultTableModel dtm = new DefaultTableModel();
    private JLabel lblKorisnik;
    private int idTheater;
    private int idShow;



    public AdminForm(){

        add(jPanel);
        setBounds(200, 200, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ADMIN FORM");
        insertTheaaterCB();
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        setColumns();



        USERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewUserForm newUserForm = new NewUserForm();
                newUserForm.setVisible(true);
            }
        });
        THEATERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CinemaForm theaterForm = new CinemaForm();
                theaterForm.setVisible(true);
            }
        });
        SHOWButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ShowForm showForm = new ShowForm();
                showForm.setVisible(true);
            }
        });
        TERMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TermForm termForm = new TermForm();
                termForm.setVisible(true);
            }
        });
        ACTORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            ActorForm actorForm = new ActorForm();
            actorForm.setVisible(true);
            }
        });
        cbTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbShow.removeAllItems();
                String nazivPozorista = cbTheater.getSelectedItem().toString();
                Cinema cinema = new Cinema();
                Show show = new Show();
                cinema.settheaterName(nazivPozorista);
                idTheater = (int) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_ID).getResponse();

                List<Show> listShow = (List<Show>) TransferClass.transferKlasa(show, idTheater, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_DATA).getResponse();
                for (Show show1 : listShow) {
                        cbShow.addItem(show1.getshowName());
                }
            }
        });
        cbShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (cbShow.getSelectedItem() != null) {
                    String nazivPredstave = cbShow.getSelectedItem().toString();
                    Show show = new Show();
                    dtm.setRowCount(0);
                    Term term = new Term();
                    show.setshowName(nazivPredstave);
                    idShow = (int) TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_ID).getResponse();

                    Map<Term, Integer> hmList = (Map<Term, Integer>) TransferClass.transferKlasa(term, idShow, ConstantFC.TERM, ConstantBLC.DATA_RETURN_FRONT_PAGE).getResponse();
                    for (Map.Entry<Term, Integer> hash: hmList.entrySet()) {
                            Object [] red = {hash.getKey().getdate(), hash.getKey().gettime(),
                                    hash.getKey().getticketsNumber(), hash.getValue()};
                            dtm.addRow(red);
                    }
                }
            }
        });

        RESERVATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdminReservationsForm adminReservationsForm = new AdminReservationsForm();
                adminReservationsForm.setVisible(true);
            }
        });
    }

        private void setColumns() {
        Object[] kolone = {"Date", "Time", "Total number of tickets", "Reserved tickets number" };
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
    }

    private void insertTheaaterCB() {
        Cinema cinema = new Cinema();
        List<String> namesTheater = (List<String>) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_NAMES).getResponse();
        for (String str: namesTheater             ) {
            cbTheater.addItem(str);
        }
    }

    @Override
    public void login(User user) {
        setVisible(true);
    }
}
