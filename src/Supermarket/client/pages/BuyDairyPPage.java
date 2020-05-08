package Supermarket.client.pages;

import Supermarket.client.applications.*;
import Supermarket.data.DairyProduct;
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

public class BuyDairyPPage extends AppPanel {
    private ClientFrame parent;
    private AppButton BuyButton,BackButton,RefreshButton;
    private AppLabel idLabel,nameLabel,priceLabel,availableLabel,fatContentLabel;
    private AppField idField,nameField,priceField,availableField;
    private String[] fat_content={"choose","0.5","1.5","2.5","3.2","5.0","9.0","42.0"};
    private Object[] columns={"ID","Name","Price","Available","Fat content"};
    private JComboBox jcomboFats;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private ArrayList<DairyProduct> dairies;

    public BuyDairyPPage(ClientFrame parent){
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
                jcomboFats.setSelectedItem(model.getValueAt(i,4).toString());
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
        updateDairies();

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

        fatContentLabel=new AppLabel("Fat content(%):");
        fatContentLabel.setLocation(20,530);
        add(fatContentLabel);
        jcomboFats=new JComboBox(fat_content);
        jcomboFats.setBounds(160,530,200,35);
        jcomboFats.setBackground(Color.WHITE);
        jcomboFats.setForeground(Color.BLACK);
        jcomboFats.setFont(new Font("Consolas",1,16));
        jcomboFats.setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));
        add(jcomboFats);


        BuyButton=new AppButton("Buy");
        BuyButton.setLocation(410,370);
        add(BuyButton);
        BuyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Long id=Long.valueOf(idField.getText());
                String name=nameField.getText();
                int price=Integer.parseInt(priceField.getText());
                int available=Integer.parseInt(availableField.getText());
                double fat_content=Double.parseDouble(jcomboFats.getSelectedItem().toString());
                if(name.isEmpty()||price==0||available==0||fat_content==0){
                    JOptionPane.showMessageDialog(parent,"Please,fill all fields!!!");
                }
                else{
                    User_buy user_buy=new User_buy(null,ClientApp.currentUser.getId(),name,available,price*available);
                    parent.clientSocket.buyDairyP(user_buy);
                    DairyProduct dairy=new DairyProduct(id,null,0,available,0);
                    parent.clientSocket.editDairyPBuy(dairy);
                    nameField.setText("");
                    priceField.setText("");
                    availableField.setText("");
                    jcomboFats.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(parent,"Dairy product was buyed succesfully!!!");
                    clearDairies();
                    updateDairies();
                }
            }
        });

        RefreshButton=new AppButton("Refresh");
        RefreshButton.setLocation(410,440);
        add(RefreshButton);
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearDairies();
                updateDairies();
            }
        });

        BackButton=new AppButton("Back");
        BackButton.setLocation(410,510);
        add(BackButton);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.buyDairyPPage.setVisible(false);
                parent.userPage.setVisible(true);
            }
        });

    }
    public void updateDairies(){
        dairies=parent.clientSocket.getAllDairies();
        Object[] rows=new Object[5];
        for(DairyProduct d:dairies){
            rows[0]=d.getId();
            rows[1]=d.getName();
            rows[2]=d.getPrice();
            rows[3]=d.getAvailable();
            rows[4]=d.getFat_content();
            model.addRow(rows);
        }
    }

    public void clearDairies(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
            dm.removeRow(0);
    }
}
