//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class TextTabs extends JPanel
{
    public TextTabs()
    {
        JTabbedPane pane;
        JPanel panel;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        pane = new JTabbedPane();
        
        panel = new JPanel();
        panel.add(new Label("First"));
        pane.addTab("First",panel);
        
        panel = new JPanel();
        panel.add(new Label("Second"));
        pane.addTab("Second",panel);
        
        panel = new JPanel();
        panel.add(new Label("Third"));
        pane.addTab("Third",panel);
        
        pane.setSelectedIndex(0);
        add(pane,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Text Tabs Example");
        TextTabs panel = new TextTabs();
        
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
