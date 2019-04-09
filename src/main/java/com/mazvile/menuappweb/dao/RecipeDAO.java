package com.mazvile.menuappweb.dao;

import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
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
public class RecipeDAO {

    @Autowired
    private DataSource dataSource;

    public int addRecipe(Recipe recipe) {
        if (recipe.getId() == null) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("INSERT INTO Recipe" +
                        "(Name, Description, Type) VALUES (\"" + recipe.getName() +
                        "\", \"" + recipe.getDescription() + "\", \"" + recipe.getDishType() + "\")");
                ResultSet resultSet = statement.executeQuery("SELECT MAX(Id) FROM Recipe");
                resultSet.next();
                int recipeId = resultSet.getInt("MAX(Id)");
                for (Product product : recipe.getProducts()) {
                    result += statement.executeUpdate("INSERT INTO Product_List" +
                            "(Recipe_Id, Product_Id, Quantity) VALUES (" + recipeId +
                            ", " + product.getId() + ", " + product.getQuantity().getValue() + ")");
                }
                statement.close();
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int removeRecipe(Recipe recipe) {
        if (recipe.getId() != null) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("DELETE FROM Product_List WHERE Recipe_Id = " + recipe.getId());
                result += statement.executeUpdate("DELETE FROM Recipe WHERE Id = " + recipe.getId());
                statement.close();
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int updateRecipe(Recipe recipe) {
        if (recipe.getId() != null) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("DELETE FROM Product_List WHERE Recipe_Id = " + recipe.getId());
                result += statement.executeUpdate("UPDATE Recipe " +
                        "SET Name = \"" + recipe.getName() + "\", Description = \"" + recipe.getDescription()
                        + "\", Type = \"" + recipe.getDishType() + "\" WHERE Id = " + recipe.getId());
                for (Product product : recipe.getProducts()) {
                    result += statement.executeUpdate("INSERT INTO Product_List" +
                            "(Recipe_Id, Product_Id, Quantity) VALUES (" + recipe.getId() +
                            ", " + product.getId() + ", " + product.getQuantity().getValue() + ")");
                }
                statement.close();
                connection.close();
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>();
    }
}
