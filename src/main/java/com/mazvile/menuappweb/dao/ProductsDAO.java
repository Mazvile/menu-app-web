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
public class ProductsDAO {

    @Autowired
    private DataSource dataSource;

    public int insertProduct(Product product) {
        if (product.getId() == null) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("INSERT INTO Product" +
                        "(Name, Units) VALUES (\"" + product.getName() +
                        "\", \"" + product.getQuantity().getUnit() + "\")");
                statement.close();
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int deleteProduct(Product product) {
        if (product.getId() != null) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("DELETE FROM Product" +
                        "WHERE Id=" + product.getId());
                statement.close();
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
            while (resultSet.next()) {
                Integer productId = resultSet.getInt("Id");
                String productName = resultSet.getString("Name");
                String productUnits = resultSet.getString("Units");
                Units units = Units.valueOf(productUnits);
                products.add(new Product(productId, productName, units));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
