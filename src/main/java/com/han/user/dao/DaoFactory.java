package com.han.user.dao;

import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(connectionMaker());
        return userDao;
    }

    @Bean
    public DataSource connectionMaker() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://han-dev.czcyu1ss9tvf.us-east-1.rds.amazonaws.com:3306/mydb");
        dataSource.setUsername("han2015");
        dataSource.setPassword("hjh20150101");

        return dataSource;
    }
}
