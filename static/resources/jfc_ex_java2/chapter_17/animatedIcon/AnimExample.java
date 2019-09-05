//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class AnimExample extends JPanel
{
    JLabel label;
    
    public AnimExample()
    {
        ImageIcon icon;
        
        setBackground(Color.white);
        
        icon = new ImageIcon("images/plane.gif");
        
        label = new JLabel(icon,JLabel.CENTER);
        icon.setImageObserver(label);
        
        add(label,"Center");
    }
   
    public Dimension getPreferredSize()
    {
        return label.getPreferredSize();
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("AnimatedIcon");
        AnimExample panel = new AnimExample();
        
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

