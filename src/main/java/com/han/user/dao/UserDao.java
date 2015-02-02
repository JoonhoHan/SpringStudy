package com.han.user.dao;

import com.han.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection c = DriverManager.getConnection(
                "jdbc:mysql://han-dev.czcyu1ss9tvf.us-east-1.rds.amazonaws.com:3306/mydb", "han2015", "hjh20150101"
        );

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
