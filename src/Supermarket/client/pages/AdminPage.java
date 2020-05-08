package Supermarket.client.pages;

import Supermarket.client.applications.*;
import java.awt.event.*;

public class AdminPage extends AppPanel {
    private ClientFrame parent;
    private AppLabel label;
    private AppButton DrinkButton,DairyPButton,IncomeButton,LogoutButton;
    public AdminPage(ClientFrame parent){
        this.parent=parent;

        label=new AppLabel("Welcome Administrator!");
        label.setBounds(200,185,200,40);
        add(label);

        DrinkButton=new AppButton("Drinks");
        DrinkButton.setLocation(220,250);
        add(DrinkButton);
        DrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.adminPage.setVisible(false);
                parent.drinksPage.setVisible(true);
            }
        });

        DairyPButton=new AppButton("Dairy products");
        DairyPButton.setLocation(220,320);
        add(DairyPButton);
        DairyPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.adminPage.setVisible(false);
                parent.dairyPPage.setVisible(true);
            }
        });

        IncomeButton=new AppButton("Income");
        IncomeButton.setLocation(220,390);
        add(IncomeButton);
        IncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.adminPage.setVisible(false);
                parent.incomePage.setVisible(true);
            }
        });

        LogoutButton=new AppButton("Logout");
        LogoutButton.setLocation(220,460);
        add(LogoutButton);
        LogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClientApp.currentUser=null;
                parent.adminPage.setVisible(false);
                parent.loginPage.setVisible(true);
            }
        });
    }
}
