//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class ListExample extends JPanel
{
    public ListExample() 
    {
        JList list;
        Font f;
        Icon icon;
        Vector data;
        
        f = new Font("Serif",Font.PLAIN,18);
        setFont(f);
        setLayout(new BorderLayout());

        list = new JList();
        list.setFont(f);
        
        list.setCellRenderer(new ListItemRenderer());
        
        data = new Vector();
        
        icon = new ImageIcon("images/check.gif");
        data.addElement(new ListItem("Check",icon));
        
        icon = new ImageIcon("images/ex.gif");
        data.addElement(new ListItem("Ex",icon));
        
        icon = new ImageIcon("images/shapes.gif");
        data.addElement(new ListItem("Shapes",icon));

        list.setListData(data);
        list.setSelectedIndex(1);
        
        add(new JScrollPane(list),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(140, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("List Example");
        ListExample panel = new ListExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}


