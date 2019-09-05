//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class ButtonTextPos extends JPanel
{
    public ButtonTextPos()
    {
        JButton button;
        ImageIcon icon;
        JPanel p  = new JPanel();
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        icon = new ImageIcon("images/shapes.gif");
        
        setLayout(new GridLayout(3,3,5,5));
        
        button = new JButton("Top Left",icon);
        button.setVerticalTextPosition(AbstractButton.TOP);
        button.setHorizontalTextPosition(AbstractButton.LEFT);
        add(button);
        
        button = new JButton("Top Center",icon);
        button.setVerticalTextPosition(AbstractButton.TOP);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        add(button);
        
        button = new JButton("Top Right",icon);
        button.setVerticalTextPosition(AbstractButton.TOP);
        button.setHorizontalTextPosition(AbstractButton.RIGHT);
        add(button);
        
        button = new JButton("Center Left",icon);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEFT);
        add(button);
        
        button = new JButton("Center Center",icon);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        add(button);
        
        button = new JButton("Center Right",icon);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.RIGHT);
        add(button);
        
        button = new JButton("Bottom Left",icon);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.LEFT);
        add(button);
        
        button = new JButton("Bottom Center",icon);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        add(button);
        
        button = new JButton("Bottom Right",icon);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.RIGHT);
        add(button);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Button Text Pos Example");
        ButtonTextPos panel = new ButtonTextPos();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

