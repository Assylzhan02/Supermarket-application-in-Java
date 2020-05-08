package Supermarket.client.applications;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class AppButton extends JButton {
    public AppButton(String text){
        setText(text);
        setSize(150,40);
        setForeground(Color.BLACK);
        setBackground(Color.MAGENTA);
        setFont(new Font("Consolas",1,16));
        setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));
    }
}
