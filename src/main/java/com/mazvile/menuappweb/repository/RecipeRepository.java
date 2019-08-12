package com.mazvile.menuappweb.repository;

import com.mazvile.menuappweb.model.*;
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
public class RecipeRepository {

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
        List<Recipe> recipes = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet recipeSet = statement.executeQuery("SELECT * FROM Recipe");
            while (recipeSet.next()) {
                Integer recipeId = recipeSet.getInt("Id");
                String recipeName = recipeSet.getString("Name");
                String recipeDescription = recipeSet.getString("Description");
                String type = recipeSet.getString("Type");
                RecipeType recipeType = RecipeType.valueOf(type);
                List<Product> products = new ArrayList<>();
                ResultSet productSet = statement.executeQuery("SELECT * FROM Product_List " +
                        "JOIN Product ON Product.Id = Product_List.Product_Id " +
                        "WHERE Recipe_Id = " + recipeId);
                while (productSet.next()) {
                    Integer productId = productSet.getInt("Product_Id");
                    String productName = productSet.getString("Name");
                    String units = productSet.getString("Units");
                    Units productUnits = Units.valueOf(units);
                    Integer suppliesQuantity = productSet.getInt("Quantity");
                    products.add(Product.ProductBuilder.aProduct()
                            .withId(productId)
                            .withName(productName)
                            .withQuantity(new Quantity(suppliesQuantity, productUnits))
                            .build());
                }
                recipes.add(Recipe.RecipeBuilder.aRecipe()
                        .withId(recipeId)
                        .withName(recipeName)
                        .withDescription(recipeDescription)
                        .withDishType(recipeType)
                        .withProducts(products)
                        .build());
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
