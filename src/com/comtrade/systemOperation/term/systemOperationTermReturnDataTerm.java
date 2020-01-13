package com.comtrade.systemOperation.term;

import com.comtrade.broker.Broker;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.Term;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.*;

public class systemOperationTermReturnDataTerm extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {
        Term term = (Term) transferClass.getRequest();
        int idPredstave = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(vratiPodatkeTerminiMetoda(broker.returnData(term), idPredstave));
    }

    public List<Term> vratiPodatkeTerminiMetoda(List<GeneralDomen> listaGeneralDomen, int idPredstave) {
        List<Term>listaTermina = new ArrayList<>();

        for (GeneralDomen opsd: listaGeneralDomen) {
            listaTermina.add((Term) opsd);
        }
        List<Term>listaTermina1 = new ArrayList<>();
        Map<Integer, Term> hm = new HashMap<>();
        for (int i = 0; i < listaTermina.size(); i++) {
            hm.put(listaTermina.get(i).getidTerm(), listaTermina.get(i));
        }
        for (Map.Entry<Integer, Term> s: hm.entrySet() ) {
            if (s.getValue().getidShow() == idPredstave )

                listaTermina1.add(s.getValue());
        }
        return listaTermina1;
    }

}
