package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Show implements GeneralDomen {

    private int idShow;
    private String showName;
    private int idTheater;

    public Show(){

    }

    public Show(int idShow, String showName, int idTheater) {
        this.idShow = idShow;
        this.showName = showName;
        this.idTheater = idTheater;
    }

    public int getidShow() {
        return idShow;
    }

    public void setidShow(int idShow) {
        this.idShow = idShow;
    }

    public String getshowName() {
        return showName;
    }

    public void setshowName(String showName) {
        this.showName = showName;
    }

    public int getidTheater() {
        return idTheater;
    }

    public void setidTheater(int idTheater) {
        this.idTheater = idTheater;
    }

    @Override
    public String returnTableName() {
        return "`show`";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
     Show show = null;
        try {
            show = new Show(resultSet.getInt("idShow"), resultSet.getString("nameShow"),
                    resultSet.getInt("idCinema"));
        } catch (SQLException e) {
            e.printStackTrace();
        } return show;
    }

    @Override
    public String returnColumnsSave() {
        return " (nameShow, idCinema) ";
    }

    @Override
    public String returnValues() {
        return " (?,?) ";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getshowName());
            preparedStatement.setInt(2, getidTheater());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnRemove() {
        return " idShow ";
    }

    @Override
    public String returnValueQuestionMark() {
        return " ? ";
    }

    @Override
    public String returnColumnsUpdate() {
        return " nameShow = ? ";
    }

    @Override
    public String updateOnValue() {
        return " idShow = ? ";
    }

    @Override
    public PreparedStatement returnPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getshowName());
            preparedStatement.setInt(2, getidShow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String columnName() {
        return "nameShow";
    }

    @Override
    public String IDColumnName() {
        return "idShow";
    }

    @Override
    public String NameColumnName() {
        return "nameShow";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getshowName());
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
        return "nameShow";
    }
}
