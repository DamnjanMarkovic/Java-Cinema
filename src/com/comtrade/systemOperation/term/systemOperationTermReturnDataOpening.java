package com.comtrade.systemOperation.term;

import com.comtrade.broker.Broker;
import com.comtrade.domen.*;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.*;

public class systemOperationTermReturnDataOpening extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Term term = (Term) transferClass.getRequest();
        Broker broker = new Broker();
        int idPredstave = transferClass.getMessage();
        transferClass.setResponse(broker.vratiPodatkeUlaznaStrana(term));
        transferClass.setResponse(konkretnaPozorista(broker.vratiPodatkeUlaznaStrana(term), idPredstave));

    }
    public Map<Term, Integer>  konkretnaPozorista(Map<Term, Integer> hmList, int idPredstave){
        Map<Term, Integer> finalHM = new HashMap<>();
        for (Map.Entry<Term, Integer> hash: hmList.entrySet()) {
            if (hash.getKey().getidShow()==idPredstave ){
                finalHM.put(hash.getKey(), hash.getValue());
            }
        }return finalHM;
    }
}
