package Supermarket.server;

import Supermarket.data.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class ServerApp {
    public static Connection connection;
    public static void main(String[] args) {
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db?useUnicode=true&serverTimezone=UTC","root", "");
         ServerSocket serverSocket=new ServerSocket(2025);
         System.out.println("Waiting for client...");
         while(true){
             Socket socket=serverSocket.accept();
             System.out.println("Client connected");
             ServerThread serverThread=new ServerThread(socket);
             serverThread.start();
         }
     }
     catch(Exception e){
         e.printStackTrace();
     }
    }
    public static void addUser(User user){
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO users(id,login,password,fullname,role)"+
                    " VALUES(NULL,?,?,?,?)");
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullname());
            statement.setInt(4,user.getRole());
            statement.executeUpdate();//insert update delete
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static User getUser(String login,String password){
        User user=null;
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM users WHERE login=? AND password=?");
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String login1 = resultSet.getString("login");
                String password1 = resultSet.getString("password");
                String fullname = resultSet.getString("fullname");
                int role = resultSet.getInt("role");
                user = new User(id, login1, password1, fullname, role);
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static void addDrink(Drink drink){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    " INSERT INTO drinks(id,name,price,available,liters,kind_of_drink)"+
                    " VALUES(NULL,?,?,?,?,?)");
            statement.setString(1,drink.getName());
            statement.setInt(2,drink.getPrice());
            statement.setInt(3,drink.getAvailable());
            statement.setDouble(4,drink.getLiters());
            statement.setString(5,drink.getKind_of_drink());
            statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Drink> getAllDrinks(){
        ArrayList<Drink> drinks=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT * FROM drinks");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String name=resultSet.getString("name");
                int price=resultSet.getInt("price");
                int available=resultSet.getInt("available");
                double liters=resultSet.getDouble("liters");
                String kind_of_drink=resultSet.getString("kind_of_drink");
                int sold=resultSet.getInt("sold");
                drinks.add(new Drink(id,name,price,available,sold,liters,kind_of_drink));
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return drinks;
    }
    public static void editDrink(Drink drink,Long id){
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    " UPDATE drinks SET name=?,price=?,available=?,liters=?,kind_of_drink=? WHERE id=?");
            statement.setString(1,drink.getName());
            statement.setInt(2,drink.getPrice());
            statement.setInt(3,drink.getAvailable());
            statement.setDouble(4,drink.getLiters());
            statement.setString(5,drink.getKind_of_drink());
            statement.setLong(6,drink.getId());
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteDrink(Drink drink){
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "DELETE FROM drinks WHERE id=?");
            statement.setLong(1,drink.getId());
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void addDairy(DairyProduct dairy){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    " INSERT INTO dairies(id,name,price,available,fat_content)"+
                    " VALUES(NULL,?,?,?,?)");
            statement.setString(1,dairy.getName());
            statement.setInt(2,dairy.getPrice());
            statement.setInt(3,dairy.getAvailable());
            statement.setDouble(4,dairy.getFat_content());
            statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<DairyProduct> getAllDairies(){
        ArrayList<DairyProduct> dairies=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT * FROM dairies");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String name=resultSet.getString("name");
                int price=resultSet.getInt("price");
                int available=resultSet.getInt("available");
                double fat_content=resultSet.getDouble("fat_content");
                int sold=resultSet.getInt("sold");
                dairies.add(new DairyProduct(id,name,price,available,sold,fat_content));
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dairies;
    }
    public static void editDairy(DairyProduct dairy,Long id){
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    " UPDATE dairies SET name=?,price=?,available=?,fat_content=? WHERE id=?");
            statement.setString(1,dairy.getName());
            statement.setInt(2,dairy.getPrice());
            statement.setInt(3,dairy.getAvailable());
            statement.setDouble(4,dairy.getFat_content());
            statement.setLong(5,id);
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteDairy(DairyProduct dairy){
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "DELETE FROM dairies WHERE id=?");
            statement.setLong(1,dairy.getId());
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void addUserBuy(User_buy user_buy){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    " INSERT INTO users_buy(id,user_id,product_name,count,totalSum)"+
                    " VALUES(NULL,?,?,?,?)");
            statement.setLong(1,user_buy.getUser_id());
            statement.setString(2,user_buy.getProduct_name());
            statement.setInt(3,user_buy.getCount());
            statement.setInt(4,user_buy.getTotalSum());
            statement.executeUpdate();
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Drink getDrink(Long id){
        Drink drink=null;
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM drinks WHERE id=?");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()) {
                Long id1=resultSet.getLong("id");
                String name=resultSet.getString("name");
                int price=resultSet.getInt("price");
                int available=resultSet.getInt("available");
                double liters=resultSet.getDouble("liters");
                String kind_of_drink=resultSet.getString("kind_of_drink");
                drink=new Drink(id1,name,price,available,liters,kind_of_drink);
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return drink;
    }
    public static void updateDrinkBuy(Long id,int count,int sold){
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    " UPDATE drinks SET available=?,sold=? WHERE id=?");
            statement.setInt(1,count);
            statement.setInt(2,sold);
            statement.setLong(3,id);
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static DairyProduct getDairyP(Long id){
        DairyProduct dairy=null;
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM dairies WHERE id=?");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()) {
                Long id1=resultSet.getLong("id");
                String name=resultSet.getString("name");
                int price=resultSet.getInt("price");
                int available=resultSet.getInt("available");
                double fat_content=resultSet.getDouble("fat_content");
                dairy=new DairyProduct(id1,name,price,available,fat_content);
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dairy;
    }
    public static void updateDairyPBuy(Long id,int count,int sold){
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    " UPDATE dairies SET available=?,sold=? WHERE id=?");
            statement.setInt(1,count);
            statement.setInt(2,sold);
            statement.setLong(3,id);
            statement.executeUpdate();
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<User_buy> getAllProducts(Long user_id){
        ArrayList<User_buy> users_buy=new ArrayList<>();
        try{
            PreparedStatement statement=connection.prepareStatement("" +
                    "SELECT * FROM users_buy WHERE user_id=?");
            statement.setLong(1,user_id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                Long user_id1=resultSet.getLong("user_id");
                String name=resultSet.getString("product_name");
                int count=resultSet.getInt("count");
                int totalSum=resultSet.getInt("totalSum");
                users_buy.add(new User_buy(id,user_id1,name,count,totalSum));
            }
            statement.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return users_buy;
    }
}
