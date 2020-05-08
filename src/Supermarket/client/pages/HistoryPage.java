package Supermarket.client.pages;

import Supermarket.client.applications.*;
import Supermarket.data.DairyProduct;
import Supermarket.data.Drink;
import Supermarket.data.User_buy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HistoryPage extends AppPanel {
    private ClientFrame parent;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private AppButton BackButton, RefreshButton;
    private Object[] columns={"#","Product", "Count", "Price"};
    private ArrayList<User_buy> users_buy;
    private User_buy user_buy;

    public HistoryPage(ClientFrame parent) {
        this.parent = parent;
        this.parent=parent;
        table=new JTable();
        model=new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Consolas",1,16));
        table.setRowHeight(30);
        pane=new JScrollPane(table);
        pane.setBounds(0,0,600,350);
        add(pane);
        if(ClientApp.currentUser!=null){
            updateProduct();
        }

        RefreshButton=new AppButton("Refresh");
        RefreshButton.setLocation(200, 450);
        add(RefreshButton);
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearProduct();
                updateProduct();
            }
        });

        BackButton=new AppButton("Back");
        BackButton.setLocation(200, 500);
        add(BackButton);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.historyPage.setVisible(false);
                parent.userPage.setVisible(true);
            }
        });
    }
    private void updateProduct(){
        user_buy=new User_buy(null, ClientApp.currentUser.getId(), null, 0, 0);
        users_buy=parent.clientSocket.getAllGoods(user_buy);
        Object[] row=new Object[4];
        for(User_buy u:users_buy){
            row[0]=u.getId();
            row[1]=u.getProduct_name();
            row[2]=u.getCount();
            row[3]=u.getTotalSum();
            model.addRow(row);
        }
    }
    private void clearProduct(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
}
