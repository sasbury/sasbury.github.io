//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CheckboxExample extends JPanel
{
    public CheckboxExample()
    {
        ImageIcon buttonImage=new ImageIcon("images/no_check.gif");
        ImageIcon selImage=new ImageIcon("images/w_check.gif");
        AbstractButton button;
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        setLayout(new GridLayout(1,2,5,5));
        
        button = new JCheckBox("Save");
        add(button);

        button = new JCheckBox(buttonImage);
        button.setSelectedIcon(selImage);
        add(button);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 70);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Checkbox Example");
        CheckboxExample panel = new CheckboxExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

