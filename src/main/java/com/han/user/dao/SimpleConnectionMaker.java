package com.han.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker {

    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        // 생략가능
        Class.forName("com.mysql.jdbc.Driver");

        // 1. DB 연결을 위한 Connection 을 가져온다.
        return DriverManager.getConnection(
                "jdbc:mysql://han-dev.czcyu1ss9tvf.us-east-1.rds.amazonaws.com:3306/mydb", "han2015", "hjh20150101"
        );
    }
}
