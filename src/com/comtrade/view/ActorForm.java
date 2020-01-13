package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ActorForm extends JFrame{
    private JPanel jPanel;
    private JComboBox cbTheater;
    private JComboBox cbshow;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JTextField tfActorName;
    private JTextField tfActorLastName;
    private JButton SAVEButton;
    private JButton REMOVEButton;

    private JButton ACTORSButton;
    private JTable table2;
    private JScrollPane jScrollPane2;
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private int idTheater;
    private int idShow;
    private int idActor;


    public ActorForm(){

        add(jPanel);
        setBounds(200, 200, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ACTOR FORM");
        insertTheaterInCB();
        table1 = new JTable(dtm);
        table2=new JTable(dtm2);
        jScrollPane.setViewportView(table1);
        jScrollPane2.setViewportView(table2);
        setColumns2();
        setColumns();


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                tfActorName.setText(table1.getModel().getValueAt(red, 0).toString());
                tfActorLastName.setText(table1.getModel().getValueAt(red, 1).toString());

            }
        });

        cbTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cbshow.removeAllItems();
                String theaterName = cbTheater.getSelectedItem().toString();
                Cinema cinema = new Cinema();
                cinema.settheaterName(theaterName);
                idTheater = (int) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_ID).getResponse();
                setShowInCB();

            }
        });
        cbshow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (cbshow.getSelectedItem() != null) {
                    String theaterName = cbshow.getSelectedItem().toString();
                    Show show = new Show();
                    show.setshowName(theaterName);
                    idShow = (int) TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_ID).getResponse();
                    setDataInTable(idShow);
                }
            }
        });
        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name= tfActorName.getText();
                String lastName = tfActorLastName.getText();
                Actor actor = new Actor(name, lastName);

                TransferClass.transferKlasa(actor, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_SAVE);
                idActor= (int) TransferClass.transferKlasa(actor, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_RETURN_ID).getResponse();
                Actor_Show actorShow = new Actor_Show(idShow, idActor);
                TransferClass.transferKlasa(actorShow, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_SHOW_SAVE);
            }
        });

        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try  {
                    Actor actor = new Actor();
                    actor.setidActor(idActor);
                    TransferClass.transferKlasa(actor, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_REMOVE);
                    clearFields();
                    setDataInTable2();
                }   catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table2.getSelectedRow();
                idActor= Integer.parseInt(table2.getModel().getValueAt(red, 0).toString());
            }
        });
        ACTORSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setDataInTable2();
            }
        });
    }

    private void setColumns() {
        Object[]kolone = {"actor name", "actor lastName"};
            dtm.addColumn(kolone[0]);
            dtm.addColumn(kolone[1]);
    }

    private void clearFields() {
        tfActorName.setText("");
        tfActorLastName.setText("");
    }

    private void setDataInTable(int idPredstave) {

        dtm.setRowCount(0);
        Actor actor = new Actor();
        List<Integer>listIDActorsInShow = (List<Integer>) TransferClass.transferKlasa(actor, idPredstave, ConstantFC.ACTOR, ConstantBLC.ACTOR_RETURN_SHOW).getResponse();
        actor = new Actor();
        List<Actor> listActors = (List<Actor>) TransferClass.transferKlasa(actor, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_RETURN_DATA).getResponse();


        for (Actor actor1 : listActors) {
            for (Integer idActor:listIDActorsInShow                 ) {
                if (idActor== actor1.getidActor()){
                    Object [] red = {actor1.getname(), actor1.getlastName()};
                    dtm.addRow(red);
                }
            }
        }
    }

    private void setShowInCB() {
        Show show = new Show();
        List<Show> listShow = (List<Show>) TransferClass.transferKlasa(show, idTheater, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_DATA).getResponse();
        for (Show show1 : listShow) {
            cbshow.addItem(show1.getshowName());
        }
    }
    private void insertTheaterInCB() {
        Cinema cinema = new Cinema();
        List<String> theaterNames = (List<String>) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_NAMES).getResponse();
        for (String str: theaterNames             ) {
            cbTheater.addItem(str);
        }
    }

    private void setColumns2() {
        Object[]kolone = {"idActor", "Actor name", "Actor lastname"};
        dtm2.addColumn(kolone[0]);
        dtm2.addColumn(kolone[1]);
        dtm2.addColumn(kolone[2]);
    }


    private void setDataInTable2() {
        dtm2.setRowCount(0);
        Actor actor = new Actor();
        List<Actor> listaGlumaca = (List<Actor>) TransferClass.transferKlasa(actor, 0, ConstantFC.ACTOR, ConstantBLC.ACTOR_RETURN_DATA).getResponse();
        for (Actor actor1 : listaGlumaca) {
            Object [] red = {actor1.getidActor(), actor1.getname(), actor1.getlastName()};
            dtm2.addRow(red);
        }
    }
}
