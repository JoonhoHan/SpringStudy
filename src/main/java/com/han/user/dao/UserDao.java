package com.han.user.dao;

import com.han.user.domain.User;

import java.sql.*;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {

        // 생략가능
        Class.forName("com.mysql.jdbc.Driver");

        // 1. DB 연결을 위한 Connection 을 가져온다.
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://han-dev.czcyu1ss9tvf.us-east-1.rds.amazonaws.com:3306/mydb", "han2015", "hjh20150101"
        );

        // 2. SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        // 3. 만들어진 Statement를 실행한다.
        ps.executeUpdate();

        // 4. 생성된 Connection, Statement, ResultSet 같은 리소스는 작업을 마친 후 반드시 닫아준다.
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        // 생략가능
        Class.forName("com.mysql.jdbc.Driver");

        // 1. DB 연결을 위한 Connection 을 가져온다.
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://han-dev.czcyu1ss9tvf.us-east-1.rds.amazonaws.com:3306/mydb", "han2015", "hjh20150101"
        );

        // 2. SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );

        ps.setString(1, id);

        // 3. 만들어진 Statement를 실행한다.
        // 4. 조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트(여기서는 User)에 옮겨준다.
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // 5. 생성된 Connection, Statement, ResultSet 같은 리소스는 작업을 마친 후 반드시 닫아준다.
        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
