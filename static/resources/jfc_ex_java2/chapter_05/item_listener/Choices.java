//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Choices extends JPanel 
implements ItemListener
{
    JComboBox myChoice;
    JTextField field;
    
    public Choices()
    {
        setBackground(Color.lightGray);
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        field = new JTextField(10);
        add(field);
        
        myChoice = new JComboBox();
        myChoice.addItem("Primary");
        myChoice.addItem("Secondary");
        myChoice.addItem("Tertiary");
        myChoice.addItemListener(this);
        add(myChoice);
    }
    
    public void itemStateChanged(ItemEvent evt)
    {
        Object value;
            
        if(evt.getSource() == myChoice)
        {
            value = myChoice.getSelectedItem();
            field.setText(value.toString());
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Choices");
        Choices panel = new Choices();
        
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
