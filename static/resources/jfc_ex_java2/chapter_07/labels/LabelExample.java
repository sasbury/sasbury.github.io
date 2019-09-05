//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class LabelExample extends JPanel
{

    public LabelExample()
    {
        ImageIcon img;
        JLabel label;
        Font font,bold;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        bold = new Font("SanSerif",Font.BOLD,18);
        setFont(font);
        setDoubleBuffered(true);
        
        setLayout(new GridLayout(3,2,5,5));
        
        label = new JLabel("Label 1");
        label.setFont(font);
        label.setToolTipText("Just Text");
        label.setHorizontalAlignment(label.CENTER);
        label.setVerticalTextPosition(label.BOTTOM);
        label.setHorizontalTextPosition(label.CENTER);
        add(label);
 
        img = new ImageIcon("images/shapes.gif");
        
        label = new JLabel();
        label.setFont(font);
        label.setIcon(img);
        label.setToolTipText("Just Image");
        label.setHorizontalAlignment(label.CENTER);
        
        add(label);
 
        img = new ImageIcon("images/check.gif");
        
        label = new JLabel("Label3");
        label.setFont(font);
        label.setIcon(img);
        label.setToolTipText("Text and Image, bold font. Text at bottom left");
        label.setVerticalTextPosition(label.BOTTOM);
        label.setHorizontalAlignment(label.CENTER);
        label.setHorizontalTextPosition(label.LEFT);
        label.setFont(bold);
        add(label);
 

        img = new ImageIcon("images/ex.gif");

        label = new JLabel("Label4");
        label.setFont(font);
        label.setIcon(img);
        label.setToolTipText("Text and Image. Text at top-right");
        label.setVerticalTextPosition(label.TOP);
        label.setHorizontalAlignment(label.CENTER);
        label.setHorizontalTextPosition(label.RIGHT);
        add(label);
 
        img = new ImageIcon("images/shapes.gif");

        label = new JLabel("Label 5");
        label.setFont(font);
        label.setIcon(img);
        label.setToolTipText("Text and Image, blue foreground");
        label.setVerticalTextPosition(label.BOTTOM);
        label.setHorizontalAlignment(label.CENTER);
        label.setHorizontalTextPosition(label.CENTER);
        label.setForeground(Color.blue);
        add(label);
 
        label = new JLabel("Label 6");
        label.setFont(font);
        label.setToolTipText("Text, Green and Bold");
        label.setVerticalTextPosition(label.BOTTOM);
        label.setHorizontalAlignment(label.CENTER);
        label.setHorizontalTextPosition(label.CENTER);
        label.setFont(bold);
        label.setForeground(Color.green);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Label Example");
        LabelExample panel = new LabelExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}


