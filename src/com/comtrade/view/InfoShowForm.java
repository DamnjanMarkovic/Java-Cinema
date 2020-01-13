package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InfoShowForm extends JFrame {
    private JPanel jPanel;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JLabel lblTheater;
    private JLabel lblShow;
    private DefaultTableModel dtm = new DefaultTableModel();


    public InfoShowForm(int idTheater, int idShow) {

        add(jPanel);
        setBounds(200, 200, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SHOW INFO");

        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        setColumns();
        setDataInLabels(idTheater, idShow);
        setDataInTable(idShow);
        setDataInTable(idShow);
    }

    private void setDataInLabels(int idTheater, int idShow) {
        Cinema cinema = new Cinema();
        String theaterName = (String) TransferClass.transferKlasa(cinema, idTheater, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_NAME).getResponse();
        lblTheater.setText(theaterName);
        Show show = new Show();
        String nazivPredstave = (String) TransferClass.transferKlasa(show, idShow, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_NAMES).getResponse();
        lblShow.setText(nazivPredstave);
    }

    private void setColumns() {

        Object[] kolone = {"Actor name", "Actor last name"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
    }

    private void setDataInTable(int idShow) {

        dtm.setRowCount(0);
        Actor actor = new Actor();
        List<Integer> listIDActorsInShow = (List<Integer>) TransferClass.transferKlasa(actor, idShow, ConstantFC.ACTOR, ConstantBLC.ACTOR_RETURN_SHOW).getResponse();
        actor = new Actor();
        List<Actor> actorList = (List<Actor>) TransferClass.transferKlasa(actor, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_RETURN_DATA).getResponse();


        for (Actor actor1 : actorList) {
            for (Integer idActor : listIDActorsInShow) {
                if (idActor == actor1.getidActor()) {
                    Object[] red = {actor1.getname(), actor1.getlastName()};
                    dtm.addRow(red);
                }
            }
        }


    }
}
