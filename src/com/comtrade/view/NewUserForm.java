package com.comtrade.view;

import com.comtrade.domen.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class NewUserForm extends JFrame{
    private JPanel jPanel;
    private JComboBox comboBox1;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfTelephone;
    private JTextField tfMail;
    private JButton NEWUSERButtoon;
    private JButton UPDATEButton;
    private JButton REMOVEButton;
    private JTable table1;
    private JScrollPane jScrollPane;
    private DefaultTableModel dtm = new DefaultTableModel();
    private int idUser;
    private int status;

    public NewUserForm(){


        add(jPanel);
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("NEW USER FORM");
        table1 = new JTable(dtm);
        jScrollPane.setViewportView(table1);
        setDataCB();
        setColumns();
        setDateInTable();



        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String tekstCB =  comboBox1.getSelectedItem().toString();
                if (tekstCB.equalsIgnoreCase("administrator"))status = 1;
                else if (tekstCB.equalsIgnoreCase("registered user")) status=2;

            }
        });
        NEWUSERButtoon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String username = tfUsername.getText();
                String password = tfPassword.getText();
                String telefon = tfTelephone.getText();
                String mail = tfMail.getText();

                User user = new User(username, password, telefon, mail, status);

                TransferClass.transferKlasa(user, 0, ConstantFC.USER, ConstantBLC.USER_DATA_ENTRY);
                setDateInTable();
                izbrisiFildove();


            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int red = table1.getSelectedRow();
                idUser = Integer.parseInt(table1.getModel().getValueAt(red, 0).toString());
                tfUsername.setText(table1.getModel().getValueAt(red, 1).toString());
                tfPassword.setText(table1.getModel().getValueAt(red, 2).toString());
                tfTelephone.setText((table1.getModel().getValueAt(red, 3).toString()));
                tfMail.setText(table1.getModel().getValueAt(red, 4).toString());


            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
             User user = new User(idUser, tfUsername.getText(), tfPassword.getText(),
                        tfTelephone.getText(), tfMail.getText(), status);
                TransferClass.transferKlasa(user, 0, ConstantFC.USER, ConstantBLC.USER_DATA_UPDATE);
                setDateInTable();
                izbrisiFildove();
            }
        });
        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                User user = new User();
                user.setidUser(idUser);
                TransferClass.transferKlasa(user, 0, ConstantFC.USER, ConstantBLC.USER_REMOVE);
                setDateInTable();
                izbrisiFildove();

            }
        });
    }

    private void izbrisiFildove() {
        tfUsername.setText("");
        tfPassword.setText("");
        tfMail.setText("");
        tfTelephone.setText("");
    }


    private void setDateInTable() {
        dtm.setRowCount(0);
        User user = new User();
        List<User> listaPredstava = (List<User>) TransferClass.transferKlasa(user, 0, ConstantFC.USER, ConstantBLC.USER_RETURN_DATA).getResponse();
        for (User user1 : listaPredstava) {
            Object [] red = {user1.getidUser(), user1.getUsername(), user1.getPassword(), user1.gettelephone(), user1.getMail(), user1.getStatus()};

                dtm.addRow(red);
        }
    }

    private void setColumns() {
        Object[] kolone = {"idUser", "username", "password", "telephone", "mail", "status"};
        dtm.addColumn(kolone[0]);
        dtm.addColumn(kolone[1]);
        dtm.addColumn(kolone[2]);
        dtm.addColumn(kolone[3]);
        dtm.addColumn(kolone[4]);
        dtm.addColumn(kolone[5]);
    }


    private void setDataCB() {
        comboBox1.addItem("administrator");
        comboBox1.addItem("registered user");
    }

}
