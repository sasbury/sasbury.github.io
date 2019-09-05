//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class PlainExample extends JPanel
{
    public PlainExample(String fileName)
    {
        JEditorPane area;
        URL url;
        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        area = new JEditorPane();
        
        area.setContentType("text/plain");

        try
        {
            url = new URL("file",null,fileName);
            area.setPage(url);
        }
        catch(Exception exp)
        {
            area.setText("Error loading page.\n" + exp);
        }
        
        add(new JScrollPane(area),"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Plain Text Example");
        PlainExample panel;
        
        if(s.length > 0) panel = new PlainExample(s[0]);
        else panel = new PlainExample("Constitution.txt");
        
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

