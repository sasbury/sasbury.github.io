//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class DoubleClickExample extends JPanel
{
    public DoubleClickExample() 
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
        
        list.setListData(data);
        list.setSelectedIndex(1);
        
        list.addMouseListener(new DoubleClicker(list));
        
        add(new JScrollPane(list),"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(100, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Double Click Example");
        DoubleClickExample panel = new DoubleClickExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

class DoubleClicker extends MouseAdapter
{
    protected JList list;
    
    public DoubleClicker(JList l)
    {
        list = l;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() == 2)
        {
            ListModel model;
            int index = list.locationToIndex(e.getPoint());
            Object item;
            
            model = list.getModel();
            item = model.getElementAt(index);

            list.ensureIndexIsVisible(index);
            
            System.out.println("Double clicked on " + item);
        }
    }
}

