//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class Simple_Applet extends JApplet
{
    public void init()
    {
        JButton button = new JButton("Hello");
        
        setBackground(Color.lightGray);
        getContentPane().add(button,"Center");
        
    }
}

