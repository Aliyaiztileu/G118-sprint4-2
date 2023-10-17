package kzbitlab.database;

import kzbitlab.model.City;
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
                        "SELECT i.id, i.name, i.description, i.price, i.city_id, c.name as city_name, " +
                                "      c.code as city_code " +
                                "from sprintfortwo.items i INNER JOIN sprintfortwo.cities c on i.city_id = c.id\n"
                );
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getLong("id"));
                    item.setName(resultSet.getString("name"));
                    item.setDescription(resultSet.getString("description"));
                    item.setPrice(resultSet.getDouble("price"));
                    City city = new City();
                    city.setId(resultSet.getLong("city_id"));
                    city.setName(resultSet.getString("city_name"));
                    city.setCode(resultSet.getString("city_code"));
                    item.setCity(city);
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
                );
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setEmail(email);
                    user.setPassword(resultSet.getString("password"));
                    user.setFullName(resultSet.getString("full_name"));
                }
                statement.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            return user;
        }

        public static List <City> getCities (){
            List<City> cities = new ArrayList<>();
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM SPRINTFORTWO.CITIES"
                );
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    City city = new City();
                    city.setId(resultSet.getLong("id"));
                    city.setName(resultSet.getString("name"));
                    city.setCode(resultSet.getString("code"));
                    cities.add(city);
                } statement.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            return cities;
        }

        public static void addItem (Item item){
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO SPRINTFORTWO.ITEMS (NAME, DESCRIPTION, PRICE, CITY_ID) "
                                + "VALUES (?, ?, ?, ?)"
                ); statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setDouble(3, item.getPrice());
                statement.setLong(4, item.getCity().getId());
                statement.executeUpdate();
                statement.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        public static City getCityById(Long id){
            City city = null;
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "Select * from sprintfortwo.cities where id=?"
                ); statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    city = new City();
                    city.setId(id);
                    city.setName(resultSet.getString("name"));
                    city.setCode(resultSet.getString("code"));
                    statement.close();
                }
            }catch ( Exception e){
                e.printStackTrace();
            }
            return city;
        }

        public static void deleteItemById (Long id){
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "Delete from Sprintfortwo.items where id = ?"

                ); statement.setLong(1, id);
                statement.executeUpdate();
                statement.close();
            }catch ( Exception e){
                e.printStackTrace();
            }
        }
    public static void editItemById (Long id, String    name, String description, Double price, Long cityId){
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE SPRINTFORTWO.items\n" +
                                "SET NAME = ?, description = ?, price = ?, CITY_ID = ? \n" +
                                "WHERE ID = ?"
                ); statement.setString(1, name );
                statement.setString(2, description);
                statement.setDouble(3, price);
                statement.setLong(4, cityId);
                statement.setLong(5, id);
                statement .executeUpdate();
                statement.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    public static void addUser(User user) {
            try {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO SPRINTFORTWO.USERS(EMAIL, PASSWORD, FULL_NAME) "
                        + "VALUES (?, ?, ?)"
                );
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFullName());
                statement.executeUpdate();
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
