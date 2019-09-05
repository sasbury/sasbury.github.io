//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class SimpleComboExample extends JPanel
{
    public SimpleComboExample() 
    {
        JComboBox comboBox;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());

        comboBox = new JComboBox();
        comboBox.setFont(f);
        
        comboBox.addItem("Gold");
        comboBox.addItem("Silver");
        comboBox.addItem("Bronze");

        add(comboBox,"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("SimpleComboBox Example");
        SimpleComboExample panel = new SimpleComboExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

