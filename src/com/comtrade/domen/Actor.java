package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actor implements GeneralDomen {

    private int idActor;
    private String name;
    private String lastName;

    public Actor(int idActor, String name, String lastName) {
        this.idActor = idActor;
        this.name = name;
        this.lastName = lastName;
    }

    public Actor(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Actor(){

    }


    public int getidActor() {
        return idActor;
    }

    public void setidActor(int idActor) {
        this.idActor = idActor;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String returnTableName() {
        return "actor";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
        Actor actor = null;
        try {
            actor = new Actor(resultSet.getInt("idActor"), resultSet.getString("name"),
                    resultSet.getString("lastName"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return actor;
    }

    @Override
    public String returnColumnsSave() {
        return " (name, lastName) ";
    }

    @Override
    public String returnValues() {
        return " (?,?) ";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getname());
            preparedStatement.setString(2, getlastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnRemove() {
        return "idActor";
    }

    @Override
    public String returnValueQuestionMark() {
        return "?";
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
    public String columnName() {
        return "idActor";
    }

    @Override
    public String IDColumnName() {
        return "idActor";
    }

    @Override
    public String NameColumnName() {
        return "(name, lastName)";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getname());
            preparedStatement.setString(2, getlastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnValueQuestionMarkID() {
        return "(?, ?)";
    }

    @Override
    public String returnSpecificClumnName() {
        return "null";
    }
}
