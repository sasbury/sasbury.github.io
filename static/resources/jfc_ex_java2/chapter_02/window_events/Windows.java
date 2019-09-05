//Copyright 1998 John Wiley and Sons, Inc.
 
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Windows extends Object
 implements WindowListener
{
    public void windowOpened(WindowEvent e)
    {
        System.out.println("Window opened.");
    }
    
    public void windowClosing(WindowEvent e)
    {
        Window win = e.getWindow();
        
        System.out.println("Window closing.");
        
        win.setVisible(false);
        win.dispose();
    }
    
    public void windowClosed(WindowEvent e)
    {
        System.out.println("Window closed.");
        System.exit(0);
    }
    
    public void windowIconified(WindowEvent e)
    {
        System.out.println("Window iconified.");
    }
    
    public void windowDeiconified(WindowEvent e)
    {
        System.out.println("Window deiconified.");
    }
    
    public void windowActivated(WindowEvent e)
    {
        System.out.println("Window activated.");
    }
    
    public void windowDeactivated(WindowEvent e)
    {
        System.out.println("Window deactivated.");
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Windows");
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new Windows());
        
        frame.getContentPane().add(new Label("Hello World"),"Center");
        
        frame.pack();
        frame.setVisible(true);
    }
}
