package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface GeneralDomen {


    String returnTableName(); //vratiNazivTabele();

    String returnColumnsSave();//vratiKoloneSave

    String returnValues ();//vratiVrednosti

    PreparedStatement returnPreparedStatementSave (PreparedStatement preparedStatement); //vratiPreparedStatementSave

    GeneralDomen returnObjects(ResultSet resultSet); // procitajObjekte

    String returnColumnRemove ();//vratiKolonuBrisanje

    String returnValueQuestionMark ();//vratiVrednostUpitnik

    String returnColumnsUpdate ();//vratiKoloneIzmena

    String updateOnValue ();//izmenePoVrednosti

    PreparedStatement returnPreparedStatementUpdate (PreparedStatement preparedStatement);//vratiPreparedStatementUpdate

    String columnName();//nazivKolone


    String IDColumnName ();//nazivIDKolone

    String NameColumnName();//nazivNazivKolone

    PreparedStatement returnIDPreparedStatement (PreparedStatement preparedStatement);//vratiIdPreparedStatement

    String returnValueQuestionMarkID ();//vratiVrednostUpitnikID

    String returnSpecificClumnName();//vratiImeTrazeneKolone
}
