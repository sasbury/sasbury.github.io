//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TreeExpExample extends JPanel
implements TreeExpansionListener
{
    JTextField selField;
    JTree tree;
    
    public TreeExpExample() 
    {
        DefaultMutableTreeNode rootNode;
        DefaultMutableTreeNode parentNode;
        DefaultMutableTreeNode node;
        TreeSelectionModel selModel;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());
        
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
        
        parentNode = new DefaultMutableTreeNode("Trucks");
        
        node = new DefaultMutableTreeNode("S10",false);
        parentNode.add(node);
        
        node = new DefaultMutableTreeNode("Ranger",false);
        parentNode.add(node);
        
        node = new DefaultMutableTreeNode("Frontier",false);
        parentNode.add(node);
        
        rootNode.add(parentNode);

        tree = new JTree(rootNode);
        //**Commented out for Java 2 version**
        //tree.setFont(f);
        
        tree.addTreeExpansionListener(this);
        
        add(new JScrollPane(tree),"Center");
        
        selField = new JTextField(20);
        add(selField,"South");
    }
    
    public void treeCollapsed(TreeExpansionEvent e)
    {
        TreePath path = e.getPath();
        Object pathObj[] = path.getPath();
        int i,max;
        String formattedPath = "";
        
        max = pathObj.length;
        
        for(i=0;i<max;i++)
        {
            formattedPath+="/";
            formattedPath+=pathObj[i].toString();
        }
        
        selField.setText("Collapsed: "+formattedPath);
    }
    
    public void treeExpanded(TreeExpansionEvent e)
    {
        TreePath path = e.getPath();
        Object pathObj[] = path.getPath();
        int i,max;
        String formattedPath = "";
        
        max = pathObj.length;
        
        for(i=0;i<max;i++)
        {
            formattedPath+="/";
            formattedPath+=pathObj[i].toString();
        }
        
        selField.setText("Expanded: "+formattedPath);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(200, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Tree Expansion Example");
        TreeExpExample panel = new TreeExpExample();
        
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
