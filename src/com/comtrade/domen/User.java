package com.comtrade.domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements GeneralDomen {

    private int idUser;
    private String username;
    private String password;
    private String telephone;
    private String mail;
    private int status;
    public User(){

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int idUser, String username, String password, String telephone, String mail, int status) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.mail = mail;
        this.status = status;
    }

    public User(String username, String password, String telephone, String mail, int status) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.mail = mail;
        this.status = status;
    }

    public int getidUser() {
        return idUser;
    }

    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String gettelephone() {
        return telephone;
    }

    public void settelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String returnTableName() {
        return " user ";
    }

    @Override
    public GeneralDomen returnObjects(ResultSet resultSet) {
        User user = null;
        try {
            user = new User(resultSet.getInt("idUser"), resultSet.getString("username"),
                    resultSet.getString("password"), resultSet.getString("phone"),
                    resultSet.getString("mail"), resultSet.getInt("status"));


        } catch (SQLException e) {
            e.printStackTrace();
        } return user;


    }

    @Override
    public String returnColumnsSave() {
        return " (username, password, phone, mail, status) ";
    }

    @Override
    public String returnValues() {
        return " (?,?,?,?,?) ";
    }

    @Override
    public PreparedStatement returnPreparedStatementSave(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, gettelephone());
            preparedStatement.setString(4, getMail());
            preparedStatement.setInt(5, getStatus());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public String returnColumnRemove() {
        return " idUser ";
    }

    @Override
    public String returnValueQuestionMark() {
        return " ? ";
    }

    @Override
    public String returnColumnsUpdate() {
        return " username = ?, password = ?, phone = ?, mail = ? ";
    }

    @Override
    public String updateOnValue() {
        return " idUser = ? ";
    }

    @Override
    public PreparedStatement returnPreparedStatementUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
            preparedStatement.setString(3, gettelephone());
            preparedStatement.setString(4, getMail());
            preparedStatement.setInt(5, getidUser());
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
        return "idUser";
    }

    @Override
    public String NameColumnName() {
        return "username = ? and password";
    }

    @Override
    public PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getPassword());
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
        return "username";
    }
}
