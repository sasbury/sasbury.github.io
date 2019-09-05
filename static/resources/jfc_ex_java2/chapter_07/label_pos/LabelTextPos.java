//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class LabelTextPos extends JPanel
{
    public LabelTextPos()
    {
        JLabel label;
        ImageIcon icon;
        JPanel p  = new JPanel();
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        icon = new ImageIcon("images/shapes.gif");
        
        setLayout(new GridLayout(3,3,5,5));
        
        label = new JLabel("Top Left",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.LEFT);
        add(label);
        
        label = new JLabel("Top Center",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        add(label);
        
        label = new JLabel("Top Right",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        add(label);
        
        label = new JLabel("Center Left",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.LEFT);
        add(label);
        
        label = new JLabel("Center Center",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        add(label);
        
        label = new JLabel("Center Right",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        add(label);
        
        label = new JLabel("Bottom Left",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.LEFT);
        add(label);
        
        label = new JLabel("Bottom Center",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        add(label);
        
        label = new JLabel("Bottom Right",icon,JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Label Text Pos Example");
        LabelTextPos panel = new LabelTextPos();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

