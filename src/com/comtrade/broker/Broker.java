package com.comtrade.broker;

import com.comtrade.domen.*;
import com.comtrade.connection.Connect;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Broker {

    public List<GeneralDomen> returnData(GeneralDomen generalDomen) {
        List<GeneralDomen> listGeneralDomen = new ArrayList<>();
        String sql = "select * from " + generalDomen.returnTableName();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                generalDomen = (GeneralDomen) generalDomen.returnObjects(resultSet);
                listGeneralDomen.add(generalDomen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGeneralDomen;
    }

    public void save(GeneralDomen generalDomen) {
        String sql = "insert into " + generalDomen.returnTableName() + " " + generalDomen.returnColumnsSave() + " values "
                + generalDomen.returnValues();
        try {
            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement = generalDomen.returnPreparedStatementSave(preparedStatement);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(GeneralDomen generalDomen, int id) {
        String sql = "delete from " + generalDomen.returnTableName() + " where " + generalDomen.returnColumnRemove () +
                " = " + generalDomen.returnValueQuestionMark();
        try {
            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "You do not have authority to delete that specific item!");
        }
    }

    public void update(GeneralDomen generalDomen) {

        String sql = "update " + generalDomen.returnTableName() + " set " + generalDomen.returnColumnsUpdate() + " where "
                + generalDomen.updateOnValue();
        try {
            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement = generalDomen.returnPreparedStatementUpdate(preparedStatement);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int returnID(GeneralDomen generalDomen) {
        int idTrazeni = 0;
        String sql = "select " + generalDomen.IDColumnName() + " from " + generalDomen.returnTableName() +
                " where " + generalDomen.NameColumnName() + " = " + generalDomen.returnValueQuestionMarkID();
        try {

            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement = generalDomen.returnIDPreparedStatement (preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first())
                idTrazeni = resultSet.getInt(generalDomen.IDColumnName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idTrazeni;
    }


    public List<Object> vratiDatume(GeneralDomen generalDomen, String message) {
        List<Object> objectList = new ArrayList<>();
        String sql = " select " + message + " from " + generalDomen.returnTableName();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) objectList.add(resultSet.getString(message));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    public int vratiBrojUlaznica(Term term) {
        int totalTicketsNumber = 0;
        int numberofReserved = 0;
        int availableTicketsNumber = 0;

        String sql = "SELECT numberofTickets from term where idTerm = ?";
        try {
            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, term.getidTerm());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                totalTicketsNumber = resultSet.getInt("numberofTickets");
            }
            sql = "select sum(numberofTickets) as tickets from reservation where idTerm = ?";
            preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, term.getidTerm());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) numberofReserved = resultSet.getInt("tickets");
            availableTicketsNumber = totalTicketsNumber - numberofReserved;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableTicketsNumber;
    }

    public Map<Term, Integer> vratiPodatkeUlaznaStrana(GeneralDomen generalDomen) {
        Map<Term, Integer>hm = new HashMap<>();


        Term term = null;
        String sql = "select term.idTerm as idTerm, COALESCE((sum(reservation.numberofTickets)),0) as numberofTickets, " +
                "term.date as date, term.time as time, term.numberofTickets as totalNumberOfTickets, " +
                "term.idShow as idShow FROM term left join reservation on reservation.idTerm = term.idTerm " +
                "group by term.idTerm";

        try {
            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                term = new Term(resultSet.getString("time"), resultSet.getDate("date"),
                        resultSet.getInt("totalNumberOfTickets"), resultSet.getInt("idShow"));

                hm.put(term, resultSet.getInt("numberofTickets"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hm;
    }

    public List<Reservation> vratiPregledRezervacija(int idTerm) {

        Reservation reservation = new Reservation();
        List<Reservation> listReservation = new ArrayList<>();
        String sql = "select reservation.idReservation, reservation.numberofTickets, reservation.idUser FROM reservation where reservation.idTerm=? group by idReservation";
        try {
            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idTerm);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservation = new Reservation(resultSet.getInt("idReservation"),
                        resultSet.getInt("numberofTickets"), resultSet.getInt("idUser"), idTerm);

                listReservation.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReservation;
    }
    public String  vratiIme(GeneralDomen generalDomen, int idUser) {
        String username = null;
        String sql = "select " + generalDomen.returnSpecificClumnName() + " FROM " + generalDomen.returnTableName() + " where " + generalDomen.IDColumnName() + " = ?";
        try {

            PreparedStatement preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first())
                username = resultSet.getString(generalDomen.returnSpecificClumnName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;

    }


    public List<Integer> vratiIDGlumacaUPredstavi(int idShow) {
        List<Integer> objectList = new ArrayList<>();
        int idActor = 0;
        String sql = "select idActor from actor_show where idShow = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = Connect.getConnect().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idShow);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idActor = resultSet.getInt("idActor");
                objectList.add(idActor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }
}