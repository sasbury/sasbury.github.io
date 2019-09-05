//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class CustomEditorExample extends JPanel
{
    public CustomEditorExample() 
    {
        JTree tree;
        Font f;
        DefaultMutableTreeNode rootNode;
        DefaultMutableTreeNode parentNode;
        DefaultMutableTreeNode node;
        TreeCellRenderer treeR;
        TreeCellEditor treeE;
        ImageIcon icon;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());
        
        rootNode = new DefaultMutableTreeNode("Colors");
        
        node = new DefaultMutableTreeNode("Red",false);
        rootNode.add(node);
        
        node = new DefaultMutableTreeNode("Green",false);
        rootNode.add(node);
        
        node = new DefaultMutableTreeNode("Blue",false);
        rootNode.add(node);

        tree = new JTree(rootNode);
        tree.setFont(f);
        
        treeR = new SimpleTreeRenderer();
        tree.setCellRenderer(treeR);
        
        treeE = new TreeFieldEditor();
        tree.setCellEditor(treeE);
        tree.setEditable(true);
        tree.setInvokesStopCellEditing(true);
        
        tree.setRowHeight(0);//let the renderer handle it
        
        add(new JScrollPane(tree),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(200, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Custom Editor Example");
        CustomEditorExample panel = new CustomEditorExample();
        
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
