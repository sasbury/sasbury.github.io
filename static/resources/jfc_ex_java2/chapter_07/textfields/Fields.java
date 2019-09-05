//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Fields extends JPanel
{
    public Fields()
    {
        JTextField field;
        JPanel tmpPanel;
        Font font;
        
        /*
         * Use a grid layout, and turn on buffering to reduce flicker.
         */
        setLayout(new GridLayout(4,1,10,10));
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        /*
         * Create a field with 10 columns.
         * Set the font to 16pt. plain serif.
         */
        field = new JTextField(10);
        font = new Font("Serif",Font.PLAIN,16);
        field.setFont(font);
        
        /* Add the field to a panel first, to prevent
         * unwanted resizing.
         */
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tmpPanel.add(field);
        add(tmpPanel);
        
        
        /*
         * Create a field with the initial string "hello".
         * Set the font to 18pt. Bold sanserif.
         * Make the field non-editable.
         */
        field = new JTextField("Hello");
        field.setEditable(false);
        font = new Font("Sanserif",Font.BOLD,18);
        field.setFont(font);
        
        /* Add the field to a panel first, to prevent
         * unwanted resizing.
         */
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tmpPanel.add(field);
        add(tmpPanel);
        
        /*
         * Create a field with the initial string "World", and 15 columns.
         * Set the font to 14pt. Italic sanserif.
         */
        field = new JTextField("World",15);
        font = new Font("Sanserif",Font.ITALIC,14);
        field.setFont(font);
        
        /* Add the field to a panel first, to prevent
         * unwanted resizing.
         */
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tmpPanel.add(field);
        add(tmpPanel);
        
        /*
         * Create a default field.
         * Set the font to 20pt. plain serif.
         */
        field = new JTextField();
        font = new Font("Serif",Font.PLAIN,20);
        field.setFont(font);
        
        /* Add the field to a panel first, to prevent
         * unwanted resizing.
         */
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tmpPanel.add(field);
        add(tmpPanel);
        
        
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Fields");
        Fields panel = new Fields();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

