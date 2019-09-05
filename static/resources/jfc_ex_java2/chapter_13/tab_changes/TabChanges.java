//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
 
public class TabChanges extends JPanel
implements ChangeListener
{
    JTabbedPane pane;
        
    public TabChanges()
    {
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
        
        pane.addChangeListener(this);
        add(pane,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public void stateChanged(ChangeEvent e)
    {   
        int curSelIndex = pane.getSelectedIndex();
        String curPaneTitle = pane.getTitleAt(curSelIndex);
        
        System.out.println("Selected: "+curPaneTitle);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Tab Changes Example");
        TabChanges panel = new TabChanges();
        
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
