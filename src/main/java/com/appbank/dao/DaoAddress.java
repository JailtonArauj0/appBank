package com.appbank.dao;

import com.appbank.connection.SingleConnection;
import com.appbank.models.ModelAddress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoAddress {
    private Connection connection;

    public DaoAddress() {
        connection = SingleConnection.getConnection();
    }

    public void insert(ModelAddress modelAddress) {
        try {
            String sql = "INSERT INTO address (street, \"number\", district, cep, city, uf, id_client) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelAddress.getStreet());
            statement.setString(2, modelAddress.getNumber());
            statement.setString(3, modelAddress.getDistrict());
            statement.setString(4, modelAddress.getCep());
            statement.setString(5, modelAddress.getCity());
            statement.setString(6, modelAddress.getUf());
            statement.setLong(7, modelAddress.getId_client());
            statement.execute();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ModelAddress modelAddress) {
        try {
            String sql = "UPDATE address SET street=?, \"number\"=?, district=?, cep=?, city=?, uf=? WHERE id_client = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelAddress.getStreet());
            statement.setString(2, modelAddress.getNumber());
            statement.setString(3, modelAddress.getDistrict());
            statement.setString(4, modelAddress.getCep());
            statement.setString(5, modelAddress.getCity());
            statement.setString(6, modelAddress.getUf());
            statement.setLong(7, modelAddress.getId_client());
            statement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userExistsById(Long Id) {
        try {
            String sql = "SELECT * FROM address WHERE id_client = " + Id;
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ModelAddress queryUserById(Long Id) {
        try {
            ModelAddress user = new ModelAddress();
            String sql = "SELECT * FROM address WHERE id_client = " + Id;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet query = statement.executeQuery();

            while (query.next()) {
                user.setStreet(query.getString("street"));
                user.setNumber(query.getString("number"));
                user.setDistrict(query.getString("district"));
                user.setCep(query.getString("cep"));
                user.setCity(query.getString("city"));
                user.setUf(query.getString("uf"));
                user.setId(query.getLong("id"));
                user.setId_client(query.getLong("id_client"));
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
