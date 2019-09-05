//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class Simple_Window
{
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Simple Example");
        JButton button = new JButton("Hello");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        frame.getContentPane().add(button,"Center");
        
        frame.pack();
        frame.setVisible(true);
    }
}

