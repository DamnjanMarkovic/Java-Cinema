package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cinema implements GeneralDomen {

    private int idTheater;
    private String theaterName;
    private String street;
    private int number;
    private String city;

    public Cinema(int idTheater, String theaterName, String street, int number, String city) {
        this.idTheater = idTheater;
        this.theaterName = theaterName;
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public Cinema(String theaterName, String street, int number, String city) {
        this.theaterName = theaterName;
        this.street = street;
        this.number = number;
        this.city = city;
    }
    public Cinema(){

    }

    public int getidTheater() {
        return idTheater;
    }

    public void setidTheater(int setidTheater) {        this.idTheater = setidTheater;    }

    public String gettheaterName() {
        return theaterName;
    }

    public void settheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getstreet() {
        return street;
    }

    public void setstreet(String street) {
        this.street = street;
    }

    public int getnumber() {
        return number;
    }

    public void setnumber(int broj) {
        this.number = number;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    @Override
    public String returnTableName() {
        return "cinema";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
        Cinema cinema =null;
        try {
            cinema = new Cinema(resultSet.getInt("idCinema"),
                    resultSet.getString("nameCinema"), resultSet.getString("street"),
                    resultSet.getInt("number"), resultSet.getString("city"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return cinema;
    }

    @Override
    public String returnColumnsSave() {
        return " (nameCinema, street, number, city) ";
    }

    @Override
    public String returnValues() {
        return " (?,?,?,?) ";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, gettheaterName());
            preparedStatement.setString(2, getstreet());
            preparedStatement.setInt(3, getnumber());
            preparedStatement.setString(4, getcity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnRemove() {
        return " idCinema ";
    }

    @Override
    public String returnValueQuestionMark() {
        return " ? ";
    }

    @Override
    public String returnColumnsUpdate() {
        return " nameCinema = ?, street = ?, number = ?, city = ? ";
    }




    @Override
    public String updateOnValue() {
        return " idCinema = ? ";
    }

    @Override
    public PreparedStatement returnPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, gettheaterName());
            preparedStatement.setString(2, getstreet());
            preparedStatement.setInt(3, getnumber());
            preparedStatement.setString(4, getcity());
            preparedStatement.setInt(5, getidTheater());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String columnName() {
        return "nameCinema";
    }

    @Override
    public String IDColumnName() {
        return "idCinema";
    }

    @Override
    public String NameColumnName() {
        return "nameCinema";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, gettheaterName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnValueQuestionMarkID() {
        return " ? ";
    }

    @Override
    public String returnSpecificClumnName() {
        return "nameCinema";
    }


}
