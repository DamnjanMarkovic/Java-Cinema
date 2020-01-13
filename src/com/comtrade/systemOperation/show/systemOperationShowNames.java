package com.comtrade.systemOperation.show;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.Show;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.ArrayList;
import java.util.List;

public class systemOperationShowNames extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Show show = (Show) transferClass.getRequest();
        Broker broker = new Broker();
        transferClass.setResponse(vratiPodatke(broker.returnData(show)));
    }


    public List<String> vratiPodatke(List<GeneralDomen> listaGeneralDomen) {
        String imePredstave;
        List<String>listaImenaPredstava = new ArrayList<>();
        List<Show> listaShow = new ArrayList<>();
        for (GeneralDomen opsd: listaGeneralDomen) {
            listaShow.add((Show) opsd);
        }
        for (Show show : listaShow) {
            imePredstave = show.getshowName();
            listaImenaPredstava.add(imePredstave);
        }
        return listaImenaPredstava;
    }
}
