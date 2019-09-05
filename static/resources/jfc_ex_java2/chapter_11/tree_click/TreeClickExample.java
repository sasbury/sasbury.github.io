//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TreeClickExample extends JPanel
{
    public TreeClickExample() 
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
        
        tree.addMouseListener(new DoubleClicker());
        
        add(new JScrollPane(tree),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(200, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Tree Clicks Example");
        TreeClickExample panel = new TreeClickExample();
        
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

class DoubleClicker extends MouseAdapter
{
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() == 2)
        {
            JTree tree;
            Object item;
            TreePath path;
            
            tree = (JTree) e.getSource();
            path = tree.getPathForLocation(e.getX(), e.getY());
            item = path.getLastPathComponent();
            
            System.out.println("Double clicked on " + item);
            
            tree.scrollPathToVisible(path);
        }
    }
}

