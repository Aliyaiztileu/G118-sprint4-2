package kzbitlab.database;

import kzbitlab.model.Item;
import kzbitlab.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static Connection connection;

//    static {
//        List<Item> items = new ArrayList<>();
//        items.add(new Item(1L, "Iphone 15", "Test description", 512.3));
//        items.add(new Item(2L, "Iphone 14", "Test description2", 512.3));
//        items.add(new Item(3L, "Iphone 16", "Test description3", 512.3));
//    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/G118?currentSchema=sprintfortwo",
                    "postgres",
                    "postgres"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }
    }

    public static List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from sprintfortwo.items"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setPrice(resultSet.getDouble("price"));
                items.add(item);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static User getUserByEmail(String  email){
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * from sprintfortwo.users where email = ?"
            ); statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


}
