//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class FileList extends JPanel
{
    public FileList(String startPath) 
    {
        JList list;
        FileSystemListModel model;
        File root;
        
        setLayout(new BorderLayout());
        
        root = new File(startPath);
        model = new FileSystemListModel(root);
        
        list = new JList();
        list.setModel(model);
        
        add(new JScrollPane(list),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("File List Example");
        FileList panel;
        
        if(s.length > 0)
            panel = new FileList(s[0]);
        else
            panel = new FileList("/");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
        frame.addWindowListener(new WindowCloser());
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

