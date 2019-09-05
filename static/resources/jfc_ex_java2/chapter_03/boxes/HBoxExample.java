//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HBoxExample extends JPanel
{
    public HBoxExample()
    {
        Box hBox;
        JButton button1,button2;
        JLabel label;
        Component glue,strut;
        
        hBox = Box.createHorizontalBox();
        
        setLayout(new BorderLayout());
        add(hBox,"Center");
        
        button1 = new JButton("Hello");
        button2 = new JButton("World");
        
        label = new JLabel("Swing");
        
        glue = Box.createHorizontalGlue();
        
        hBox.add(glue);
        
        hBox.add(button1);
        
        strut = Box.createHorizontalStrut(10);
        
        hBox.add(strut);
        
        hBox.add(label);
        
        strut = Box.createHorizontalStrut(10);
        
        hBox.add(strut);
        
        hBox.add(button2);
        
        glue = Box.createHorizontalGlue();
        
        hBox.add(glue);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 70);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Horiz. Box Example");
        HBoxExample panel = new HBoxExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

