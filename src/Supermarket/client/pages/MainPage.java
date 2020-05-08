package Supermarket.client.pages;

import Supermarket.client.applications.*;
import java.awt.event.*;

public class MainPage extends AppPanel {
    public ClientFrame parent;
    private AppButton LoginButton,RegisterButton,ExitButton;
    public MainPage(ClientFrame parent){
        this.parent=parent;
        LoginButton=new AppButton("Sign in");
        LoginButton.setLocation(220,220);
        add(LoginButton);
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.mainPage.setVisible(false);
                parent.loginPage.setVisible(true);
            }
        });

        RegisterButton=new AppButton("Register");
        RegisterButton.setLocation(220,300);
        add(RegisterButton);
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.mainPage.setVisible(false);
                parent.registerPage.setVisible(true);
            }
        });

        ExitButton=new AppButton("Exit");
        ExitButton.setLocation(220,380);
        add(ExitButton);
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
