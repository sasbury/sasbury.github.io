//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RadioExample extends JPanel
{
    public RadioExample()
    {
        ImageIcon buttonImage=new ImageIcon("images/ex.gif");
        ImageIcon selImage=new ImageIcon("images/check.gif");
        AbstractButton button;
        ButtonGroup group;
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        setLayout(new GridLayout(2,2,5,5));
        
        group = new ButtonGroup();
        
        button = new JRadioButton("Save");
        group.add(button);
        add(button);
        
        button = new JRadioButton("Delete");
        group.add(button);
        add(button);
        
        group = new ButtonGroup();
        
        button = new JRadioButton(buttonImage);
        button.setSelectedIcon(selImage);
        group.add(button);
        add(button);
        
        button = new JRadioButton(buttonImage);
        button.setSelectedIcon(selImage);
        group.add(button);
        add(button);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(250, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("RadioButton Example");
        RadioExample panel = new RadioExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

