//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenusActions extends JFrame
{
    public MenusActions()
    {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Buddies");
        BuddyAction dee,dum;
        JButton button;
        
        setTitle("Menus and Actions");
        getContentPane().setBackground(Color.lightGray);
        getContentPane().setLayout(new FlowLayout());
        
        dee = new BuddyAction("TweedleDee");
        dum = new BuddyAction("TweedleDum");
        
        dee.setBuddy(dum);
        dum.setBuddy(dee);
        
        button = new JButton("TweedleDee");
        button.addActionListener(dee);
        getContentPane().add(button);
        
        button = new JButton("TweedleDum");
        button.addActionListener(dum);
        getContentPane().add(button);
        
        menu.add(dee);
        menu.add(dum);
        
        menu.addSeparator();
        menu.add(new QuitAction(this));
        bar.add(menu);
        
        setJMenuBar(bar);
        
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new MenusActions();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        
        frame.setSize(frame.getPreferredSize());
        frame.setVisible(true);
    }
}

class BuddyAction extends AbstractAction
{
    Action buddy;
    
    public BuddyAction(String name)
    {
        Icon img = new ImageIcon("images/shapes.gif");
        putValue(Action.SMALL_ICON,img);
        putValue(Action.DEFAULT,img);
        putValue(Action.NAME,name);
    }
    
    public void setBuddy(Action a)
    {
        buddy = a;
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        if(buddy != null)
        {
            buddy.setEnabled(true);
            setEnabled(false);
        }
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
