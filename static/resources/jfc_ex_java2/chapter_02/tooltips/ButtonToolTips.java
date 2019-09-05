//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ButtonToolTips
{
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Button Tooltip Example");
        JButton b = new JButton("Hello");
        
        b.setToolTipText("World");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        
        frame.getContentPane().add(b,"Center");
        
        frame.pack();
        frame.setVisible(true);
    }
}

