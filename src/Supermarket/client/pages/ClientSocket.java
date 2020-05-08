package Supermarket.client.pages;

import Supermarket.data.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientSocket {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ClientSocket(){
        try{
            socket=new Socket("localhost",2025);
            output=new ObjectOutputStream(socket.getOutputStream());
            input=new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try{
            output.close();
            input.close();
            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addUser(User user){
        Packet packet=new Packet("ADD_USER",user);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public User toLogin(User user){
        User result=null;
        try{
            Packet packet=new Packet("AUTH",user);
            output.writeObject(packet);

            Packet packet1=(Packet)input.readObject();
            result=(User)packet1.getData();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public void addDrink(Drink drink){
        Packet packet=new Packet("ADD_DRINK",drink);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Drink> getAllDrinks(){
        ArrayList<Drink> drinks=new ArrayList<>();
        Packet packet=new Packet("LIST_DRINKS",null);
        try{
            output.writeObject(packet);
            Packet packet1=(Packet)input.readObject();
            drinks=(ArrayList<Drink>)packet1.getData();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return drinks;
    }
    public void editDrink(Drink drink){
        Packet packet=new Packet("EDIT_DRINK",drink);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteDrink(Drink drink){
        Packet packet=new Packet("DELETE_DRINK",drink);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addDairy(DairyProduct dairy){
        Packet packet=new Packet("ADD_DAIRY",dairy);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<DairyProduct> getAllDairies(){
        ArrayList<DairyProduct> dairies=new ArrayList<>();
        Packet packet=new Packet("LIST_DAIRIES",null);
        try{
            output.writeObject(packet);
            Packet packet1=(Packet)input.readObject();
            dairies=(ArrayList<DairyProduct>)packet1.getData();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dairies;
    }
    public void editDairy(DairyProduct dairy){
        Packet packet=new Packet("EDIT_DAIRY",dairy);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteDairy(DairyProduct dairy){
        Packet packet=new Packet("DELETE_DAIRY",dairy);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void buyDrink(User_buy user_buy){
        Packet packet=new Packet("BUY_DRINK",user_buy);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void editDrinkBuy(Drink drink){
        Packet packet=new Packet("EDIT_DRINK_BUY",drink);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void buyDairyP(User_buy user_buy){
        Packet packet=new Packet("BUY_DAIRY_P",user_buy);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void editDairyPBuy(DairyProduct dairyProduct){
        Packet packet=new Packet("EDIT_DAIRY_P_BUY",dairyProduct);
        try{
            output.writeObject(packet);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<User_buy> getAllGoods(User_buy user_buy){
        ArrayList<User_buy> products=new ArrayList<>();
        Packet packet=new Packet("LIST_PRODUCTS",user_buy);
        try{
            output.writeObject(packet);
            Packet packet1=(Packet)input.readObject();
            products=(ArrayList<User_buy>)packet1.getData();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return products;
    }
}
