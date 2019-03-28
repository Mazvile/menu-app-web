package com.mazvile.menuappweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DBInitializeConfig {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize(){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS UserLogin");
            statement.executeUpdate(
                    "CREATE TABLE Product(" +
                            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "Name VARCHAR(50) NOT NULL," +
                            "Units VARCHAR(10) NOT NULL)"
            );
            statement.executeUpdate(
                    "CREATE TABLE Supplies(" +
                            "Product_Id INTEGER, " +
                            "Quantity INTEGER NOT NULL," +
                            "FOREIGN KEY(Product_Id) REFERENCES Product(Id))"
            );
            statement.executeUpdate(
                    "CREATE TABLE Recipe(" +
                            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "Name VARCHAR(50) NOT NULL," +
                            "Description TEXT," +
                            "Type VARCHAR(11) NOT NULL)"
            );
            statement.executeUpdate(
                    "CREATE TABLE Product_List(" +
                            "Recipe_Id INTEGER, " +
                            "Product_Id INTEGER, " +
                            "Quantity INTEGER NOT NULL," +
                            "FOREIGN KEY(Recipe_Id) REFERENCES Recipe(Id)," +
                            "FOREIGN KEY(Product_Id) REFERENCES Product(Id))"
            );
//            statement.executeUpdate(
//                    "INSERT INTO UserLogin " +
//                            "(userName,password,firstName,lastName,email,mobile) " +
//                            "VALUES " + "('bharat0126','dbase123','Bharat','Verma',"
//                            + " 'bharatverma2488@gmail.com','8861456151')"
//            );
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
