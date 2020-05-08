package Supermarket.client.pages;

import Supermarket.client.applications.*;
import Supermarket.data.Drink;
import Supermarket.data.User_buy;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BuyDrinkPage extends AppPanel {
    private ClientFrame parent;
    private AppButton BuyButton,BackButton,RefreshButton;
    private AppLabel idLabel,nameLabel,priceLabel,availableLabel,litersLabel,kind_of_drinkLabel;
    private AppField idField,nameField,priceField,availableField;
    private String[] kinds={"choose","still water","sparkling water","soda","juice"};
    private String[] liters={"choose","0.5","1.0","1.5","2.0"};
    private Object[] columns={"ID","Name","Price","Available","Liters","Kind of drink"};
    private JComboBox jcomboKinds,jcomboLiters;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private ArrayList<Drink> drinks;

    public BuyDrinkPage(ClientFrame parent){
        this.parent=parent;
        table=new JTable();
        model=new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Consolas",1,16));
        table.setRowHeight(30);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int i=table.getSelectedRow();
                idField.setText(model.getValueAt(i,0).toString());
                nameField.setText(model.getValueAt(i,1).toString());
                priceField.setText(model.getValueAt(i,2).toString());
                availableField.setText("");
                jcomboLiters.setSelectedItem(model.getValueAt(i,4).toString());
                jcomboKinds.setSelectedItem(model.getValueAt(i,5).toString());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        pane=new JScrollPane(table);
        pane.setBounds(0,0,600,350);
        add(pane);
        updateDrinks();

        idLabel=new AppLabel("ID:");
        idLabel.setLocation(20,370);
        add(idLabel);
        idField=new AppField();
        idField.setLocation(160,370);
        add(idField);

        nameLabel=new AppLabel("Name:");
        nameLabel.setLocation(20,410);
        add(nameLabel);
        nameField=new AppField();
        nameField.setLocation(160,410);
        add(nameField);

        priceLabel=new AppLabel("Price:");
        priceLabel.setLocation(20,450);
        add(priceLabel);
        priceField=new AppField();
        priceField.setLocation(160,450);
        add(priceField);

        availableLabel=new AppLabel("Available:");
        availableLabel.setLocation(20,490);
        add(availableLabel);
        availableField=new AppField();
        availableField.setLocation(160,490);
        add(availableField);

        litersLabel=new AppLabel("Liters:");
        litersLabel.setLocation(20,530);
        add(litersLabel);
        jcomboLiters=new JComboBox(liters);
        jcomboLiters.setBounds(160,530,200,35);
        jcomboLiters.setBackground(Color.WHITE);
        jcomboLiters.setForeground(Color.BLACK);
        jcomboLiters.setFont(new Font("Consolas",1,16));
        jcomboLiters.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));
        add(jcomboLiters);

        kind_of_drinkLabel=new AppLabel("Kind of drink:");
        kind_of_drinkLabel.setLocation(20,570);
        add(kind_of_drinkLabel);
        jcomboKinds=new JComboBox(kinds);
        jcomboKinds.setBounds(160,570,200,35);
        jcomboKinds.setBackground(Color.WHITE);
        jcomboKinds.setForeground(Color.BLACK);
        jcomboKinds.setFont(new Font("Consolas",1,16));
        jcomboKinds.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));
        add(jcomboKinds);

        BuyButton=new AppButton("Buy");
        BuyButton.setLocation(410,400);
        add(BuyButton);
        BuyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Long id=Long.valueOf(idField.getText());
                String name=nameField.getText();
                int price=Integer.parseInt(priceField.getText());
                int available=Integer.parseInt(availableField.getText());
                double liters=Double.parseDouble(jcomboLiters.getSelectedItem().toString());
                String kind_of_drink=jcomboKinds.getSelectedItem().toString();
                if(id==0||name.isEmpty()||price==0||available==0||liters==0||kind_of_drink.isEmpty()){
                    JOptionPane.showMessageDialog(parent,"Please,fill all fields!!!");
                }
                else{
                    User_buy user_buy=new User_buy(null,ClientApp.currentUser.getId(),name,available,price*available);
                    parent.clientSocket.buyDrink(user_buy);
                    Drink drink=new Drink(id,null,0,available,0,null);
                    parent.clientSocket.editDrinkBuy(drink);
                    nameField.setText("");
                    priceField.setText("");
                    availableField.setText("");
                    jcomboLiters.setSelectedIndex(0);
                    jcomboKinds.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(parent,"Drink was buyed succesfully!!!");
                    clearDrinks();
                    updateDrinks();
                }
            }
        });

        RefreshButton=new AppButton("Refresh");
        RefreshButton.setLocation(410,470);
        add(RefreshButton);
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearDrinks();
                updateDrinks();
            }
        });

        BackButton=new AppButton("Back");
        BackButton.setLocation(410,540);
        add(BackButton);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.buyDrinkPage.setVisible(false);
                parent.userPage.setVisible(true);
            }
        });

    }
    public void updateDrinks(){
        drinks=parent.clientSocket.getAllDrinks();
        Object[] rows=new Object[6];
        for(Drink d:drinks){
            rows[0]=d.getId();
            rows[1]=d.getName();
            rows[2]=d.getPrice();
            rows[3]=d.getAvailable();
            rows[4]=d.getLiters();
            rows[5]=d.getKind_of_drink();
            model.addRow(rows);
        }
    }

    public void clearDrinks(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
            dm.removeRow(0);
    }
}
