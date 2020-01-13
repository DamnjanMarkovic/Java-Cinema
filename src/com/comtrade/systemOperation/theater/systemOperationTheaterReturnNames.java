package com.comtrade.systemOperation.theater;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Cinema;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.TransferClass;

import java.util.ArrayList;
import java.util.List;

public class systemOperationTheaterReturnNames extends com.comtrade.systemOperation.MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {
        Cinema cinema = (Cinema) transferClass.getRequest();

        Broker broker = new Broker();
        transferClass.setResponse(returnData(broker.returnData(cinema)));
    }
    public List<String> returnData(List<GeneralDomen> listaGeneralDomen) {

        List<Cinema> list = new ArrayList<>();
        List<String> listNames = new ArrayList<>();
        for (GeneralDomen opsd: listaGeneralDomen) {
            list.add((Cinema) opsd);
        }
        for (Cinema cinema : list             ) {
            listNames.add(cinema.gettheaterName());

        }
        return listNames;

    }
}
