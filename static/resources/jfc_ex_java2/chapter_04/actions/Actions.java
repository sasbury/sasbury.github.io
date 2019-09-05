//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Actions extends JPanel 
implements ActionListener
{
    public Actions()
    {
        JTextField field;
        JButton button;
        JRadioButton radioButton;
        ButtonGroup grp;
        
        setBackground(Color.lightGray);
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        field = new JTextField(10);
        field.addActionListener(this);
        field.setActionCommand("Text Field Activated");
        add(field);
        
        button = new JButton("Button");
        button.addActionListener(this);
        button.setActionCommand("Button Activated");
        add(button);
        
        grp = new ButtonGroup();
        
        radioButton =  new JRadioButton("One");
        radioButton.addActionListener(this);
        radioButton.setActionCommand("One Activated");
        grp.add(radioButton);
        add(radioButton);
        
        radioButton =  new JRadioButton("Two");
        radioButton.addActionListener(this);
        radioButton.setActionCommand("Two Activated");
        grp.add(radioButton);
        add(radioButton);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String cmd;
        Object source;
        
        source = e.getSource();
        cmd = e.getActionCommand();
        
        System.out.println("Action: "+cmd+"\n\tperformed by: "+source);
        System.out.println();
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Actions");
        Actions panel = new Actions();
        
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
