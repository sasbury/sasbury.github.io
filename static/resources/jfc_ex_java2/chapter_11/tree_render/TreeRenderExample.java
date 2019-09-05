//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TreeRenderExample extends JPanel
{
    public TreeRenderExample() 
    {
        JTree tree;
        Font f;
        DefaultMutableTreeNode rootNode;
        DefaultMutableTreeNode parentNode;
        DefaultMutableTreeNode node;
        TreeIconRenderer treeR;
        TreeData data;
        ImageIcon icon;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());
        
        rootNode = new DefaultMutableTreeNode("Categories");
        
        icon = new ImageIcon("images/ex.gif");
        data = new TreeData("Ex",icon);
        node = new DefaultMutableTreeNode(data,false);
        rootNode.add(node);
        
        icon = new ImageIcon("images/check.gif");
        data = new TreeData("Check",icon);
        node = new DefaultMutableTreeNode(data,false);
        rootNode.add(node);
        
        icon = new ImageIcon("images/shapes.gif");
        data = new TreeData("Shapes",icon);
        node = new DefaultMutableTreeNode(data,false);
        rootNode.add(node);

        tree = new JTree(rootNode);
        tree.setFont(f);
        
        treeR = new TreeIconRenderer();
        tree.setCellRenderer(treeR);
        
        tree.setRowHeight(0);//let the renderer handle it
        
        add(new JScrollPane(tree),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(200, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Tree Renderer Example");
        TreeRenderExample panel = new TreeRenderExample();
        
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

