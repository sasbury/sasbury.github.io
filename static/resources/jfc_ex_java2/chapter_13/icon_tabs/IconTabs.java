//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class IconTabs extends JPanel
{
    public IconTabs()
    {
        JTabbedPane pane;
        JPanel panel;
        ImageIcon icon;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        pane = new JTabbedPane();
        
        icon = new ImageIcon("images/shapes.gif");
        panel = new JPanel();
        panel.add(new Label("First"));
        pane.addTab(null,icon,panel);
        
        icon = new ImageIcon("images/check.gif");
        panel = new JPanel();
        panel.add(new Label("Second"));
        pane.addTab(null,icon,panel);
        
        icon = new ImageIcon("images/ex.gif");
        panel = new JPanel();
        panel.add(new Label("Third"));
        pane.addTab(null,icon,panel);
        
        pane.setSelectedIndex(0);
        add(pane,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Icon Tabs Example");
        IconTabs panel = new IconTabs();
        
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
