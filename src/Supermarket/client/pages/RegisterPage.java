package Supermarket.client.pages;

import Supermarket.client.applications.*;
import Supermarket.data.*;
import javax.swing.*;
import java.awt.event.*;

public class RegisterPage extends AppPanel {
    private ClientFrame parent;
    private AppLabel LoginLabel,PasswordLabel,FIOLabel;
    private AppField LoginField,PasswordField,FIOField;
    private AppButton AddButton,BackButton;

    public RegisterPage(ClientFrame parent){
        this.parent=parent;
        LoginLabel=new AppLabel("Login:");
        LoginLabel.setLocation(130,200);
        add(LoginLabel);
        LoginField=new AppField();
        LoginField.setLocation(210,200);
        add(LoginField);

        PasswordLabel=new AppLabel("Password:");
        PasswordLabel.setLocation(130,260);
        add(PasswordLabel);
        PasswordField=new AppField();
        PasswordField.setLocation(210,260);
        add(PasswordField);

        FIOLabel=new AppLabel("Fullname:");
        FIOLabel.setLocation(130,320);
        add(FIOLabel);
        FIOField=new AppField();
        FIOField.setLocation(210,320);
        add(FIOField);

        AddButton=new AppButton("Sign Up");
        AddButton.setLocation(120,410);
        add(AddButton);
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String login=LoginField.getText();
                String password=PasswordField.getText();
                String FIO=FIOField.getText();
                if(login.isEmpty()||password.isEmpty()||FIO.isEmpty()){
                    JOptionPane.showMessageDialog(parent,"Please, fill all fields!");
                }
                else{
                    User user=new User(null,login,password,FIO,2);
                    parent.clientSocket.addUser(user);
                    LoginField.setText("");
                    PasswordField.setText("");
                    FIOField.setText("");
                    JOptionPane.showMessageDialog(parent,"You have registered succesfully!");
                }
            }
        });

        BackButton=new AppButton("Back");
        BackButton.setLocation(350,410);
        add(BackButton);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.registerPage.setVisible(false);
                parent.mainPage.setVisible(true);
            }
        });


    }

}
