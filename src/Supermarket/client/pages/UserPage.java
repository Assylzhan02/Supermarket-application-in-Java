package Supermarket.client.pages;

import Supermarket.client.applications.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends AppPanel {
    private ClientFrame parent;
    private AppButton BuyDrink,BuyDairyP,HistoryButton,LogoutButton;
    private AppLabel label;
    public UserPage(ClientFrame parent){
        this.parent=parent;
        label=new AppLabel("");
        label.setBounds(220,185,200,40);
        add(label);

        BuyDrink=new AppButton("Buy drink");
        BuyDrink.setBounds(220,250,180,40);
        add(BuyDrink);
        BuyDrink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.userPage.setVisible(false);
                parent.buyDrinkPage.setVisible(true);
            }
        });

        BuyDairyP=new AppButton("Buy dairy products");
        BuyDairyP.setBounds(220,320,180,40);
        add(BuyDairyP);
        BuyDairyP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.userPage.setVisible(false);
                parent.buyDairyPPage.setVisible(true);
            }
        });

        HistoryButton=new AppButton("History");
        HistoryButton.setBounds(220,390,180,40);
        add(HistoryButton);
        HistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.userPage.setVisible(false);
                parent.historyPage.setVisible(true);
            }
        });

        LogoutButton=new AppButton("Logout");
        LogoutButton.setBounds(220,460,180,40);
        add(LogoutButton);
        LogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClientApp.currentUser=null;
                parent.userPage.setVisible(false);
                parent.loginPage.setVisible(true);
            }
        });
    }
    public void updateUser(){ label.setText("Welcome "+ClientApp.currentUser.getFullname());}
}
