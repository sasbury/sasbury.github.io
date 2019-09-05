//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class LabelAlign extends JPanel
{
    public LabelAlign()
    {
        JLabel label;
        JPanel p  = new JPanel();
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        setLayout(new GridLayout(3,3,5,5));
        
        label = new JLabel("Top Left");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);
        add(label);
        
        label = new JLabel("Top Center");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
        
        label = new JLabel("Top Right");
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.RIGHT);
        add(label);
        
        label = new JLabel("Center Left");
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.LEFT);
        add(label);
        
        label = new JLabel("Center Center");
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
        
        label = new JLabel("Center Right");
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.RIGHT);
        add(label);
        
        label = new JLabel("Bottom Left");
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.LEFT);
        add(label);
        
        label = new JLabel("Bottom Center");
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);
        
        label = new JLabel("Bottom Right");
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.RIGHT);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Align Label Example");
        LabelAlign panel = new LabelAlign();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

