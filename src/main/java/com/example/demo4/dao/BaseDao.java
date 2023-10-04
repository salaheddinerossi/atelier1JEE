package com.example.demo4.dao;

import com.example.demo4.ConnectionJDBC;

import java.sql.*;

public abstract class BaseDao{
    protected Connection myConnection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    BaseDao() throws SQLException, ClassNotFoundException {

        this.myConnection = ConnectionJDBC.getInstance();
    }



}
