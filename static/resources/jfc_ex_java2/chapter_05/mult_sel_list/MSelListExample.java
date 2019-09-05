//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class MSelListExample extends JPanel
{
    public MSelListExample() 
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
        data.addElement("Nickel");
        data.addElement("Iron");
        
        list.setListData(data);
        list.setSelectedIndex(1);
        
        int mode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        list.setSelectionMode(mode);

        add(new JScrollPane(list),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(120, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("List Selection Example");
        MSelListExample panel = new MSelListExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

