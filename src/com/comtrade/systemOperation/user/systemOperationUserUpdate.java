package com.comtrade.systemOperation.user;

import com.comtrade.broker.Broker;
import com.comtrade.domen.User;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationUserUpdate extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        User user = (User) transferClass.getRequest();
        Broker broker = new Broker();

        broker.update(user);
    }
}
