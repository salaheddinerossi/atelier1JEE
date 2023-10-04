package com.example.demo4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    private static String url="jdbc:mysql://localhost/atelier1";
    private static String user="root";
    private static String password="";
    private static Connection myConnection;

    private ConnectionJDBC ()throws SQLException,ClassNotFoundException {

        System.out.println("connecting");
        Class.forName("com.mysql.cj.jdbc.Driver");
        myConnection= DriverManager.getConnection(url,user,password);
    }

    public static Connection getInstance() throws SQLException, ClassNotFoundException{
        if(myConnection == null){

            new ConnectionJDBC();


        }



        return myConnection ;

    }
}
