package com.comtrade.systemOperation.show;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.Show;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.ArrayList;
import java.util.List;

public class systemOperationShowReturnColums extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Show show = (Show) transferClass.getRequest();
        int idTheater = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(specificOperation(broker.returnData(show), idTheater));
    }



    public List<Show>  specificOperation(List<GeneralDomen> listShow, int idTheater){
        List<Show> listaShow =new ArrayList<>();
        List<Show> listaShowFinal =new ArrayList<>();
        for (GeneralDomen show1: listShow){
            listaShow.add((Show) show1);
        }
        for (Show show : listaShow) {
            if (show.getidTheater() == idTheater)
                listaShowFinal.add(show);
        }return listaShowFinal;
    }
}
