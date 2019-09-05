//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ButtonLabels extends JPanel
{
    public ButtonLabels()
    {
        JButton button;
        JPanel p  = new JPanel();
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        setLayout(new GridLayout(3,3,5,5));
        
        button = new JButton("Top Left");
        button.setVerticalAlignment(AbstractButton.TOP);
        button.setHorizontalAlignment(AbstractButton.LEFT);
        add(button);
        
        button = new JButton("Top Center");
        button.setVerticalAlignment(AbstractButton.TOP);
        button.setHorizontalAlignment(AbstractButton.CENTER);
        add(button);
        
        button = new JButton("Top Right");
        button.setVerticalAlignment(AbstractButton.TOP);
        button.setHorizontalAlignment(AbstractButton.RIGHT);
        add(button);
        
        button = new JButton("Center Left");
        button.setVerticalAlignment(AbstractButton.CENTER);
        button.setHorizontalAlignment(AbstractButton.LEFT);
        add(button);
        
        button = new JButton("Center Center");
        button.setVerticalAlignment(AbstractButton.CENTER);
        button.setHorizontalAlignment(AbstractButton.CENTER);
        add(button);
        
        button = new JButton("Center Right");
        button.setVerticalAlignment(AbstractButton.CENTER);
        button.setHorizontalAlignment(AbstractButton.RIGHT);
        add(button);
        
        button = new JButton("Bottom Left");
        button.setVerticalAlignment(AbstractButton.BOTTOM);
        button.setHorizontalAlignment(AbstractButton.LEFT);
        add(button);
        
        button = new JButton("Bottom Center");
        button.setVerticalAlignment(AbstractButton.BOTTOM);
        button.setHorizontalAlignment(AbstractButton.CENTER);
        add(button);
        
        button = new JButton("Bottom Right");
        button.setVerticalAlignment(AbstractButton.BOTTOM);
        button.setHorizontalAlignment(AbstractButton.RIGHT);
        add(button);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Button Label Example");
        ButtonLabels panel = new ButtonLabels();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

