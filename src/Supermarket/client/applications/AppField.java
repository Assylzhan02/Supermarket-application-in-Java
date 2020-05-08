package Supermarket.client.applications;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class AppField extends JTextField {
    public AppField(){
        setSize(200,35);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Consolas",1,16));
        setBorder(new EtchedBorder(Color.BLACK,Color.BLACK));
    }
}
