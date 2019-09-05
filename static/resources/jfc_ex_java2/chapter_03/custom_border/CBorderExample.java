//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class CBorderExample extends JPanel
{
    public CBorderExample()
    {
        Border border;
        JLabel label;
        
        setLayout(new GridLayout(1,2,10,10));
        
        border = new TriBorder(5,10,5,10);
        label = new JLabel("(5,10,5,10) TriBorder",JLabel.CENTER);
        label.setBorder(border);
        add(label);
        
        border = new TriBorder(10,10,10,10);
        label = new JLabel("(10,10,10,10) TriBorder",JLabel.CENTER);
        label.setBorder(border);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(350, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Custom Border Example");
        CBorderExample panel = new CBorderExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

