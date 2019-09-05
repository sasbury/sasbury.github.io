//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Menus extends JPanel
implements ActionListener, MenuListener
{
    JTextField field;
        
    public Menus(JFrame frm)
    {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Levels");
        JMenuItem tmp;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        menu.addMenuListener(this);
        
        tmp = new JMenuItem("Primary");
        tmp.addActionListener(this);
        tmp.setActionCommand("Primary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Secondary");
        tmp.addActionListener(this);
        tmp.setActionCommand("Secondary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Tertiary");
        tmp.addActionListener(this);
        tmp.setActionCommand("Tertiary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Quit");
        tmp.addActionListener(this);
        tmp.setActionCommand("Quit");
        menu.add(tmp);
        
        bar.add(menu);
        
        frm.setJMenuBar(bar);
        
        field = new JTextField(10);
        field.addActionListener(this);
        field.setActionCommand("Text Field Activated");
        add(field,"South");
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String cmd;
        cmd = e.getActionCommand();
        
        field.setText("Action: "+cmd);
        
        if(cmd.equals("Quit"))
        {
            System.exit(0);
        }
    }
    
    public void menuSelected(MenuEvent e)
    {
        field.setText("Menu selected");
    }

    public void menuDeselected(MenuEvent e)
    {
        field.setText("Menu deselected");
    }

    public void menuCanceled(MenuEvent e)
    {
        field.setText("Menu cancelled");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Menus");
        Menus panel = new Menus(frame);
        
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
