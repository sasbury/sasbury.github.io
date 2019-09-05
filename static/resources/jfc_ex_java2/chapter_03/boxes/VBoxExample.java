//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class VBoxExample extends JPanel
{
    public VBoxExample()
    {
        Box vBox;
        JButton button1,button2;
        JLabel label;
        Component glue,strut;
        
        vBox = Box.createVerticalBox();
        
        setLayout(new BorderLayout());
        add(vBox,"Center");
        
        button1 = new JButton("Hello");
        button2 = new JButton("World");
        
        label = new JLabel("Swing");
        
        glue = Box.createVerticalGlue();
        
        vBox.add(glue);
        
        vBox.add(button1);
        
        strut = Box.createVerticalStrut(10);
        
        vBox.add(strut);
        
        vBox.add(label);
        
        strut = Box.createVerticalStrut(10);
        
        vBox.add(strut);
        
        vBox.add(button2);
        
        glue = Box.createVerticalGlue();
        
        vBox.add(glue);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(60, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Vert. Box Example");
        VBoxExample panel = new VBoxExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

