//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class HTMLExample extends JPanel
implements HyperlinkListener
{
    JEditorPane area;
    
    public HTMLExample(String urlText)
    {
        URL url;
        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        area = new JEditorPane();
        
        add(new JScrollPane(area),"Center");
        
        area.addHyperlinkListener(this);
        
        try
        {
            if(urlText != null)
            {
                url = new URL(urlText);
                area.setPage(url);
            }
        }
        catch(Exception exp)
        {
            area.setText("Error loading page.\n" + exp);
        }
    }
    
    public void hyperlinkUpdate(HyperlinkEvent evt)
    {
        try
        {
            area.setPage(evt.getURL());
        }
        catch(Exception exp)
        {
            area.setText("Error loading page.\n" + exp);
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("HTML Text Example");
        HTMLExample panel;
        
        if(s.length > 0) panel = new HTMLExample(s[0]);
        else panel = new HTMLExample(null);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

