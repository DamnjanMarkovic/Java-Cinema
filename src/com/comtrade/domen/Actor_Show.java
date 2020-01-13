package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actor_Show implements GeneralDomen {

    private int id_actor_show;
    private int idShow;
    private int idActor;

    public Actor_Show(int idShow, int idActor) {
        this.idShow = idShow;
        this.idActor = idActor;
    }

    public int getid_actor_show() {
        return id_actor_show;
    }

    public void setid_actor_show(int id_actor_show) {
        this.id_actor_show = id_actor_show;
    }

    public int getidShow() {
        return idShow;
    }

    public void setidShow(int idShow) {
        this.idShow = idShow;
    }

    public int getidActor() {
        return idActor;
    }

    public void setidActor(int idActor) {
        this.idActor = idActor;
    }

    @Override
    public String returnTableName() {
        return "actor_show";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
        return null;
    }

    @Override
    public String returnColumnsSave() {
        return "(idShow, idActor)";
    }

    @Override
    public String returnValues () {
        return "(?,?)";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, getidShow());
            preparedStatement.setInt(2, getidActor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnRemove() {
        return null;
    }

    @Override
    public String returnValueQuestionMark() {
        return null;
    }

    @Override
    public String returnColumnsUpdate() {
        return null;
    }

    @Override
    public String updateOnValue() {
        return null;
    }

    @Override
    public PreparedStatement returnPreparedStatementUpdate(PreparedStatement preparedStatement) {
        return null;
    }

    @Override
    public String columnName() {        return null;    }


    @Override
    public String IDColumnName() {
        return null;
    }

    @Override
    public String NameColumnName() {
        return null;
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        return null;
    }

    @Override
    public String returnValueQuestionMarkID() {
        return null;
    }

    @Override
    public String returnSpecificClumnName() {
        return null;
    }
}
