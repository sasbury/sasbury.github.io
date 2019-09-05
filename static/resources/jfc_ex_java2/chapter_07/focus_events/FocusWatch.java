//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class FocusWatch extends JPanel 
implements FocusListener
{
    public FocusWatch()
    {
        JTextField field;
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        field = new JTextField("one",5);
        field.addFocusListener(this);
        add(field);
        
        field = new JTextField("two",5);
        field.addFocusListener(this);
        add(field);
        
        field = new JTextField("three",5);
        field.addFocusListener(this);
        add(field);
        
        field = new JTextField("four",5);
        field.addFocusListener(this);
        add(field);
    }
    
    public void focusGained(FocusEvent e)
    {
        JTextField field = (JTextField) e.getSource();
        String text = field.getText();
        System.out.println("Focus gained by: "+text);
    }
    
    public void focusLost(FocusEvent e)
    {
        JTextField field = (JTextField) e.getSource();
        String text = field.getText();
        System.out.println("Focus lost by: "+text);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(350, 50);
    }

    public static void main(String s[])
    {
        JFrame frame = new JFrame("FocusWatch");
        FocusWatch panel = new FocusWatch();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

class WindowCloser extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}
