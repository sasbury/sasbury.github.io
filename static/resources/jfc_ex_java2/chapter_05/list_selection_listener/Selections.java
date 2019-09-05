//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Selections extends JPanel 
implements ListSelectionListener
{
    JTextField indField;
    JTextField selIndField;
    JTextField actField;
    JList list;
        
    public Selections()
    {
        String items[] = {"one","two","three","four"
                            ,"five","six","seven"};
        JPanel footer,tmp;
        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        list = new JList(items);
        list.addListSelectionListener(this);
        add(new JScrollPane(list),"Center");
        
        footer = new JPanel();
        footer.setLayout(new GridLayout(3,1,5,5));
        
        tmp = new JPanel();
        tmp.add(new JLabel("Evt index:"));
        indField = new JTextField(20);
        tmp.add(indField);
        
        footer.add(tmp);
        
        tmp = new JPanel();
        tmp.add(new JLabel("Min/max index:"));
        selIndField = new JTextField(20);
        tmp.add(selIndField);
        
        footer.add(tmp);
        
        tmp = new JPanel();
        tmp.add(new JLabel("Sel values:"));
        actField = new JTextField(20);
        tmp.add(actField);
        
        footer.add(tmp);
        
        add(footer,"South");
    }
    
    public void valueChanged(ListSelectionEvent e)
    {
        int first,last;
        int i;
        String newVal = "";
        ListModel listData = list.getModel();
        Object selValues[];
        
        /*
         * Display the information from the event.
         */
        first = e.getFirstIndex();
        last = e.getLastIndex();
        
        for(i=first;(i>=0)&&(i<=last);i++)
        {
            if(i!=first) newVal+=" ";
            newVal += listData.getElementAt(i);
        }

        indField.setText(newVal);
        
        /*
         * Display the every thing in sel indexes
         * first and last will be -1 
         * if there is no selection.
         */
         newVal = "";
         
        first = list.getMinSelectionIndex();
        last = list.getMaxSelectionIndex();
        
        for(i=first;(i>=0)&&(i<=last);i++)
        {
            if(i!=first) newVal+=" ";
            newVal += listData.getElementAt(i);
        }

        selIndField.setText(newVal);
    
        //Display the actual selected values
        
        selValues = list.getSelectedValues();
        
        if(selValues != null) last = selValues.length;
        else last = 0;
        
        newVal = "";
        
        for(i=0;i<last;i++)
        {
            if(i!=0) newVal+=" ";
            newVal += selValues[i].toString();
        }
        
        actField.setText(newVal);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(350, 350);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Selections");
        Selections panel = new Selections();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
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
