//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class SimpleListExample extends JPanel
{
    public SimpleListExample() 
    {
        JList list;
        Vector data;
        Font f;
        
        f = new Font("SanSerif",Font.PLAIN,24);
        setFont(f);
        setLayout(new BorderLayout());

        list = new JList();
        list.setFont(f);
        
        data = new Vector();
        data.addElement("Gold");
        data.addElement("Silver");
        data.addElement("Bronze");
        data.addElement("Copper");
        data.addElement("Iron");
        data.addElement("Platinum");
        data.addElement("Titanium");
        
        list.setListData(data);
        
        add(new JScrollPane(list),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(100, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Simple List Example");
        SimpleListExample panel = new SimpleListExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

