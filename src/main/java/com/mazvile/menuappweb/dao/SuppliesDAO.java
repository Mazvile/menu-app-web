package com.mazvile.menuappweb.dao;

import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuppliesDAO {

    @Autowired
    private DataSource dataSource;

    public int addProduct(Product product) {
        int result = 0;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM Supplies WHERE Product_Id =" + product.getId());
            resultSet.next();
            int count = resultSet.getInt("COUNT(*)");
            if (count == 0) {
                result = statement.executeUpdate("INSERT INTO Supplies" +
                        "(Product_Id, Quantity) VALUES (\"" + product.getId() +
                        "\", \"" + product.getQuantity().getValue() + "\")");
                statement.close();
                connection.close();
                return result;
            }
            resultSet = statement.executeQuery("SELECT Quantity FROM Supplies WHERE Product_Id = "
                    + "\"" + product.getId() + "\"");
            resultSet.next();
            int existingQuantity = resultSet.getInt("Quantity");
            int updatedQuantity = existingQuantity + product.getQuantity().getValue();
            result = statement.executeUpdate("UPDATE Supplies SET Quantity =" + updatedQuantity + " WHERE Product_Id ="
                    + "\"" + product.getId() + "\"");
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int removeProduct(Product product) {
        if (product.getId() != null) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT Quantity FROM Supplies WHERE Product_Id = " + product.getId());
                Integer productQuantity = resultSet.getInt("Quantity");
                int result = statement.executeUpdate("UPDATE Supplies SET Quantity = " +
                        (productQuantity - product.getQuantity().getValue()) +
                        " WHERE Product_Id=" + product.getId());
                statement.close();
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<Product> getAllSupplies() {
        List<Product> supplies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Supplies.Product_Id, Product.Name, Product.Units, Supplies.Quantity " +
                    "FROM Supplies JOIN Product ON Supplies.Product_Id = Product.Id");
            while (resultSet.next()) {
                Integer suppliesId = resultSet.getInt("Product_Id");
                String suppliesName = resultSet.getString("Name");
                String suppliesUnits = resultSet.getString("Units");
                Units units = Units.valueOf(suppliesUnits);
                Integer suppliesValue = resultSet.getInt("Quantity");
                supplies.add(Product.ProductBuilder.aProduct()
                        .withId(suppliesId)
                        .withName(suppliesName)
                        .withUnits(units)
                        .withValue(suppliesValue)
                        .build());
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplies;
    }
}
