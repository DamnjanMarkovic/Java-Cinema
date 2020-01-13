package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Term implements GeneralDomen {

    private int idTerm;
    private String time;
    private Date date;
    private int ticketsNumber;
    private int idShow;

    public Term(String time, Date date, int ticketsNumber, int idShow) {
        this.date = date;
        this.time = time;
        this.ticketsNumber = ticketsNumber;
        this.idShow = idShow;
    }

    public Term(int idTerm, String time, Date date, int ticketsNumber, int idShow) {
        this.idTerm = idTerm;
        this.date = date;
        this.time = time;
        this.ticketsNumber = ticketsNumber;
        this.idShow = idShow;
    }

    public Term(){

    }


    public int getidTerm() {
        return idTerm;
    }

    public void setidTerm(int idTerm) {
        this.idTerm = idTerm;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    public int getticketsNumber() {
        return ticketsNumber;
    }

    public void setticketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    public int getidShow() {
        return idShow;
    }

    public void setidShow(int idShow) {
        this.idShow = idShow;
    }

    @Override
    public String returnTableName() {
        return "term";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
        Term term = null;
        try {
            term = new Term(resultSet.getInt("idTerm"), resultSet.getString("time"),
                    resultSet.getDate("date"), resultSet.getInt("numberofTickets"),
                    resultSet.getInt("idShow"));


        } catch (SQLException e) {
            e.printStackTrace();
        } return term;


    }

    @Override
    public String returnColumnsSave() {
        return " (time, date, numberofTickets, idShow) ";
    }

    @Override
    public String returnValues() {
        return " (?,?,?,?) ";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, gettime());
            preparedStatement.setDate(2, (java.sql.Date) getdate());
            preparedStatement.setInt(3, getticketsNumber());
            preparedStatement.setInt(4, getidShow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnRemove() {
        return " idTerm ";
    }

    @Override
    public String returnValueQuestionMark() {
        return " ? ";
    }

    @Override
    public String returnColumnsUpdate() {
        return " time = ?, date = ?, numberofTickets = ? ";
    }

    @Override
    public String updateOnValue() {
        return " idTerm = ? ";
    }

    @Override
    public PreparedStatement returnPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, gettime());
            preparedStatement.setDate(2, (java.sql.Date) getdate());
            preparedStatement.setInt(3, getticketsNumber());
            preparedStatement.setInt(4, getidTerm());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String columnName() {
        return null;
    }

    @Override
    public String IDColumnName() {
        return "idTerm";
    }

    @Override
    public String NameColumnName() {
        return " (date, time, idShow) ";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setDate(1, (java.sql.Date) getdate());
            preparedStatement.setString(2, gettime());
            preparedStatement.setInt(3, getidShow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }





    @Override
    public String returnValueQuestionMarkID() {
        return " (?,?,?) ";
    }

    @Override
    public String returnSpecificClumnName() {
        return null;
    }


}
