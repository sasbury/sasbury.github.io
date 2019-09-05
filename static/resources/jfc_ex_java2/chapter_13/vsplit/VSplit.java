//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class VSplit extends JPanel
{
    public VSplit()
    {
        JSplitPane pane;
        JPanel top,bottom;
        JLabel label;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        top = new JPanel();
        top.setOpaque(true);
        top.setBackground(Color.red);
        top.setLayout(new BorderLayout());
        label = new JLabel("Top",JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);
        top.add(label,"Center");
        
        bottom = new JPanel();
        bottom.setOpaque(true);
        bottom.setBackground(Color.blue);
        bottom.setLayout(new BorderLayout());
        label = new JLabel("Bottom",JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);
        bottom.add(label,"Center");
        
        
        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT
                            ,top,bottom);
        
        pane.setContinuousLayout(false);
        add(pane,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Vertical Split Example");
        VSplit panel = new VSplit();
        
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
