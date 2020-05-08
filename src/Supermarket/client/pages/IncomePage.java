package Supermarket.client.pages;

import Supermarket.client.applications.*;
import Supermarket.data.DairyProduct;
import Supermarket.data.Drink;
import Supermarket.data.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IncomePage extends AppPanel {
    public ClientFrame parent;
    private AppButton BackButton, RefreshButton;
    private AppLabel label;
    private Object[] columns={"ID", "Name", "Price", "Count"};
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private ArrayList<Drink> drinks;
    private ArrayList<DairyProduct> dairies;

    public IncomePage(ClientFrame parent) {
        this.parent = parent;
        table=new JTable();
        model=new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Consolas",1,16));
        table.setRowHeight(30);
        pane=new JScrollPane(table);
        pane.setBounds(0,0, 600, 350);
        add(pane);
        updateProduct();

        label=new AppLabel("Total income: "+Integer.toString(getTotalPrice()));
        label.setBounds(400, 450, 300, 30);
        add(label);

        RefreshButton=new AppButton("Refresh");
        RefreshButton.setLocation(200, 500);
        add(RefreshButton);
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Total income: "+Integer.toString(getTotalPrice()));
                clearProduct();
                updateProduct();
            }
        });

        BackButton=new AppButton("Back");
        BackButton.setLocation(200, 550);
        add(BackButton);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.incomePage.setVisible(false);
                parent.adminPage.setVisible(true);
            }
        });

    }
    public void updateProduct(){
        drinks=parent.clientSocket.getAllDrinks();
        dairies=parent.clientSocket.getAllDairies();
        Object[] rowDr=new Object[4];
        Object[] rowDa=new Object[4];
        for(Drink dr:drinks){
            if(dr.getSold()>0){
                rowDr[0]=dr.getId();
                rowDr[1]=dr.getName();
                rowDr[2]=dr.getPrice();
                rowDr[3]=dr.getSold();
                model.addRow(rowDr);
            }
        }
        for (DairyProduct da:dairies) {
            if(da.getSold()>0) {
                rowDa[0] = da.getId();
                rowDa[1] = da.getName();
                rowDa[2] = da.getPrice();
                rowDa[3] = da.getSold();
                model.addRow(rowDa);
            }
        }

    }
    public int getTotalPrice(){
        drinks=parent.clientSocket.getAllDrinks();
        dairies=parent.clientSocket.getAllDairies();

        int total=0;
        for(Drink dr:drinks){
            if(dr.getSold()>0){
                total+=dr.getSold()*dr.getPrice();
            }
        }
        for(DairyProduct da:dairies){
            if(da.getSold()>0){
                total+=da.getSold()*da.getPrice();
            }
        }
        return total;
    }
    public void clearProduct(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
}
