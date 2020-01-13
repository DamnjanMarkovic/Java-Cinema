package com.comtrade.systemOperation.show;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Show;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationShowReturnNames extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {
        Show show = (Show) transferClass.getRequest();
        int idKorisnika = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(broker.vratiIme(show, idKorisnika));
    }
}
