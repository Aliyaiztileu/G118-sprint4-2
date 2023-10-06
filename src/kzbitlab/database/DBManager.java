package kzbitlab.database;

import kzbitlab.model.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private  static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/G118?currentSchema=sprint_task_2"
                    "postgres"
                    "postgres"
            );
        }catch (ClassNotFoundException | SQLException e){
            throw  new RuntimeException();
        }
    }

    public static List<Item> getItems () throws SQLException {
        List<Item> items = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * from sprint_task_2.items"
        );
    }

}
