package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ShowForm extends JFrame{
    private JPanel jPanel;
    private JComboBox cbTheater;
    private JTextField tfShow;
    private JButton ButtonSave;
    private JButton ButtonUpdate;
    private JButton ButtonRemove;
    private JTable table1;
    private JScrollPane jScrollPane;
    private JLabel lblidPozorista;
    private JLabel lblIDPozorista;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idTheater;
    private int idShow;

    public ShowForm(){

        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SHOW FORM");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        importTheatersCB();
        setColumns();




        cbTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    dtm.setRowCount(0);
                    String theaterName = cbTheater.getSelectedItem().toString();
                    Cinema cinema = new Cinema();
                    cinema.settheaterName(theaterName);
                    idTheater = (int) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_ID).getResponse();
                    setDataInTable();

            }
        });
        ButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String nameShow = tfShow.getText();
                Show show = new Show();
                show.setshowName(nameShow);
                show.setidTheater(idTheater);
                TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_DATA_ENTRY);
                setDataInTable();
                clearFields();

            }
        });
        ButtonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try  {
                Show show = new Show();
                show.setidShow(idShow);
                TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_REMOVE);
                setDataInTable();
                    clearFields();
                }   catch(Exception e){
                e.printStackTrace();
                }

            }
        });


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idShow = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
                tfShow.setText(table1.getModel().getValueAt(red, 1).toString());
            }
        });
        ButtonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    String nazivPredstave = tfShow.getText();
                    Show show = new Show(idShow, nazivPredstave, idTheater);
                    TransferClass.transferKlasa(show, 0, ConstantFC.SHOW, ConstantBLC.SHOW_NAMES_UPDATE);
                    setDataInTable();
                    clearFields();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "You are not authorized to delete!");
                }
            }
        });
    }
    private void setDataInTable() {
            dtm.setRowCount(0);
            Show show = new Show();
            List<Show> listShow = (List<Show>) TransferClass.transferKlasa(show, idTheater, ConstantFC.SHOW, ConstantBLC.SHOW_RETURN_DATA).getResponse();
            for (Show show1 : listShow) {
                Object [] red = {show1.getidShow(), show1.getshowName(), show1.getidTheater()};
                dtm.addRow(red);
            }
    }
    private void setColumns() {
        Object[]kolone = {"idShow", "Show name", "idTheater"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);

    }
    private void importTheatersCB() {
        Cinema cinema = new Cinema();
        List<String> theatersNames = (List<String>) TransferClass.transferKlasa(cinema, 0, ConstantFC.THEATER, ConstantBLC.THEATER_RETURN_NAMES).getResponse();
        for (String str: theatersNames             ) {
            cbTheater.addItem(str);
        }
    }

    private void clearFields() {
        tfShow.setText("");
    }
}
