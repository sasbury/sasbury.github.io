//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class HSplit extends JPanel
{
    public HSplit()
    {
        JSplitPane pane;
        JPanel left,right;
        JLabel label;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        left = new JPanel();
        left.setOpaque(true);
        left.setBackground(Color.red);
        left.setLayout(new BorderLayout());
        label = new JLabel("Left",JLabel.CENTER);
        label.setForeground(Color.white);
        left.add(label,"Center");
        
        right = new JPanel();
        right.setOpaque(true);
        right.setBackground(Color.blue);
        right.setLayout(new BorderLayout());
        label = new JLabel("Right",JLabel.CENTER);
        label.setForeground(Color.white);
        right.add(label,"Center");
        
        
        pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT
                            ,left,right);
        
        pane.setContinuousLayout(true);
        add(pane,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Horizontal Split Example");
        HSplit panel = new HSplit();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.show();
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
