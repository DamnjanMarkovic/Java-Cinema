package com.comtrade.systemOperation.theater;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Cinema;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationTheaterEntry extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Cinema cinema = (Cinema) transferClass.getRequest();
        Broker broker = new Broker();
        broker.save(cinema);

    }
}
