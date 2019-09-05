//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Clocks extends JPanel 
{   
    public Clocks()
    {
       DigitalClock clock = new DigitalClock();
       
       setBackground(Color.lightGray);
       setLayout(new FlowLayout(FlowLayout.CENTER));
       
       clock.setFont(new Font("Serif",Font.BOLD,24));
       clock.setShowSeconds(true);
       
       add(clock);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 70);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Clocks");
        Clocks panel = new Clocks();
        
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
