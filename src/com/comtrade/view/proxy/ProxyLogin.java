package com.comtrade.view.proxy;

import com.comtrade.domen.ConstantFC;
import com.comtrade.domen.ConstantBLC;
import com.comtrade.domen.User;
import com.comtrade.domen.TransferClass;
import com.comtrade.view.AdminForm;
import com.comtrade.view.ReservationForm;

import javax.swing.*;

public class ProxyLogin implements Proxy{





    @Override
    public void login(User user) {

        int status = (int) TransferClass.transferKlasa(user, 0, ConstantFC.USER, ConstantBLC.LOGGING).getResponse();

        if (status==1){
            AdminForm adminForm = new AdminForm();
            adminForm.login(user);
        }else if (status ==2){
            ReservationForm reservationForm = new ReservationForm(user);
            reservationForm.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null, "You are not registered user.");
        }
    }
}
