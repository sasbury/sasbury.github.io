//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Areas extends JPanel
{
    public Areas()
    {
        JTextArea area;
        JPanel tmpPanel;
        Font font;
        
        /*
         * Use a grid layout, and turn on buffering to reduce flicker.
         */
        setLayout(new GridLayout(4,1,10,10));
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        /*
         * Create a area with 10 rows and columns.
         * Set the font to 16pt. plain serif.
         */
        area = new JTextArea(10,10);
        font = new Font("Serif",Font.PLAIN,16);
        area.setFont(font);
        
        add(area);
        
        
        /*
         * Create a area with the initial string "hello".
         * Set the font to 18pt. Bold sanserif.
         * Make the area non-editable.
         */
        area = new JTextArea("Hello");
        area.setEditable(false);
        font = new Font("Sanserif",Font.BOLD,18);
        area.setFont(font);
        
        add(new JScrollPane(area));
        
        /*
         * Create a area with the initial string "World", and 10 rows,15 columns.
         * only allow vertical scrollbars
         * Set the font to 14pt. Italic sanserif.
         */
        area = new JTextArea("World",10,15);
        font = new Font("Sanserif",Font.ITALIC,14);
        area.setFont(font);
        
        add(new JScrollPane(area));
        
        /*
         * Create a default area.
         * Set the font to 20pt. plain serif.
         */
        area = new JTextArea();
        font = new Font("Serif",Font.PLAIN,20);
        area.setFont(font);
        
        add(new JScrollPane(area));
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 500);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Areas");
        Areas panel = new Areas();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

