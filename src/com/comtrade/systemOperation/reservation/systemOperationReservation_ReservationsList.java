package com.comtrade.systemOperation.reservation;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Reservation;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationReservation_ReservationsList extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Reservation reservation = (Reservation) transferClass.getRequest();
        int idTermina = transferClass.getMessage();
        //int idTermina = Integer.parseInt(idTerminaString);
        Broker broker = new Broker();
        transferClass.setResponse(broker.vratiPregledRezervacija(idTermina));



    }
}
