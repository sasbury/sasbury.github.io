//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class IOptionExample extends JPanel
{
    public IOptionExample()
    {
        JTextField field;
        JLabel label;
        JPanel tmpPanel;
        Font font;
        
        /*
         * Use a grid layout, and turn on buffering to reduce flicker.
         */
        setLayout(new GridLayout(4,1,10,10));
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        label = new JLabel("Name:");
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        label = new JLabel("Address:");
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        label = new JLabel("State:");
        field = new JTextField(4);
        tmpPanel.add(label);
        tmpPanel.add(field);
        
        label = new JLabel("Zip:");
        field = new JTextField(12);
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
        
        label = new JLabel("Phone:");
        field = new JTextField(25);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        tmpPanel.add(label);
        tmpPanel.add(field);
        add(tmpPanel);
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(380, 160);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Info Internal Frame");
        IOptionExample panel = new IOptionExample();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    
        JOptionPane.showInternalMessageDialog(panel
                                     ,"Something Important Has Happened."
                                     ,"Important"
                                     ,JOptionPane.INFORMATION_MESSAGE);
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

