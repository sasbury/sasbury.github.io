//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TreeRootExample extends JPanel
{
    public TreeRootExample() 
    {
        JTree tree1,tree2;
        DefaultMutableTreeNode rootNode;
        DefaultMutableTreeNode parentNode;
        DefaultMutableTreeNode node;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new GridLayout(1,2,10,10));
        
        rootNode = new DefaultMutableTreeNode("Categories");
        
        parentNode = new DefaultMutableTreeNode("Metals");
        
        node = new DefaultMutableTreeNode("Gold",false);
        parentNode.add(node);
        
        node = new DefaultMutableTreeNode("Silver",false);
        parentNode.add(node);
        
        node = new DefaultMutableTreeNode("Bronze",false);
        parentNode.add(node);

        node = new DefaultMutableTreeNode("Copper",false);
        parentNode.add(node);

        node = new DefaultMutableTreeNode("Iron",false);
        parentNode.add(node);

        node = new DefaultMutableTreeNode("Platinum",false);
        parentNode.add(node);

        node = new DefaultMutableTreeNode("Titanium",false);
        parentNode.add(node);

        
        rootNode.add(parentNode);
        
        parentNode = new DefaultMutableTreeNode("Companies");
        
        node = new DefaultMutableTreeNode("Paradigm Research",false);
        parentNode.add(node);
        
        node = new DefaultMutableTreeNode("JavaSoft",false);
        parentNode.add(node);
        
        node = new DefaultMutableTreeNode("Wiley Press",false);
        parentNode.add(node);

        node = new DefaultMutableTreeNode("Your Name Here",false);
        parentNode.add(node);
        
        
        rootNode.add(parentNode);

        tree1 = new JTree(rootNode);
        //**Commented out for Java 2 version**
        //tree1.setFont(f);
        
        tree1.setRootVisible(true);
        tree1.setShowsRootHandles(true);
        
        tree2 = new JTree();
        tree2.setModel(tree1.getModel());
        //**Commented out for Java 2 version**
        //tree2.setFont(f);
        
        tree2.setRootVisible(false);
        tree2.setShowsRootHandles(false);
        
        add(new JScrollPane(tree1));
        add(new JScrollPane(tree2));
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Tree Root Example");
        TreeRootExample panel = new TreeRootExample();
        
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

