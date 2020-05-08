package Supermarket.client.applications;

import javax.swing.*;
import java.awt.*;

public class AppLabel extends JLabel {
    public AppLabel(String text){
        setText(text);
        setSize(160,40);
        setForeground(Color.BLACK);
        setFont(new Font("Consolas",1,16));
    }

}
