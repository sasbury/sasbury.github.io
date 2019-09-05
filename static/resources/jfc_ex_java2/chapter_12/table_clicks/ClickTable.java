//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ClickTable extends JPanel
{
    public ClickTable(String startPath) 
    {
        JTable table;
        FileSystemTableModel model;
        File root;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());
        
        root = new File(startPath);
        model = new FileSystemTableModel(root);
        
        table = new JTable();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        
        table.addMouseListener(new TripleClicker());
        
        add(new JScrollPane(table)
            ,"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Click Table Example");
        ClickTable panel;
        
        if(s.length > 0)
            panel = new ClickTable(s[0]);
        else
            panel = new ClickTable("/");
        
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

