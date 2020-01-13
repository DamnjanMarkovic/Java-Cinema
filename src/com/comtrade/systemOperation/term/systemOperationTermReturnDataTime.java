package com.comtrade.systemOperation.term;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.Term;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.*;

public class systemOperationTermReturnDataTime extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {
        Term term = (Term) transferClass.getRequest();
        int idPredstave = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(vratiPodatkeTerminiVremenaMetoda(broker.returnData(term), term.getdate(), idPredstave));
    }


    public List<String> vratiPodatkeTerminiVremenaMetoda(List<GeneralDomen> listaGeneralDomen, Date datum, int idPredstave) {
        List<Term>listaTermina = new ArrayList<>();
        for (GeneralDomen opsd: listaGeneralDomen) {
            listaTermina.add((Term) opsd);
        }
        List<String>listaVremena = new ArrayList<>();
        Map<Integer, Term> hm = new HashMap<>();
        for (int i=0; i<listaTermina.size(); i++) {
            hm.put(listaTermina.get(i).getidTerm(), listaTermina.get(i));
        }
        for (Map.Entry<Integer, Term> s: hm.entrySet() ) {
            if (s.getValue().getidShow() == idPredstave
                    && s.getValue().getdate().toString().equalsIgnoreCase(datum.toString())
                    && !listaVremena.contains(s.getValue().gettime()))

                listaVremena.add(s.getValue().gettime());
        }
        return listaVremena;
    }





}
