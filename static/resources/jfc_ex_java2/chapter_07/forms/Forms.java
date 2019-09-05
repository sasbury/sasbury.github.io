//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Forms extends JPanel
{
    public Forms()
    {
        JTextField field;
        JLabel label;
        JPanel tmpPanel;
        Font font;
        
        /*
         * Use a grid layout, and turn on buffering to reduce flicker.
         */
        setLayout(new GridLayout(4,1,10,10));
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        label = new JLabel("Name:");
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        label = new JLabel("Address:");
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        label = new JLabel("State:");
        field = new JTextField(4);
        tmpPanel.add(label);
        tmpPanel.add(field);
        
        label = new JLabel("Zip:");
        field = new JTextField(12);
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        label = new JLabel("Phone:");
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(380, 160);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Forms");
        Forms panel = new Forms();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

