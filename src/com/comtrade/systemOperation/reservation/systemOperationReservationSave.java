package com.comtrade.systemOperation.reservation;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Reservation;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationReservationSave extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Reservation reservation = (Reservation) transferClass.getRequest();
        Broker broker = new Broker();
        broker.save(reservation);


    }
}
