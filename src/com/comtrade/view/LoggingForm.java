package com.comtrade.view;

import com.comtrade.domen.User;
import com.comtrade.view.proxy.Proxy;
import com.comtrade.view.proxy.ProxyLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggingForm extends JFrame{
    private JPanel jPanel;
    private JTextField tfUser;
    private JPasswordField pfPassword;
    private JButton LOGINButton;
    private JLabel tfUserName;
    private JButton NEWUSERButton;


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LoggingForm loggingForm = new LoggingForm();
            loggingForm.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    public LoggingForm() {

        add(jPanel);
        setBounds(200,200,400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("LOGGING FORM");


        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String username=tfUser.getText();
                char[] niz = pfPassword.getPassword();
                String password = String.copyValueOf(niz);
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                Proxy proxy=new ProxyLogin();
                proxy.login(user);
                tfUser.setText("");
                pfPassword.setText("");
            }
        });

    }
}
