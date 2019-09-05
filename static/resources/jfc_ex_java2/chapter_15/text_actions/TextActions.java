//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class TextActions extends JPanel
{
    public TextActions(JFrame frame)
    {
        JTextPane area;
        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        area = new JTextPane();
        
        add(new JScrollPane(area),"Center");
        
        buildMenu(frame,area);
    }
    
    public void buildMenu(JFrame frame,JTextComponent text)
    {
        Action[] actions;
        int i,max;
        JMenuBar bar;
        JMenu menu;
        
        bar = new JMenuBar();
        menu = new JMenu("Actions");
    
        actions = text.getActions();
        
        max = actions.length;
        
        for(i=0;i<max;i++)
        {
            menu.add(actions[i]);
        }
        
        bar.add(menu);
        
        frame.setJMenuBar(bar);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 150);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Text Actions Example");
        TextActions panel = new TextActions(frame);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

