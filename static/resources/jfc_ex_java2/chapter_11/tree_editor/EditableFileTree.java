//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class EditableFileTree extends JPanel
{
    public EditableFileTree(String startPath) 
    {
        JTree tree;
        FileSystemTreeModel model;
        File root;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());
        
        root = new File(startPath);
        model = new FileSystemTreeModel(root);
        
        tree = new JTree();
        tree.setModel(model);
        //**Commented out for Java 2 version**
        //tree.setFont(f);
        tree.setEditable(true);
        
        add(new JScrollPane(tree),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(250, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Editable File Tree");
        EditableFileTree panel;
        
        if(s.length > 0)
            panel = new EditableFileTree(s[0]);
        else
            panel = new EditableFileTree("/");
        
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
