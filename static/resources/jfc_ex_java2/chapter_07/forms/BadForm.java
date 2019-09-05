//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class BadForm extends JPanel
{
    public BadForm()
    {
        JTextField field;
        JLabel label;
        JPanel tmpPanel;
        ImageIcon icon;
        Font font;
        
        /*
         * Use a grid layout, and turn on buffering to reduce flicker.
         */
        setLayout(new GridLayout(5,1,10,10));
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        label = new JLabel("Name:");
        label.setForeground(Color.red);
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        icon = new ImageIcon("images/mail.gif");
        label = new JLabel(icon);
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        label = new JLabel("State:");
        add(label);
        
        field = new JTextField(4);
        tmpPanel.add(field);
        
        label = new JLabel("Zip:");
        field = new JTextField(12);
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        icon = new ImageIcon("images/phone.gif");
        label = new JLabel(icon);
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(380, 180);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("BadForm");
        BadForm panel = new BadForm();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

