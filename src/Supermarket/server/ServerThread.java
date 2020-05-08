package Supermarket.server;

import Supermarket.data.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try{
            ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
            while(true){
                Packet packet=(Packet)input.readObject();
                if(packet.getCode().equals("ADD_USER")){
                    User user=(User)packet.getData();
                    ServerApp.addUser(user);
                }
                else if(packet.getCode().equals("AUTH")){
                    User user=(User)packet.getData();
                    User result=ServerApp.getUser(user.getLogin(),user.getPassword());
                    Packet packet1=new Packet("AUTH",result);
                    output.writeObject(packet1);
                }
                else if(packet.getCode().equals("ADD_DRINK")){
                    Drink drink=(Drink)packet.getData();
                    ServerApp.addDrink(drink);
                }
                else if(packet.getCode().equals("LIST_DRINKS")){
                    ArrayList<Drink> drinks=ServerApp.getAllDrinks();
                    Packet packet1=new Packet("LIST_DRINKS",drinks);
                    output.writeObject(packet1);
                }
                else if(packet.getCode().equals("EDIT_DRINK")){
                    Drink drink=(Drink)packet.getData();
                    ServerApp.editDrink(drink,drink.getId());
                }
                else if(packet.getCode().equals("DELETE_DRINK")){
                    Drink drink =(Drink)packet.getData();
                    ServerApp.deleteDrink(drink);
                }
                else if(packet.getCode().equals("ADD_DAIRY")){
                    DairyProduct dairy=(DairyProduct)packet.getData();
                    ServerApp.addDairy(dairy);
                }
                else if(packet.getCode().equals("LIST_DAIRIES")){
                    ArrayList<DairyProduct> dairies=ServerApp.getAllDairies();
                    Packet packet1=new Packet("LIST_DAIRIES",dairies);
                    output.writeObject(packet1);
                }
                else if(packet.getCode().equals("EDIT_DAIRY")){
                    DairyProduct dairy=(DairyProduct)packet.getData();
                    ServerApp.editDairy(dairy,dairy.getId());
                }
                else if(packet.getCode().equals("DELETE_DAIRY")){
                    DairyProduct dairy=(DairyProduct)packet.getData();
                    ServerApp.deleteDairy(dairy);
                }
                else if(packet.getCode().equals("BUY_DRINK")){
                    User_buy user_buy=(User_buy)packet.getData();
                    ServerApp.addUserBuy(user_buy);
                }
                else if(packet.getCode().equals("EDIT_DRINK_BUY")){
                    Drink drink=(Drink)packet.getData();
                    Drink fromDB=ServerApp.getDrink(drink.getId());
                    int new_count=fromDB.getAvailable()-drink.getAvailable();
                    ServerApp.updateDrinkBuy(drink.getId(),new_count,drink.getAvailable());
                }
                else if(packet.getCode().equals("BUY_DAIRY_P")){
                    User_buy user_buy=(User_buy)packet.getData();
                    ServerApp.addUserBuy(user_buy);
                }
                else if(packet.getCode().equals("EDIT_DAIRY_P_BUY")){
                    DairyProduct dairy=(DairyProduct)packet.getData();
                    DairyProduct fromDB=ServerApp.getDairyP(dairy.getId());
                    int new_count=fromDB.getAvailable()-dairy.getAvailable();
                    ServerApp.updateDairyPBuy(dairy.getId(),new_count,dairy.getAvailable());
                }
                else if(packet.getCode().equals("LIST_PRODUCTS")){
                    User_buy user_buy1=(User_buy)packet.getData();
                    ArrayList<User_buy>users_buy1=ServerApp.getAllProducts(user_buy1.getUser_id());
                    Packet packet1=new Packet("LIST_PRODUCTS",users_buy1);
                    output.writeObject(packet1);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
