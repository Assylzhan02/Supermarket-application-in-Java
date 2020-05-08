package Supermarket.client.pages;

import Supermarket.client.applications.*;
import Supermarket.data.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginPage extends AppPanel {
    private ClientFrame parent;
    private AppField LoginField,PasswordField;
    private AppLabel LoginLabel,PasswordLabel;
    private AppButton AddButton,BackButton;
    public LoginPage(ClientFrame parent){
        this.parent=parent;
        LoginLabel=new AppLabel("Login:");
        LoginLabel.setLocation(150,230);
        add(LoginLabel);
        LoginField=new AppField();
        LoginField.setLocation(230,230);
        add(LoginField);

        PasswordLabel=new AppLabel("Password:");
        PasswordLabel.setLocation(150,295);
        add(PasswordLabel);
        PasswordField=new AppField();
        PasswordField.setLocation(230,295);
        add(PasswordField);

        AddButton=new AppButton("Sign In");
        AddButton.setLocation(120,390);
        add(AddButton);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String login=LoginField.getText();
                String password=PasswordField.getText();
                User user=new User(null,login,password,null,0);
                User user1=parent.clientSocket.toLogin(user);
                if(user1!=null){
                    ClientApp.currentUser=user1;
                    if(user1.getRole()==1){
                        parent.loginPage.setVisible(false);
                        parent.adminPage.setVisible(true);}
                        else if(user1.getRole()==2){
                            parent.loginPage.setVisible(false);
                            parent.userPage.updateUser();
                            parent.userPage.setVisible(true);
                        }
                }
                else{
                        LoginField.setText("");
                        PasswordField.setText("");
                        JOptionPane.showMessageDialog(parent,"Login or password is wrong");
                    }
            }
        });

        BackButton=new AppButton("Back");
        BackButton.setLocation(350,390);
        add(BackButton);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.mainPage.setVisible(true);
                parent.loginPage.setVisible(false);
            }
        });
    }
}
