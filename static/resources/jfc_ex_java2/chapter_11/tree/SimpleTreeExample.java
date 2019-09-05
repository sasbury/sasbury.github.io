//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class SimpleTreeExample extends JPanel
{
    public SimpleTreeExample() 
    {
        JTree tree;
        Vector data;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());
        
        data = new Vector();
        data.addElement("Gold");
        data.addElement("Silver");
        data.addElement("Bronze");
        data.addElement("Copper");
        data.addElement("Iron");
        data.addElement("Platinum");
        data.addElement("Titanium");

        tree = new JTree(data);
        //**Commented out for Java 2 version**
        //tree.setFont(f);
        
        add(new JScrollPane(tree),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(250, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Simple Tree Example");
        SimpleTreeExample panel = new SimpleTreeExample();
        
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

