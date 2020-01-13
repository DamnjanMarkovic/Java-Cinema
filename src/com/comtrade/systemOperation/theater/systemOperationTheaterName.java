package com.comtrade.systemOperation.theater;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Cinema;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.ArrayList;
import java.util.List;

public class systemOperationTheaterName extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Cinema cinema = (Cinema) transferClass.getRequest();
        int idTheater = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(returnData(broker.returnData(cinema), idTheater));
    }
    public String returnData(List<GeneralDomen> listaGeneralDomen, int idTheater) {
        String theaterName=null;
        List<Cinema> list = new ArrayList<>();
        for (GeneralDomen opsd: listaGeneralDomen) {
            list.add((Cinema) opsd);
        }
        for (Cinema cinema : list             ) {
            if (cinema.getidTheater()==idTheater)
                theaterName = cinema.gettheaterName();
        }
        return theaterName;
    }
}
