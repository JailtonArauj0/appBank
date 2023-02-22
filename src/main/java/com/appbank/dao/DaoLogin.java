package com.appbank.dao;

import com.appbank.connection.SingleConnection;
import com.appbank.models.ModelRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoLogin {
    private Connection connection;

    public DaoLogin() {
        connection = SingleConnection.getConnection();
    }

    public ModelRegister userExists(ModelRegister modelRegister) {
        try {
            ModelRegister queryReturn = new ModelRegister();
            String sql = "SELECT * FROM account WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelRegister.getEmail());
            statement.setString(2, modelRegister.getPassword());
            ResultSet query = statement.executeQuery();
            while (query.next()) {
                queryReturn.setId(query.getLong("id"));
                queryReturn.setEmail(query.getString("email"));
                queryReturn.setPassword(query.getString("password"));
                queryReturn.setAccountNumber(Integer.valueOf(query.getString("account_number")));
                queryReturn.setAgencyNumber(Integer.valueOf(query.getString("agency")));
            }
            return queryReturn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
