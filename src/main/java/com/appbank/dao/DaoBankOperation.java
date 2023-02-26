package com.appbank.dao;

import com.appbank.connection.SingleConnection;
import com.appbank.models.ModelRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoBankOperation {
    private Connection connection;

    public DaoBankOperation() {
        connection = SingleConnection.getConnection();
    }

    public Boolean deposit(double value, Integer Id) {
        try {
            String sql = "UPDATE account SET balance= balance + ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, value);
            statement.setInt(2, Id);
            Integer dataReturn = statement.executeUpdate();
            connection.commit();
            return dataReturn > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean withdraw(double value, Integer Id) {
        try {
            if (verifyBalance(value, Id)) {

                String sql = "UPDATE account SET balance = balance - ? WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, value);
                statement.setInt(2, Id);
                Integer dataReturn = statement.executeUpdate();
                connection.commit();
                return dataReturn > 0;

            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean transfer(String accNumber, String agencyNumber, Double value, int id) throws SQLException {
        try {
            if (verifyBalance(value, id)) {
                String sql1 = "UPDATE account SET balance=balance + ? WHERE account_number=? AND agency=?";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setDouble(1, value);
                statement1.setString(2, accNumber);
                statement1.setString(3, agencyNumber);
                int dataReturn = statement1.executeUpdate();

                if (dataReturn == 1) {
                    withdraw(value, id);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }
    }

//    public int withdrawTransfer(double value, Integer Id) {
//        try {
//            if (verifyBalance(value, Id)) {
//
//                String sql = "UPDATE account SET balance = balance - ? WHERE id=?";
//                PreparedStatement statement = connection.prepareStatement(sql);
//                statement.setDouble(1, value);
//                statement.setInt(2, Id);
//                statement.executeUpdate();
//                connection.commit();
//                return 1;
//
//            } else {
//                return 0;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }


    public Boolean verifyBalance(Double value, int Id) {
        try {
            String sql = "SELECT * FROM account WHERE balance >= ? AND id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, value);
            statement.setInt(2, Id);
            ResultSet query = statement.executeQuery();

            return query.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ModelRegister balance(String email, String password) {
        try {
            ModelRegister modelRegister = new ModelRegister();
            String sql = "SELECT * FROM account WHERE email=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet query = statement.executeQuery();
            connection.commit();
            while (query.next()) {
                modelRegister.setBalance(Double.valueOf(query.getString("balance")));
            }
            return modelRegister;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ModelRegister balanceById(Long id) {
        try {
            ModelRegister modelRegister = new ModelRegister();
            String sql = "SELECT * FROM account WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet query = statement.executeQuery();
            connection.commit();
            while (query.next()) {
                modelRegister.setBalance(Double.valueOf(query.getString("balance")));
            }
            return modelRegister;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
