package com.comtrade.systemOperation.user;

import com.comtrade.broker.Broker;
import com.comtrade.domen.User;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationUserReturnName extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        User user = (User) transferClass.getRequest();
        int idKorisnika = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(broker.vratiIme(user, idKorisnika));

    }
}
