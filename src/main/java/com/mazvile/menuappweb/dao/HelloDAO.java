package com.mazvile.menuappweb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class HelloDAO {

    @Autowired
    private DataSource dataSource;

    public String getName() {
        String name = "";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT firstName FROM UserLogin");
            result.next();
            name = result.getString("firstName");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
