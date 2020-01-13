package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation implements GeneralDomen {

    private int idReservation;
    private int numberOfTickets;
    private int idUser;
    private int idTerm;

    public Reservation(int numberOfTickets, int idUser, int idTerm) {
        this.numberOfTickets = numberOfTickets;
        this.idUser = idUser;
        this.idTerm = idTerm;
    }

    public Reservation(int idReservation, int numberOfTickets, int idUser, int idTerm) {
        this.idReservation = idReservation;
        this.numberOfTickets = numberOfTickets;
        this.idUser = idUser;
        this.idTerm = idTerm;
    }

    public Reservation(){

    }
    public int getidReservation() {
        return idReservation;
    }

    public void setidReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getnumberOfTickets() {
        return numberOfTickets;
    }

    public void setnumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public int getidUser() {
        return idUser;
    }

    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    public int getidTerm() {
        return idTerm;
    }

    public void setidTerm(int idTerm) {
        this.idTerm = idTerm;
    }

    @Override
    public String returnTableName() {
        return " reservation ";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
        return null;
    }

    @Override
    public String returnColumnsSave() {
        return " (numberofTickets, idUser, idTerm) ";
    }

    @Override
    public String returnValues() {
        return " (?,?,?) ";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, getnumberOfTickets());
            preparedStatement.setInt(2, getidUser());
            preparedStatement.setInt(3, getidTerm());

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
    public String columnName() {
        return null;
    }

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
